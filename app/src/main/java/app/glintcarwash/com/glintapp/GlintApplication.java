package app.glintcarwash.com.glintapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ACER on 05-04-2016.
 */
public class GlintApplication extends Application {


    public static SharedPreferences sharedPref;

    public static Locale locale;

    public static TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
    private static GlintApplication _intance = null;
    //    private static ActiveRecordBase mDatabase;
    public static String DATABSENAME = "talkingbookz.db";
    public static int DATABASE_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//        Fresco.initialize(this);
        Log.v("app started", "Talking Book Started");
//        Foreground.init(this);
        init();
        locale = Locale.getDefault();
        timeZone = TimeZone.getTimeZone("US/Eastern");


//        DatabaseBuilder builder = new DatabaseBuilder(DATABSENAME);
//        builder.addClass(UserInfo.class);
//        builder.addClass(AudioBookChapterInfo.class);
//        builder.addClass(AudioBookInfo.class);
//        builder.addClass(CategoryInfo.class);
//        builder.addClass(Cart.class);
//        builder.addClass(WishListInfo.class);
//        builder.addClass(BookMarkInfo.class);
//        builder.addClass(MyAudioBook.class);
//        builder.addClass(MyAudioBookChapters.class);
//        builder.addClass(SubCategoryInfo.class);
//        builder.addClass(RecommendedBookInfo.class);
//        builder.addClass(RecommendedBookChapters.class);
//        builder.addClass(PlayerSettingInfo.class);
//        builder.addClass(BookInfoForCategory.class);
//        builder.addClass(SearchedBookInfo.class);
//
//        Database.setBuilder(builder);
//        try {
//            mDatabase = ActiveRecordBase.open(this, DATABSENAME,
//                    DATABASE_VERSION);
//        } catch (ActiveRecordException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void init() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        deviceInfo = new DeviceInfo(this);
//        AudioEngine config=AudioEngine.getInstance();
//        config.setLogLevel(LogLevel.VERBOSE);
//        config.setSession("a6ee082e-48b3-4c1d-af3b-dd0c1099f818");
//        config.ini
//        config.setAccountId(accountId);
//        config.setConsumerId(consumerId);
//        config.setSessionId(sessionId);

    }

    public GlintApplication() {
        _intance = this;
    }

//    public static ActiveRecordBase Connection() {
//        return mDatabase;
//    }

    public static Context getContext() {
        return _intance;
    }


}