package com.justin.social.LogUtils;

/**
 * Created by Clarence on 22/6/16.
 * <p>
 * 扩展功能的Log，可以实现点击定位，{@link Debug}控制log输出 <br />
 * <br />
 * 1.只有一个参数的函数，它的tag使用默认{@link #TAG}. 而两个参数的可以自定义tag。<br />
 * 2.在eclipse的LogCat中输出时，双击[PLACE]开头的那一行就可以定位到发出log的那一行代码.<br />
 * 3.[MESSAGE]开头的那一行就是log消息。 <br />
 * <br />
 * <b style="color:red;">正式版发布时，一定要把{@link CommonLog#DEG}设置为false</b> </br>
 */
public class CommonLog {
    /**
     * log总开关
     */
    public static boolean DEG = BuildUtils.DEBUG;

    public static String TAG          = "music";
    private static final String HEAD_MESSAGE = "[MESSAGE] ";
    private static final String HEAD_PLACE   = "[PLACE] ";
    private static final String BLANK_LINE   = "\n ";

    private static final int STACK_LAYER = 3;

    public static int i(String msg) {
        return DEG ? android.util.Log.i(TAG, getFormatedMsg(null, msg)) : -1;
    }

    public static int e(String msg) {
        return DEG ? android.util.Log.e(TAG, getFormatedMsg(null, msg)) : -1;
    }

    public static int d(String msg) {
        return DEG ? android.util.Log.d(TAG, getFormatedMsg(null, msg)) : -1;
    }

    public static int v(String msg) {
        return DEG ? android.util.Log.v(TAG, getFormatedMsg(null, msg)) : -1;
    }

    public static int w(String msg) {
        return DEG ? android.util.Log.w(TAG, getFormatedMsg(null, msg)) : -1;
    }

    public static int i(String subTag, String msg) {
        return DEG ? android.util.Log.i(TAG, getFormatedMsg(subTag, msg)) : -1;
    }

    public static int e(String subTag, String msg) {
        return DEG ? android.util.Log.e(TAG, getFormatedMsg(subTag, msg)) : -1;
    }

    public static int d(String subTag, String msg) {
        return DEG ? android.util.Log.d(TAG, getFormatedMsg(subTag, msg)) : -1;
    }

    public static int dd(String tag, String msg) {
        return DEG ? android.util.Log.d(tag, getSimpleFormatedMsg(tag, msg)) : -1;
    }


    public static int v(String subTag, String msg) {
        return DEG ? android.util.Log.v(TAG, getFormatedMsg(subTag, msg)) : -1;
    }

    public static int w(String subTag, String msg) {
        return DEG ? android.util.Log.w(TAG, getFormatedMsg(subTag, msg)) : -1;
    }

    private static String getFormatedMsg(String subTag, String msg) {
        if (subTag == null) {
            return getPlace() + HEAD_MESSAGE + msg + BLANK_LINE;
        } else {
            return "[" + subTag + "] " + msg + getPlace();
        }
    }

    private static String getPlace() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        if (stacks.length <= STACK_LAYER) {
            return "";
        }
        return new StringBuilder().append(" at ")
                .append(stacks[STACK_LAYER].getClassName()).append(".")
                .append(stacks[STACK_LAYER].getMethodName()).append("(")
                .append(stacks[STACK_LAYER].getFileName()).append(":")
                .append(stacks[STACK_LAYER].getLineNumber()).append(")\n")
                .toString();
    }


    /**
     * 简化 log 输出
     *
     * @param Tag
     * @param msg
     * @return
     */
    private static String getSimpleFormatedMsg(String Tag, String msg) {
        if (Tag == null) {
            return msg + BLANK_LINE + getSimplePlace();
        } else {
            return msg + getSimplePlace();
        }
    }

    private static String getSimplePlace() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        if (stacks.length <= STACK_LAYER) {
            return "";
        }
        return new StringBuilder().append("      -->")
                .append(stacks[STACK_LAYER].getClassName()).append(".")
                .append(stacks[STACK_LAYER].getMethodName()).append("(")
                .append(stacks[STACK_LAYER].getFileName()).append(":")
                .append(stacks[STACK_LAYER].getLineNumber()).append(")")
                .toString();
    }


}
