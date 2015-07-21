package com.imuto.joy.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuilongjun on 14/12/22.
 */
public abstract class AbsRecyclerAdapter<T> extends RecyclerView.Adapter<AbsRecyclerAdapter.AbsViewHolder> {

    private static final int HEADERS_START = Integer.MIN_VALUE;
    private static final int FOOTERS_START = Integer.MIN_VALUE + 10000;

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<T> mDataSet;
    private int[] mLayoutRes;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<View> mHeaderViews;
    private List<View> mFooterViews;

    private static class StaticViewHolder extends AbsViewHolder {
        public StaticViewHolder(View itemView, AbsRecyclerAdapter adapter) {
            super(itemView, adapter);
        }
    }

    public static abstract class AbsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        AbsRecyclerAdapter adapter;

        public AbsViewHolder(View itemView, AbsRecyclerAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            this.adapter = adapter;
        }

//        public void bindAdater(AbsRecyclerAdapter adapter) {
//            this.adapter = adapter;
//        }

        public void setItemPosition(int position) {
        }

        @Override
        public void onClick(View v) {
            if (this.adapter.mOnItemClickListener != null) {
                this.adapter.mOnItemClickListener.onItemClick(itemView, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (this.adapter.mOnItemLongClickListener != null) {
                return this.adapter.mOnItemLongClickListener.onItemLongClick(itemView, getPosition());
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClick(View view, int position);
    }

    public AbsRecyclerAdapter(Context mContext, ArrayList<T> mDataSet, int... layout) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        this.mInflater = LayoutInflater.from(mContext);
        this.mLayoutRes = layout;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    public void addHeaderView(View view) {
        if (view == null) {
            return;
        }
        mHeaderViews.add(view);
    }

    public void removeHeaderView(View view) {
        if (view == null) {
            return;
        }
        mHeaderViews.remove(view);
    }

    public void addFooterView(View view) {
        if (view == null) {
            return;
        }
        mFooterViews.add(view);
    }

    public void removeFooterView(View view) {
        if (view == null) {
            return;
        }
        mFooterViews.remove(view);
    }


    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public int getFooterCount() {
        return mFooterViews.size();
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public final AbsRecyclerAdapter.AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < HEADERS_START + getHeaderCount())
            return new StaticViewHolder(mHeaderViews.get(viewType - HEADERS_START), this);
        else if (viewType < FOOTERS_START + getFooterCount())
            return new StaticViewHolder(mFooterViews.get(viewType - FOOTERS_START), this);
        else {
            View view = mInflater.inflate(mLayoutRes[viewType], parent, false);
            AbsRecyclerAdapter.AbsViewHolder vh = onCreateViewHolder(parent, viewType, view);
            return vh;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        int hCount = getHeaderCount();
        if (position < hCount) {
//            YLLog.e("AbsRecyclerAdapter", "return header view type   " + (HEADERS_START + position));
            return HEADERS_START + position;
        } else {
            int itemCount = getCount();
            if (position < hCount + itemCount) {
//                YLLog.e("AbsRecyclerAdapter", "return item view type  " + getRecyclerItemViewType(position - hCount));
                return getRecyclerItemViewType(position - hCount);
            } else {
//                YLLog.e("AbsRecyclerAdapter", "return footer view type  " + (FOOTERS_START + position - hCount - itemCount));
                return FOOTERS_START + position - hCount - itemCount;
            }
        }
    }

    public int getRecyclerItemViewType(int position) {
        if (getViewTypeCount() > 1) {
            throw new RuntimeException("You must override this method!");
        }
        return 0;
    }


    public int getCount() {
        if (mDataSet == null) {
            return 0;
        }
        return mDataSet.size();
    }


    @Override
    public int getItemCount() {
        return getHeaderCount() + getFooterCount() + getCount();
    }

    public abstract AbsRecyclerAdapter.AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType, View view);

    @Override
    public final void onBindViewHolder(AbsViewHolder viewHolder, int position) {
//        YLLog.e("AbsRecyclerAdapter", "onBindViewHolder  " + position);
        int hCount = getHeaderCount();
        if (position < hCount) {
            onBindHeaderView(position, viewHolder.itemView);
        } else if (position >= hCount && position < hCount + getCount()) {
            int pos = position - hCount;
            viewHolder.setItemPosition(position - hCount);
            onBindItemViewHolder(viewHolder, getItem(pos), pos, position);
        }
    }

    public void onBindHeaderView(int position, View convertView) {

    }


    public abstract void onBindItemViewHolder(AbsViewHolder viewHolder, T data, int position, int realPosition);

    public void onBindHeaderViewHolder(StaticViewHolder viewHolder, int position) {
    }

    public void onBindFooterViewHolder(StaticViewHolder viewHolder, int position) {
    }

    public T getItem(int position) {
        if (mDataSet == null || mDataSet.isEmpty() || mDataSet.size() <= position || position < 0) {
            return null;
        }
        return mDataSet.get(position);
    }

    public boolean isEmpty() {
        return mDataSet == null || mDataSet.isEmpty();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public void setDataSet(ArrayList<T> set) {
        this.mDataSet = set;
    }

    public ArrayList<T> getDataSet() {
        return mDataSet;
    }

    public int getViewTypeCount() {
        return mLayoutRes.length;
    }

    public int[] getLayouts() {
        return mLayoutRes;
    }


}
