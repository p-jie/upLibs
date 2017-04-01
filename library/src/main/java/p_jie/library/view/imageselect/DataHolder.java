package p_jie.library.view.imageselect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p_jie.library.view.imageselect.bean.ImageItem;

/**
 * 使用弱引用，避免相册里大量图片引发问题
 */

public class DataHolder {
    public static final String DH_CURRENT_IMAGE_FOLDER_ITEMS = "dh_current_image_folder_items";

    private static DataHolder mInstance;
    private Map<String, List<ImageItem>> data;

    public static DataHolder getInstance() {
        if (mInstance == null){
            synchronized (DataHolder.class){
                if (mInstance == null){
                    mInstance = new DataHolder();
                }
            }
        }
        return mInstance;
    }

    private DataHolder() {
        data = new HashMap<>();
    }

    public void save(String id, List<ImageItem> object) {
        if (data != null){
            data.put(id, object);
        }
    }

    public Object retrieve(String id) {
        if (data == null || mInstance == null){
            throw new RuntimeException("你必须先初始化");
        }
        return data.get(id);
    }
//    public static final String DH_CURRENT_IMAGE_FOLDER_ITEMS = "dh_current_image_folder_items";
//
//    private static final DataHolder ourInstance = new DataHolder();
//
//    public static DataHolder getInstance() {
//        return ourInstance;
//    }
//
//    private DataHolder() {
//    }
//
//
//    Map<String, WeakReference<Object>> data = new HashMap<String, WeakReference<Object>>();
//
//    public void save(String id, Object object) {
//        data.put(id, new WeakReference<Object>(object));
//    }
//
//    public Object retrieve(String id) {
//        WeakReference<Object> objectWeakReference = data.get(id);
//        return objectWeakReference.get();
//    }
}
