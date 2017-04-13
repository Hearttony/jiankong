package com.regongzaixian.jiankong.net;

import com.regongzaixian.jiankong.model.DeviceEntity;
import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.model.StandardEntity;
import com.regongzaixian.jiankong.model.TestDataEntity;
import com.regongzaixian.jiankong.model.TestPlanDetailEntity;
import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.model.UserEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/3/28
 * Time: 下午9:57
 * Description:接口声明处
 */
public interface ApiService {
    /*注册登录************************************************************************/

    @POST("auth/local")
    Observable<UserEntity> login(@Body Map<String, String> params);

    @POST("auth/local/register")
    Observable<UserEntity> register(@Body Map<String, String> params);

    /*查看新建测试*********************************************************************/

    /**
     * 获取技术依赖文件
     */
    @GET("standard")
    Observable<List<StandardEntity>> queryStandard();

    /**
     * 创建测试（开始采集）
     */
    @POST("testplan")
    Observable<TestPlanGroup> createTestPlan(@Body Map<String, String> params);

    /**
     * 查询测试list
     */
    @GET("listtestplangroup")
    Observable<List<TestPlanGroup>> queryTestPlan(@QueryMap Map<String, String> params);

    /**
     * 查询单个测试计划
     *
     * @param testPlanId
     * @return
     */
    @GET("testplan/{id}")
    Observable<TestPlanDetailEntity> queryTestPlanDetail(@Path("id") int testPlanId, @QueryMap Map<String, String> param);

    /**
     * 测试数据
     *
     * @param params
     * @return
     */
    @GET("testdataplan")
    Observable<TestDataEntity> queryTestPlanDetailData(@QueryMap Map<String, String> params);

    /**
     * 删除测试计划
     *
     * @param testPlanId
     * @return
     */
    @DELETE("testplan/{id}")
    Observable<Object> deleteTestPlan(@Path("id") int testPlanId);

    /**
     * 停止测试计划
     *
     * @param testPlanId
     * @param params
     * @return
     */
    @PUT("testplan/{id}")
    Observable<TestPlanDetailEntity> stopTestPlan(@Path("id") int testPlanId, @Body Map<String, String> params);

    /**
     * 发送邮件
     *
     * @param params
     * @return
     */
    @POST("email")
    Observable<Object> sendEmail(@Body Map<String, String> params);

    /*被测设备************************************************************************/

    /**
     * 第一页数据请求
     *
     * @param param
     * @return
     */
    @GET("device")
    Observable<List<DeviceEntity>> queryDevices(@QueryMap Map<String, String> param);

    /**
     * 分页数据请求
     *
     * @param params
     * @return
     */
    @GET("device")
    Observable<List<DeviceEntity>> queryDeviceForPage(@QueryMap Map<String, String> params);

    //创建被测设备
    @POST("device")
    Observable<DeviceEntity> createDevice(@Body Map<String, String> params);

    @PUT("device/{id}")
    Observable<DeviceEntity> modifyDevice(@Path("id") int id, @Body Map<String, String> params);

    @DELETE("device/{id}")
    Observable<DeviceEntity> deleteDevice(@Path("id") int id);

    /*测试设备*********************************************************************************/

    /**
     * 第一页数据请求
     *
     * @param params
     * @return
     */
    @GET("instrument")
    Observable<List<InstrumentEntity>> queryInstruments(@QueryMap Map<String, String> params);

    /**
     * 分页数据请求
     *
     * @param params
     * @return
     */
    @GET("instrument")
    Observable<List<InstrumentEntity>> queryInstrumentsForPage(@QueryMap Map<String, String> params);

    //创建被测设备
    @POST("instrument")
    Observable<InstrumentEntity> createInstrument(@Body Map<String, String> params);

    @PUT("instrument/{id}")
    Observable<InstrumentEntity> modifyInstrument(@Path("id") int id, @Body Map<String, String> params);

    @DELETE("instrument/{id}")
    Observable<InstrumentEntity> deleteInstrument(@Path("id") int id);

    /*数据查询**********************************************************************************/

    /**
     * 查询测试list
     */
    @GET("listtestplangroup")
    Observable<List<TestPlanGroup>> queryLastDayTestPlan(@QueryMap Map<String, String> params);

    @GET("listtestplangroup")
    Observable<List<TestPlanGroup>> searchByWhere(@QueryMap Map<String, String> params);
}
