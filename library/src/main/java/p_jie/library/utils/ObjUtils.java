package p_jie.library.utils;

/**
 * Created by jj on 2017/4/1.
 */

public class ObjUtils {
    /**
     * 对象转String
     *
     * @param obj
     * @return
     */
    public String objToStr(Object obj) {
        return String.valueOf(obj).trim();
    }

    /**
     * 对象转Double
     *
     * @param obj
     * @return
     */

    public Double objToDouble(Object obj) {
        if (!objToStr(obj).equals("") && !objToStr(obj).equals("null")) {
            return Double.parseDouble(objToStr(obj));
        } else {
            return Double.MAX_VALUE;
        }
    }

    /**
     * 对象转Int
     *
     * @param obj
     * @return
     */
    public int objToInt(Object obj) {
        if (!objToStr(obj).equals("") && !objToStr(obj).equals("null")) {
            return Integer.parseInt(objToStr(obj));
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public String objToStrNull(Object obj) {
        return (obj == null) ? null : String.valueOf(obj).trim();
    }
}
