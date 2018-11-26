package com.leading.recyclerviewitemsanimationtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Zj
 * @package com.leading.recyclerviewitemsanimationtest
 * @fileName CustomRecyclerViewAdapter
 * @date 2018/11/21 11:41
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {
    private static final String TAG = "CustomRecyclerViewAdapt";
    private ArrayList<String> mData;
    private RecyclerView mRecyclerView;

    /**
     * RecyclerView 是否正在向上滚动
     */
    private boolean isPullingUp = false;

    /**
     * RecyclerView 是否正在向下滚动
     */
    private boolean isPullDown = false;

    /**
     * RecyclerView item的入场动画
     */
    private Animation bottomInAnim;
    private Animation topInAnim;

    public CustomRecyclerViewAdapter(ArrayList<String> mData, final RecyclerView mRecyclerView) {
        this.mData = mData;
        this.mRecyclerView = mRecyclerView;
        bottomInAnim = AnimationUtils.loadAnimation(mRecyclerView.getContext(), R.anim.bottom_up_in_anim);
        topInAnim = AnimationUtils.loadAnimation(mRecyclerView.getContext(), R.anim.top_down_in_anim);

        /**
         * 为RecyclerView设置滚动监听
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isPullingUp = (dy > 0);
                isPullDown = (dy < 0);
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final CustomViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        //清除当前显示区域中所有item的动画
        int childCount = mRecyclerView.getChildCount();
        Log.e(TAG, "onViewAttachedToWindow: childCount--> " + childCount);
        for (int i = 0; i < childCount; i++) {
            View child = mRecyclerView.getChildAt(i);
            if (child != null) {
                child.clearAnimation();
            }
        }

        //然后给当前item添加上动画
        if (isPullDown) {
            holder.itemView.startAnimation(topInAnim);
        }

        if (isPullingUp) {
            holder.itemView.startAnimation(bottomInAnim);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recyclerview_item, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
        }
    }

}
