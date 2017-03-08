package p_jie.library.adapter.recycleAdapter.listener;

import android.view.View;

import p_jie.library.adapter.recycleAdapter.BaseQuickAdapter;


public abstract class OnItemChildLongClickListener extends SimpleClickListener {


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildLongClick(adapter,view,position);
    }
    public abstract void onSimpleItemChildLongClick(BaseQuickAdapter adapter, View view, int position);
}
