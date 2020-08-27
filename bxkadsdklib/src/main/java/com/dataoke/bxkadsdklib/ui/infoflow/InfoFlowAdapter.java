package com.dataoke.bxkadsdklib.ui.infoflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dataoke.bxkadsdklib.R;
import com.dataoke.bxkadsdklib.http.bean.InfoFlowBean;
import com.dataoke.bxkadsdklib.interfaces.IClickItemListener;
import com.dataoke.bxkadsdklib.util.StrUtil;
import com.dataoke.bxkadsdklib.util.Util;

import java.util.List;

public class InfoFlowAdapter extends RecyclerView.Adapter<InfoFlowAdapter.MyView> {
    private Context context;
    private List<InfoFlowBean.ListBean> list;
    private IClickItemListener<String> listener;

    public InfoFlowAdapter(Context context, List<InfoFlowBean.ListBean> list,IClickItemListener<String> listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyView(LayoutInflater.from(context).inflate(R.layout.info_flow_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {
        Log.e("qwer",list.size()+"...onBindViewHolder");
        InfoFlowBean.ListBean bean = list.get(i);
        myView.price_text.setText(StrUtil.removeZero(bean.getJiage()+"")); //价格
        myView.label_text.setText(bean.getTag1());
        myView.quan_price_text.setText(StrUtil.removeZero(bean.getQuanJine()+"")+"元");
        String goodsName = bean.getDtitle(); // 商品标题
        if (!TextUtils.isEmpty(goodsName)) {
            SpannableString spannableString = new SpannableString(goodsName);
            LeadingMarginSpan.Standard what = new LeadingMarginSpan.Standard(Util.dip2px(context,15), 0);
            spannableString.setSpan(what, 0, spannableString.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            myView.title_text.setText(spannableString);
        } else {
            myView.title_text.setText("");
        }
        Glide.with(context).load(bean.getPic()).apply(RequestOptions.centerCropTransform()).into(myView.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
//        return 20;
    }

    class MyView extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView price_text,label_text,quan_price_text,title_text;
        public MyView(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            price_text=itemView.findViewById(R.id.price_text);
            label_text=itemView.findViewById(R.id.label_text);
            quan_price_text=itemView.findViewById(R.id.quan_price_text);
            title_text=itemView.findViewById(R.id.title_text);
            itemView.setOnClickListener(view -> listener.clickItem(getLayoutPosition(),list.get(getLayoutPosition()).getUrl()));
        }
    }
}
