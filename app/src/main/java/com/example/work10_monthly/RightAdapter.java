package com.example.work10_monthly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.work10_monthly.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context mContext;
    private List<RightBean.DataBean.ListBean>mjihe;

    public RightAdapter(Context context) {
        mContext = context;
        mjihe=new ArrayList <>();
    }

    public void setMjihe(List <RightBean.DataBean.ListBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.rightadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mjihe.get(i).getName());
        Glide.with(mContext).load(mjihe.get(i).getIcon()).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;
            ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.Right_Text);
            mImageView=itemView.findViewById(R.id.Right_Image);
        }
    }
}
