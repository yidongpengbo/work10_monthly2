package com.example.work10_monthly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work10_monthly.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
            private List<LeftBean.DataBean> mjihe;
            private Context mContext;

    public LeftAdapter(Context context) {
        mContext = context;
        mjihe=new ArrayList <>();
    }

    public void setMjihe(List <LeftBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.leftadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.left_text.setText(mjihe.get(i).getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLeftCallBack.leftback(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView left_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            left_text=itemView.findViewById(R.id.LeftText);
        }
    }

    /**
     * 接口
     */
    public interface LeftCallBack{
        void leftback(int i);
    }
    LeftCallBack mLeftCallBack;

    public void setLeftCallBack(LeftCallBack leftCallBack) {
        mLeftCallBack = leftCallBack;
    }
}
