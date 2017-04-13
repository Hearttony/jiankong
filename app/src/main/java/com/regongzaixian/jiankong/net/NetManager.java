package com.regongzaixian.jiankong.net;

import android.content.Intent;

import com.regongzaixian.jiankong.BuildConfig;
import com.regongzaixian.jiankong.base.XJApp;
import com.regongzaixian.jiankong.util.Preferences;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/3/27
 * Time: 下午10:28
 * Description:
 * 网络操作管理类，请求都从此类发出
 * 1.登录/注册成功后调用refreshRetrofit(),将Authorization重新赋值
 */
public class NetManager {
    private static NetManager instance;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private Map<String, String> headerMap;

    private NetManager() {
        refreshRetrofit();
    }

    /**
     * 刷新retrofit的请求头,让之后请求全都带有token
     */
    public void refreshRetrofit() {
        initHeaderMap();
        initRetrofit();
    }

    private void initHeaderMap() {
        this.headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json; charset=UTF-8");
        if (!Preferences.getInstance().getToken().isEmpty()) {
            headerMap.put("Authorization", "bearer " + Preferences.getInstance().getToken());
        }
    }

    private void initRetrofit() {
        this.okHttpClient = (new OkHttpClient.Builder())
                .readTimeout(30000L, TimeUnit.SECONDS)
                .connectTimeout(30000L, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        try {
                            Request response1 = chain.request().newBuilder().headers(Headers.of(headerMap)).build();
                            return chain.proceed(response1);
                        } catch (Exception var5) {
                            Response response;
                            try {
                                response = chain.proceed(chain.request());
                            } catch (Exception var4) {
                                var4.printStackTrace();
                                response = (new Response.Builder()).request(chain.request()).protocol(Protocol.HTTP_1_1).code(1).build();
                            }

                            return response;
                        }
                    }
                }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    public <M> void runRxJava(Observable<M> observable, final Subscriber<M> subscriberCallBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<M>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (!NetWorkUtils.isConnectedByState(XJApp.getInstance())) {
                            subscriberCallBack.onError(new Exception("请检查网络连接"));
                            return;
                        }
                        //token失效处理
                        int errorCode = ((HttpException) throwable).response().code();
                        if (errorCode == 403) {
                            subscriberCallBack.onError(new Exception("token失效,请重新登录"));
                            XJApp.getInstance().sendBroadcast(new Intent("token_invalid"));
                            return;
                        }

                        ResponseBody body = ((HttpException) throwable).response().errorBody();
                        try {
                            JSONObject response = new JSONObject(body.string());
                            String msg = (String) response.get("message");
                            subscriberCallBack.onError(new Exception(msg));
                        } catch (Exception e1) {
                            subscriberCallBack.onError(new Exception("unKnown error"));
                            e1.printStackTrace();
                        }

                    }

                    @Override
                    public void onNext(M m) {
                        subscriberCallBack.onNext(m);
                    }
                });
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
