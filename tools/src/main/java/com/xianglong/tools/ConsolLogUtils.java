package com.xianglong.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsolLogUtils {

    static boolean openLog = true;

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");//换行符

    public static void json(String msg) {
        if (openLog) {
            String message;
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            android.util.Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                android.util.Log.i(tag, "║ " + line);
            }
            android.util.Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }


    }

    public static void json(String key, String msg) {
        if (openLog) {
            String message;
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }
            android.util.Log.i(tag, "Json数据start");
            android.util.Log.i(key, "╔═══════════════════════════════════════════════════════════════════════════════════════");
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                android.util.Log.i(key, "║ " + line);
            }
            android.util.Log.i(key, "╚═══════════════════════════════════════════════════════════════════════════════════════");
            android.util.Log.i(tag, "Json数据Over");
        }
    }

    public static void warn(String content) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.w(tag, content);
        }
    }

    public static void warn(Throwable content) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.w(tag, caller.getMethodName(), content);
        }
    }


    public static void error(String content) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.e(tag, content);
        }
    }

    public static void error(Throwable content) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.e(tag, caller.getMethodName(), content);
        }
    }

    public static void debug(String content) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.d(tag, content);
        }
    }

    public static void debug(Throwable throwable) {
        if (openLog) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            android.util.Log.d(tag, throwable.toString());
        }
    }


    private static String filtrateInnerClass(String className) {
        if (className.contains("$")) {
            return className.substring(0, className.indexOf('$'));
        }
        return className;
    }


    private static String generateTag(StackTraceElement caller) {
        String tag = "(%s.java:%s)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, filtrateInnerClass(callerClazzName), caller.getLineNumber());
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
