package com.regongzaixian.jiankong.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.util.materialdialog.IPromptDilaog;
import com.regongzaixian.jiankong.util.materialdialog.PromptDialog;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:40
 * Description:
 */
public class DialogUtil {
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setContentView(v,
                new LinearLayout.LayoutParams(
                        DeviceUtil.getScreenWidth(context) / 2,
                        LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                loadingDialog.dismiss();
            }
        });
        return loadingDialog;

    }

    public static Dialog createLoadingDialog(Context context) {
        return createLoadingDialog(context, "请稍候...");
    }


    public static PromptDialog.Builder createDialog(Context context,
                                                    @Nullable final String title, String content, String txtOk, @Nullable String txtCancle,
                                                    final IPromptDilaog callBack, boolean cancelable) {

        PromptDialog.Builder dialog = new PromptDialog.Builder(context);
        if (title == null) {
            dialog.setTitle("提示");
        } else {
            dialog.setTitle(title);
        }
        dialog.setMessage(content);
        dialog.setButton1(txtOk, new PromptDialog.OnClickListener() {

            @Override
            public void onClick(Dialog dialog, int which) {
                if (!"视频文件失效".equals(title)) {
                    dialog.dismiss();
                }
                callBack.onOK();
            }
        });
        if (txtCancle != null) {
            dialog.setButton2(txtCancle, new PromptDialog.OnClickListener() {

                @Override
                public void onClick(Dialog dialog, int which) {
                    dialog.dismiss();
                    callBack.onCancle();
                }
            });
        }
        dialog.setCancelable(cancelable);
        return dialog;

    }

    /**
     * 确认对话框
     *
     * @param context
     * @param content
     * @param txtOk
     * @param txtCancle
     * @param callBack
     * @return
     */
    public static PromptDialog.Builder createDialog(Context context,
                                                    String content, String txtOk, String txtCancle,
                                                    IPromptDilaog callBack, boolean cancelable) {
        return createDialog(context, null, content, txtOk, txtCancle, callBack, cancelable);
    }

    public static PromptDialog.Builder createDialog(Context context,
                                                    String content, String txtOk,
                                                    IPromptDilaog callBack, boolean cancelable) {
        return createDialog(context, null, content, txtOk, null, callBack, cancelable);
    }
}
