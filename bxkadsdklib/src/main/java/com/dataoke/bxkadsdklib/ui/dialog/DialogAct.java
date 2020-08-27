package com.dataoke.bxkadsdklib.ui.dialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dataoke.bxkadsdklib.R;

/**
 * Created by jey on 2019/10/20.
 * Desc : DialogAct
 * Update :
 * {
 * <p>
 * 红包弹窗类 传入 图片链接
 * <p>
 * by bxk on 2019/10/20
 * }
 */
public class DialogAct extends Dialog {

    public DialogAct(Context context) {
        super(context);
    }

    public DialogAct(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {

        private String mActImgUrl;
        private OnClickListener mCloseButtonClickListener;
        private OnClickListener mContentClickListener;
        private boolean mCancelable = true;
        private View layout;
        private DialogAct dialog;
        private Context mContext;

        public Builder(Context context) {
            //这里传入自定义的style，直接影响此Dialog的显示效果。
            // style具体实现见style.xml
            mContext = context;
            dialog = new DialogAct(context, R.style.ActDialog);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.my_dialog_layout, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        public Builder setCloseBtnClick(OnClickListener listener) {
            this.mCloseButtonClickListener = listener;
            return this;
        }

        public Builder setContentClick(OnClickListener listener) {
            this.mContentClickListener = listener;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.mActImgUrl = imgUrl;
            return this;
        }

        /**
         * 创建更新对话框
         *
         * @return
         */
        public DialogAct create() {
            // 设置关闭 按钮 监听
            layout.findViewById(R.id.linear_dialog_act_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCloseButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                }
            });
            // 设置关闭 按钮 监听
            layout.findViewById(R.id.cts_dialog_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContentClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                }
            });
            //设置 活动图片
            if (mActImgUrl != null) {
                ImageView imgAct = layout.findViewById(R.id.img_dialog_act);
                Glide.with(mContext).load(mActImgUrl).into(imgAct);
            }
            initDialog();
            return dialog;
        }

        /**
         * 单按钮对话框和双按钮对话框的公共部分在这里设置
         */
        private void initDialog() {
            dialog.setContentView(layout);
            dialog.setCancelable(true);                           //用户可以点击手机Back键取消对话框显示
            dialog.setCanceledOnTouchOutside(mCancelable);        //用户不能通过点击对话框之外的地方取消对话框显示
        }

    }

}