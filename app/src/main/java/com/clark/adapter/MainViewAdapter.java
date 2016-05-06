package com.clark.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.clark.demo.R;
import com.clark.holder.MainViewHolder;
import com.clark.inter.OnRecyclerViewClickListener;


/**
 * Created by ClarkWu on 2016/3/24.
 */
public class MainViewAdapter extends RecyclerView.Adapter{
    public int [] mPics = new int[]{R.mipmap.tutorial,R.mipmap.work};
    public String[]  mItems = new String[]{"tutorial","work"};

    public OnRecyclerViewClickListener getmOnRecyclerItemListener() {
        return mOnRecyclerItemListener;
    }

    private OnRecyclerViewClickListener mOnRecyclerItemListener;

    public void setOnRecyclerItemListener(OnRecyclerViewClickListener mOnItemClickListener){
        this.mOnRecyclerItemListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main,parent,false);
        MainViewHolder mMainViewHolder = new MainViewHolder(recyclerView);
        mMainViewHolder.imgBtn =  (Button) recyclerView.findViewById(R.id.imgBtn);
        mMainViewHolder.txtTitle = (TextView) recyclerView.findViewById(R.id.txt);
        return mMainViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MainViewHolder viewHolder = (MainViewHolder)holder;
        viewHolder.imgBtn.setBackgroundResource(mPics[position]);
        viewHolder.txtTitle.setText(mItems[position]);
        if(getmOnRecyclerItemListener() != null){
            viewHolder.imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    getmOnRecyclerItemListener().onItemClick(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }
}


