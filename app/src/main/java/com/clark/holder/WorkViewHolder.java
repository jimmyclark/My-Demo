package com.clark.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ClarkWu on 2016/3/25.
 */
public class WorkViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTitle;
    public LinearLayout mLinearLayout;

    public WorkViewHolder(View itemView) {
        super(itemView);
    }
}
