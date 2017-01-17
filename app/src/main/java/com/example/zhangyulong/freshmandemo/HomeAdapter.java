package com.example.zhangyulong.freshmandemo;

/**
 * Created by zhangyulong on 16-11-8.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangyulong.freshmandemo.View.Content;
import com.example.zhangyulong.freshmandemo.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.zhangyulong.freshmandemo.View.BlankFragment.tmpIndex;
import static java.lang.Thread.sleep;

interface ItemClickListener {
    void OnItemClick(View v, int position);
}
public  class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    public static List<Artical.Data> mDatas=new ArrayList<>();
    private Context mContext;

    public HomeAdapter(Context context){
        this.mContext = context;
    }
    ItemClickListener mItemClickListener;
    public static void setmDatas(List<Artical.Data> mDatas) {
        HomeAdapter.mDatas = mDatas;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.activity_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.data = mDatas.get(position);
        holder.tv.setText(holder.data.getSubject());
        holder.content.setText(holder.data.getSummary());
        //holder.content.setText(Integer.toString(holder.data.getIndex()) );
        holder.itemView.setTag(position);

        Glide
                .with(mContext)
                .load(holder.data.getPic())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv, content;
        ImageView imageView;
        Artical.Data data;


        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            content = (TextView) view.findViewById(R.id.content);
            imageView = (ImageView) view.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.OnItemClick(v, (Integer) itemView.getTag());



            }

            Integer position=(Integer) itemView.getTag();
            tmpIndex=mDatas.get(position.intValue()).getIndex();
            Intent intent = new Intent(mContext, Content.class);
            mContext.startActivity(intent);


        }

    }
}

