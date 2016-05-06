package com.clark.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clark.demo.R;
import com.clark.holder.TutorialViewHolder;
import com.clark.inter.OnRecyclerViewClickListener;

/**
 * Created by ClarkWu on 2016/3/25.
 */
public class TutorialViewAdapter extends RecyclerView.Adapter{
    public String[] mItems = new String[]{"Activity LifeCycle","Dynamatic Items","save datas"};

    public OnRecyclerViewClickListener getmOnRecyclerItemListener() {
        return mOnRecyclerItemListener;
    }

    private OnRecyclerViewClickListener mOnRecyclerItemListener;

    public void setOnRecyclerItemListener(OnRecyclerViewClickListener mOnItemClickListener){
        this.mOnRecyclerItemListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recycleAdapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_content,parent,false);
        TutorialViewHolder holder = new TutorialViewHolder(recycleAdapterView);
        holder.txtTitle = (TextView) recycleAdapterView.findViewById(R.id.item_tutorial);
        holder.mLinearLayout = (LinearLayout) recycleAdapterView.findViewById(R.id.linearLayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TutorialViewHolder viewHolder = (TutorialViewHolder)holder;
        viewHolder.txtTitle.setText(mItems[position]);
        if(getmOnRecyclerItemListener() != null){
            viewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
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
