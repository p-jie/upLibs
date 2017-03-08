package p_jie.library.adapter.listviewAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author jj
 * @ClassName: ViewHolder
 * @Description:万能适配器
 * @date 2015-8-28 下午6:56:58
 */

public abstract class ListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected int mItemLayoutId;

    public ListAdapter(Context context, List<T> data, int itemLayoutId) {
        this.mContext = context;
        this.mData = data;
        this.mItemLayoutId = itemLayoutId;
    }

    public void upDate(List<T> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, converView, parent, mItemLayoutId, position);

        convert(holder, getItem(position), position);
        return holder.getmConverView();
    }

    //public abstract void convert(ViewHolder holder,T object);
    public abstract void convert(ViewHolder holder, T object, int position);
}
