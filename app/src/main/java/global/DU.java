package global;

import android.widget.DatePicker;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DU {

    public static final String CALENDAR_VIEW_DATE_FORMAT = "yyyy-MM-dd";
    public static final SimpleDateFormat calenderViewDate = new SimpleDateFormat(CALENDAR_VIEW_DATE_FORMAT, Locale.getDefault());

    public static final String APP_DATE_FORMAT = "yyyy-MMM-dd";
    public static final String APP_PUNCH_DATE_FORMAT = "yyyy-MMM-dd, hh:mm:ss a";

    public static final String APP_PUNCH_DATE = "yyyy-MMM-dd";
    public static final String APP_PUNCH_TIME = "hh:mm:ss a";



    public static long getCurrentDateTimestamp() {

        Date now = new Date();
        long timestamp = now.getTime();

        Date date = new Date(timestamp);


        SimpleDateFormat sdf = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());



        String tempDate = sdf.format(date);

        Date temp = null;
        long miliseconds = 0;
        try {
            temp = sdf.parse(tempDate);
            miliseconds = temp.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return miliseconds/1000L;
    }

    public static String getHeaderDate(long timestamp) {
        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        dateFormatUser.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDatetimefromTimestamp(long timestamp) {
        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_PUNCH_DATE_FORMAT, Locale.getDefault());
        dateFormatUser.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDatetimefromTimestamp(long timestamp,String timezone) {
        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_PUNCH_DATE_FORMAT, Locale.getDefault());
        dateFormatUser.setTimeZone(TimeZone.getTimeZone(timezone));

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getEditDateTimestamp(long timestamp) {
        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_PUNCH_DATE, Locale.getDefault());
        dateFormatUser.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getEdittimefromTimestamp(long timestamp) {

        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_PUNCH_TIME, Locale.getDefault());
        dateFormatUser.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static long getCurrentDateTimeTimeStamp() {
        Date now = new Date();
        long timestamp = now.getTime();

        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat(APP_PUNCH_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());

        String tempDate = sdf.format(date);

        Date temp = null;
        long miliseconds = 0;
        try {
            temp = sdf.parse(tempDate);
            miliseconds = temp.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return miliseconds / 1000L;

    }


    // For calendar
    private static long parseAndGetTimestamp(String strDate) {

        calenderViewDate.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = calenderViewDate.parse(strDate);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getDatePickerVal(DatePicker dp) {

        String tDate = dp.getDayOfMonth() + "";
        String tMonth = (dp.getMonth() + 1) + "";
        String tYear = dp.getYear() + "";
        if (tDate.length() == 1) {
            tDate = "0" + tDate;
        }
        if (tMonth.length() == 1) {
            tMonth = "0" + tMonth;
        }

        Date date = new Date(parseAndGetTimestamp(tYear + "-" + tMonth + "-" + tDate));
        SimpleDateFormat sdf = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());

        String tempDate = sdf.format(date);

        return tempDate;
    }


    public static long getTimestampFromDate(String strDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());

        Date tempDate = null;
        long millis = 0;
        try {
            tempDate = sdf.parse(strDate);
            millis = tempDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return millis / 1000L;
    }

    public static String getDateFromTimestamp(long timestamp) {
        SimpleDateFormat dateFormatUser = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        dateFormatUser.setTimeZone(Global.setUserTimeZone());

        try {
            Date date = new Date(timestamp * 1000);
            return dateFormatUser.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateVal(String strDate) {

        return strDate.split("-")[2];
    }
    public static String getMonthVal(String strDate) {

        return strDate.split("-")[1];
    }
    public static String getYearVal(String strDate) {

        return strDate.split("-")[0];
    }


    public static String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm:ss a");
        return formatter.format(tme);
    }

    public static long getPunchInOutTimestampFromDate(String strDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(APP_PUNCH_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());

        Date tempDate = null;
        long millis = 0;
        try {
            tempDate = sdf.parse(strDate);
            millis = tempDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return millis / 1000L;
    }

    public static long getHeaderTimestampFromDate(String strDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault());
        sdf.setTimeZone(Global.setUserTimeZone());

        Date tempDate = null;
        long millis = 0;
        try {
            tempDate = sdf.parse(strDate);
            millis = tempDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return millis / 1000L;
    }

}