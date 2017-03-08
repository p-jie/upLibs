package p_jie.library.adapter.recycleAdapter.listener;

import android.view.View;

import p_jie.library.adapter.recycleAdapter.BaseQuickAdapter;


public abstract   class OnItemClickListener extends SimpleClickListener {


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemClick(adapter,view,position);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
    public abstract void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position);
}
