package com.histudio.base.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;

import com.histudio.base.constant.BConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StringUtil {

    private static final SimpleDateFormat shortFormatter = new SimpleDateFormat("yyyy-MM-dd");
    // 时间毫秒值(一小时)
    private static final long ONE_HOUR_MILLI = 60 * 60 * 1000;
    // 时间毫秒值（一天）
    private static final long ONE_DAY_MILLI = 24 * ONE_HOUR_MILLI;

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    public static CharSequence getDateDescFormat(long millisecond) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        millisecond = millisecond * 1000;
        return commonDateDescFormat(millisecond);
    }

    public static boolean isEmptyString(String s) {
        return TextUtils.isEmpty(s);
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static int[] getYMD(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new int[]{cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE)};
    }

    /**
     * 把字符串解析成对应格式的date
     */
    public static Date parseDate(String s) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.parse(s);
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("YY年MM月dd日");
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getStringTime(long time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    public static String getDateTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getDataTime(long time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getYearTime(long time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getDataTime2(long time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getDataTime3(float time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式
     */
    public static String getHourTime(long time) {
        // 服务器中返回的时间只有10数，所以要自己手动*1000
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式(本地的不需要转化)
     */
    public static String getLocalTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String timeStr = formatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 将unix时间转换为时间格式(本地的不需要转化)
     */
    public static String getLocalTimeData(long time) {
        String timeStr = shortFormatter.format(time * 1000);
        return timeStr;
    }

    /**
     * 通用日期描述格式
     */
    public static CharSequence commonDateDescFormat(long millisecond) {
        CharSequence dateFormat = DateUtils.getRelativeTimeSpanString(millisecond);
        return dateFormat;
    }

    /**
     * 时间戳转换成具体格式日期字符
     */
    public static String convertTimetempToDate(long mill) {
        Date date = new Date(mill);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }


    /**
     * 时间戳转换成数据库存储需要的格式
     */
    public static String convertTimetempToDBDate(long mill) {
        Date date = new Date(mill);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    /**
     * 通过urlEncode字符串
     */
    public static String encodeString(String s) {
        try {
            return URLEncoder.encode(s, BConstants.DEFALUT_CODESET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过urlEncode字符串,字符串中包含中文 带汉字的字符串需要进行两次编码，服务端进行一次
     */
    public static String encodeZNString(String s) {
        try {
            return URLEncoder.encode(URLEncoder.encode(s, BConstants.DEFALUT_CODESET), BConstants.DEFALUT_CODESET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getLess15MinTime(long millisecond) {
        int ss = 1000;
        int mi = ss * 60;
        millisecond = millisecond * 1000;
        // 获取当前时间
        long currentTime = System.currentTimeMillis();
        long time15Min = 15 * mi;
        // 创建时间 +15分钟  - 当前时间
        long time = millisecond + time15Min - currentTime;


        long minute = time / mi;
        long second = (time - minute * mi) / ss;
        long milliSecond = millisecond - minute * mi - second * ss;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        //        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        //        strMilliSecond = milliSecond > 100 ? strMilliSecond.substring(0, strMilliSecond.length() - 1) : "" + strMilliSecond;

        return strMinute + "分" + strSecond + "秒";
    }

    public static String getMinTime(int time) {

        long minute = time / 60;
        long second = time - minute * 60;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒

        return minute + "\'" + strSecond + "\"";
    }

    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static List stringTolist(String str, char separator) {
        List list = new ArrayList<>();
        list = Arrays.asList(str.split(","));
        return list;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static String dataStr(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return "今天";
            } else if (diffDay == 1) {
                return "明天";
            } else if (diffDay == 2) {
                return "后天";
            }
        }
        return "";
    }


    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    /**
     * 时间转化为显示字符串
     *
     * @param timeStamp 单位为秒
     */
    public static String getTimeStr(long timeStamp) {
        if (timeStamp == 0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        if (calendar.before(inputTime)) {
            //今天23:59在输入时间之前，解决一些时间误差，把当天时间显示到这里
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.before(inputTime)) {
            return "昨天";
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("M" + "月" + "d" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);

            }

        }

    }

    /**
     * 时间转化为聊天界面显示字符串
     *
     * @param timeStamp 单位为秒
     */
    public static String getChatTimeStr(long timeStamp) {
        if (timeStamp == 0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        if (!calendar.after(inputTime)) {
            //当前时间在输入时间之前
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "昨天" + " " + sdf.format(currenTimeZone);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("M" + "月" + "d" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);
            }

        }

    }

    /**
     * 得到资源文件中图片的Uri
     *
     * @param context 上下文对象
     * @param id      资源id
     * @return Uri
     */
    public static String getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return path;
    }

    /*
    * Add color to a given text
    */
    public static SpannableStringBuilder addColor(CharSequence text, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (color != 0) {
            builder.setSpan(new ForegroundColorSpan(color), 0, text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     */
    public static String getSupportBeginDayofMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);

    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public static String getSupportEndDayofMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);
    }

    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime();
    }
}
