package p_jie.library.adapter.listviewAdapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * @ClassName: ViewHolder 
 * @Description:万能适配的的Holder（用来缓存各个组件）
 * @author jj
 * @date 2015-8-28 下午6:56:58
 */
public class ViewHolder {
	private SparseArray<View> mViews;
	
	public int mPosition;
	
	private View mConverView;
	
	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConverView = LayoutInflater.from(context).inflate(layoutId,parent,false);
		mConverView.setTag(this);
	}
	
	public static ViewHolder get(Context context,View converView,ViewGroup parent,int layoutId,int position){
		if(converView == null){
			return new ViewHolder(context,parent,layoutId,position);
		}else{
			ViewHolder holder =(ViewHolder)converView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	/**
	 * 通过ID获取控件
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId){
		View view = mViews.get(viewId);
		if(view == null){
			view = mConverView.findViewById(viewId);
			mViews.put(viewId, view);
			//setListener(view);
		}
		return (T)view;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId,IViewListener<T> listener){
		View view = mViews.get(viewId);
		if(view == null){
			view = mConverView.findViewById(viewId);
			mViews.put(viewId, view);
			listener.setListener((T)view);
		}
		return (T)view;
	}
	/**
	 * @ClassName: IViewListener 
	 * @Description:监听所有控件的事件
	 */
	public interface IViewListener<T>{
		void setListener(T view);
	}
	
	public View getmConverView() {
		return mConverView;
	}
	/**
	 * 给TextView设置类容
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setTextView(int viewId,String text){
		TextView tv = getView(viewId);
		tv.setText(String.valueOf(text).trim());
		return this;
	}
}
