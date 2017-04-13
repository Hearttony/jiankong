package com.regongzaixian.jiankong.util.materialdialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.util.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/3
 * Time: 下午11:14
 * Description:
 */
public class PromptDialog extends Dialog {
    private Context context;

    public static final int BUTTON_1 = 0x00000001;
    public static final int BUTTON_2 = 0x00000002;

    protected PromptDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public PromptDialog(Context context) {
        this(context, R.style.PromptDialogStyle);
    }

    protected PromptDialog(Context context, boolean cancelableOnTouchOutside) {
        this(context);
        this.setCanceledOnTouchOutside(cancelableOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        int marginBorderWidth = DeviceUtil.dip2px(context, 60);
        int marginBorderHeight = DeviceUtil.dip2px(context, 180);
        params.width = DeviceUtil.getScreenWidth(context) - marginBorderWidth * 2;
        params.height = DeviceUtil.getScreenHeight(context) - marginBorderHeight * 2;

        //针对平板做适配
        if (DeviceUtil.getScreenWidth(context) >= 1536) {
            params.width = (int) (DeviceUtil.getScreenWidth(context) * 0.4f);
            params.height = (int) (params.width * 5 / 4f);
        }

        window.setAttributes(params);
    }

    @SuppressLint({"NewApi", "InflateParams"})
    public static class Builder {
        private PromptDialog dialog;
        private Context context;
        private boolean messageGravityIsCenter;
        private String title;
        private String message;
        private String button1Text;
        private String button2Text;
        private int button1TextColor;
        private int button2TextColor;
        private int titleColor;
        private int messageColor;
        private float button1Size;
        private float button2Size;
        private float titleSize;
        private float messageSize;
        private ColorStateList titleColorStateList;
        private ColorStateList messageColorStateList;
        private ColorStateList button1ColorStateList;
        private ColorStateList button2ColorStateList;
        private int titlebarGravity;

        private boolean cancelable = true;
        private boolean canceledOnTouchOutside;
        private View view;

        private OnClickListener button1Listener;
        private OnClickListener button2Listener;

        private int button1Flag;
        private int button2Flag;

        private View mView = null;
        private TextView mTitle = null;
        private TextView mMessage = null;
        private TextView btnCancle = null;
        private TextView btnOk = null;
        private LinearLayout addView = null;
        private RelativeLayout btnView = null;

        /*************************
         * list
         **********************/
        private ListView mListView;
        private List<String> listItemData = new ArrayList<>();
        private DialogListViewAdapter listViewAdapter;
        private OnListDialogItemClickListener onListItemClickListener;
        private DialogType dialogType = DialogType.NOMAL_DIALOG;

        public Builder(Context context, int theme) {
            dialog = new PromptDialog(context, theme);
            this.context = context;
            initData();
        }

        public Builder(Context context) {
            dialog = new PromptDialog(context);
            this.context = context;
            initData();
        }

        private void initData() {
            this.button1TextColor = Color.parseColor("#ff009688");
            this.button2TextColor = Color.parseColor("#ff009688");
            this.messageColor = Color.parseColor("#ff009688");
            this.titleColor = Color.parseColor("#ff009688");
            this.button1Size = 16;
            this.button2Size = 16;
            this.messageSize = 15;
            this.titleSize = 18;
            this.titlebarGravity = Gravity.LEFT;
        }

        public Context getContext() {
            return context;
        }

        public Builder setTitleBarGravity(int titlebarGravity) {
            this.titlebarGravity = titlebarGravity;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessageGravityIsCenter(boolean messageGravityIsCenter) {
            this.messageGravityIsCenter = messageGravityIsCenter;
            return this;
        }

        public Builder setTitle(int titleResId) {
            this.title = context.getResources().getString(titleResId);
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setTitleColor(ColorStateList titleColor) {
            this.titleColorStateList = titleColor;
            return this;
        }

        public Builder setTitleSize(float titleSize) {
            this.titleSize = titleSize;
            return this;
        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int messageResId) {
            this.message = context.getResources().getString(messageResId);
            return this;
        }

        public Builder setMessageColor(int color) {
            this.messageColor = color;
            return this;
        }

        public Builder setMessageColor(ColorStateList color) {
            this.messageColorStateList = color;
            return this;
        }

        public Builder setMessageSize(float size) {
            this.messageSize = size;
            return this;
        }

        public Builder setButton1(String text,
                                  OnClickListener listener) {
            this.button1Text = text;
            this.button1Listener = listener;
            button1Flag = 1;
            return this;
        }

        public Builder setButton1(int textId,
                                  OnClickListener listener) {
            this.button1Text = context.getResources().getString(textId);
            this.button1Listener = listener;
            button1Flag = 1;
            return this;
        }

        public Builder setButton1TextColor(int color) {
            this.button1TextColor = color;
            return this;
        }

        public Builder setButton1TextColor(ColorStateList color) {
            this.button1ColorStateList = color;
            return this;
        }

        public Builder setButton1Size(float button1Size) {
            this.button1Size = button1Size;
            return this;
        }

        public Builder setButton2(String text,
                                  OnClickListener listener) {
            this.button2Text = text;
            this.button2Listener = listener;
            button2Flag = 2;
            return this;
        }

        public Builder setButton2(int textId,
                                  OnClickListener listener) {
            this.button2Text = context.getResources().getString(textId);
            this.button2Listener = listener;
            button2Flag = 2;
            return this;
        }

        public Builder setButton2TextColor(int color) {
            this.button2TextColor = color;
            return this;
        }

        public Builder setButton2TextColor(ColorStateList color) {
            this.button2ColorStateList = color;
            return this;
        }

        public Builder setButton2Size(float button2Size) {
            this.button2Size = button2Size;
            return this;
        }


        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceled) {
            this.canceledOnTouchOutside = canceled;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public void setListItemData(List<String> listItemData) {
            this.listItemData = listItemData;
        }

        public void setDialogType(DialogType dialogType) {
            this.dialogType = dialogType;
        }

        public DialogListViewAdapter getListViewAdapter() {
            return listViewAdapter;
        }

        public void setOnListItemClickListener(OnListDialogItemClickListener onListItemClickListener) {
            this.onListItemClickListener = onListItemClickListener;
        }

        @SuppressLint("InflateParams")
        public PromptDialog create() {
            if (dialog == null) {
                return null;
            }
            switch (dialogType) {
                case NOMAL_DIALOG:
                    mView = LayoutInflater.from(context).inflate(
                            R.layout.prompt_dialog_layout, null);
                    initNomalDialogView();
                    break;
                case LIST_DIALOG:
                    mView = LayoutInflater.from(context).inflate(
                            R.layout.prompt_dialog_listview_layout, null);
                    initListDialogView();
                    break;
                default:
                    break;
            }

            dialog.setCancelable(cancelable);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setContentView(mView);
            return dialog;
        }

        /**
         * init nomal dialog view
         */
        private void initNomalDialogView() {
            mTitle = (TextView) mView.findViewById(R.id.title);
            mMessage = (TextView) mView.findViewById(R.id.message);
            addView = (LinearLayout) mView.findViewById(R.id.layout_addview);
            btnCancle = (TextView) mView.findViewById(R.id.button_cancle);
            btnOk = (TextView) mView.findViewById(R.id.button_ok);
            btnView = (RelativeLayout) mView.findViewById(R.id.btn_view);
            initTitle();
            if (message != null) {
                if (messageGravityIsCenter) {
                    mMessage.setGravity(Gravity.CENTER);
                }
                mMessage.setVisibility(View.VISIBLE);
                mMessage.setText(message);
                mMessage.setTextSize(messageSize);
                mMessage.setTextColor(messageColor);
                if (messageColorStateList != null) {
                    mMessage.setTextColor(messageColorStateList);
                }
            } else {
                mMessage.setVisibility(View.GONE);
            }
            if (view != null) {
                addView.removeAllViews();
                addView.addView(view);
                addView.setGravity(Gravity.CENTER);
            }
            initBtnCancle();
            initBtnOk();
            int btnCountFlag = button1Flag + button2Flag;
            switch (btnCountFlag) {
                // one button
                case 1:
                    btnCancle.setVisibility(View.GONE);
                    btnOk.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    // two button
                    btnCancle.setVisibility(View.VISIBLE);
                    btnOk.setVisibility(View.VISIBLE);
                    break;
                default:
                    btnView.setVisibility(View.GONE);
                    break;
            }
        }

        private void initTitle() {
            if ((title != null)) {
                mTitle.setVisibility(View.VISIBLE);
                mTitle.setText(title);
                mTitle.setTextSize(titleSize);
                mTitle.setTextColor(titleColor);
                if (titleColorStateList != null) {
                    mTitle.setTextColor(titleColorStateList);
                }
            } else {
                mTitle.setVisibility(View.GONE);
            }
        }

        private void initBtnOk() {
            if (button1Text != null) {
                btnOk.setText(button1Text);
                btnOk.setTextSize(button1Size);
                btnOk.setTextColor(button1TextColor);

                if (button1ColorStateList != null) {
                    btnOk.setTextColor(button1ColorStateList);
                }

                if (button1Listener != null) {
                    btnOk.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            button1Listener.onClick(dialog, BUTTON_1);
                        }
                    });
                }
            }
        }

        private void initBtnCancle() {
            if (button2Text != null) {
                btnCancle.setText(button2Text);
                btnCancle.setTextSize(button2Size);
                btnCancle.setTextColor(button2TextColor);
                if (button2ColorStateList != null) {
                    btnCancle.setTextColor(button2ColorStateList);
                }
                if (button2Listener != null) {
                    btnCancle.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            button2Listener.onClick(dialog, BUTTON_2);
                        }
                    });
                }
            }
        }

        /**
         * init list dialog view
         */
        private void initListDialogView() {
            mTitle = (TextView) mView.findViewById(R.id.title);
            btnCancle = (TextView) mView.findViewById(R.id.button_cancle);
            btnOk = (TextView) mView.findViewById(R.id.button_ok);
            initTitle();
            initBtnOk();
            initBtnCancle();
            mListView = (ListView) mView.findViewById(R.id.listView);
            listViewAdapter = new DialogListViewAdapter(context, listItemData);
            mListView.setAdapter(listViewAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (onListItemClickListener != null) {
                        onListItemClickListener.onItemClick(view, position);
                    }
                }
            });
        }

        public PromptDialog show() {
            create().show();
            return dialog;
        }

        public PromptDialog getDialog() {
            return dialog;
        }
    }

    public interface OnClickListener {
        void onClick(Dialog dialog, int which);
    }

    public interface OnListDialogItemClickListener {
        void onItemClick(View v, int position);

    }

    /**
     * @nomal_dilog,默认情况下对话框
     * @list_dialog，带listview的对话框
     */
    public enum DialogType {
        NOMAL_DIALOG, LIST_DIALOG
    }
}
