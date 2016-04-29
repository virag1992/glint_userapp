package global;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Constants {

    // For User Availability

    // For GCM(Google Cloud Messaging)
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String SENDER_ID = "879478913368";
    public static final String TAG = "GCMDemo";

    public static final String DATABASE_NAME = "itimepunchdb.sqlite";

    // Folder name

    public static final String MediaFolderName = "talkingbookz";

    public static final String EXTRA_WEB_URL = "EXTRA_WEB_URL";
    public static final String EXTRA_WEB_TITLE = "EXTRA_WEB_TITLE";

    public static final String userObject = "userObject";

    // For Json Response
    public static final String JsonObjectData = "data";


    public static final class ApiResponseKey {
        public static final String Success = "success";
        public static final String Status = "status";
        public static final String True = "true";
        public static final String UserID = "UserID";
        public static final String Message = "message";
        public static final String Size = "size";
        public static final String currentTimestamp = "currentTimestamp";
        public static final String UserInfo = "UserInfo";
        public static final String ResponseData = "responseData";
    }


    public static final String KEY_OBJECT = "OBJECT";
    public static final String KEY_ABOUT_US = "aboutus";
    public static final String KEY_LEGAL = "legal";

    public static final int ERROR = 2;
    public static final int OK = 1;
    // Font names

    public static final String FontRegular = "helvetica_neue_regular.ttf";
    public static final String FontBold = "helvetica_neue_bold.ttf";

    public static final String PREF_IS_UPDATE = "IS_UPDATE";
    public static final String PREF_IS_LOGIN = "IS_LOGIN";


    public static final int padding = 0;
    public static final int MAX_PROFILE_SIZE = 512;
    // supported file formats
    public static final List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg", "png");


  /*public static String CONSUMER_KEY = "dKPLa86Kv0VmzTsbX3eK7wxKR";
    public static String CONSUMER_SECRET = "yte7IpQlBs5QSjMWwpEHjf4TsVBNrl4hjVXGGleXbtuijfqBFv";*/


    public static String CONSUMER_KEY = "EtzwTE248Jid2e88Li5WXDnDz";
    public static String CONSUMER_SECRET = "kme4xNqz9r4BQL1jNbPhOLFEK0qRbbaqxQ5wzziPvpjvr6Alnz";

    //public static String LINKEDIN_CONSUMER_KEY = "75jrp8rvyjo062";
    // public static String LINKEDIN_CONSUMER_SECRET_KEY = "Gd0m9JRghs9LHNsd";

    public static String LINKEDIN_CONSUMER_KEY = "75hiwb8dwhrbej";
    public static String LINKEDIN_CONSUMER_SECRET_KEY = "lQ3TH63DMQkOSKKa";

    public static String scopeParams = "rw_nus+r_basicprofile";

    public static String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
    public static String OAUTH_CALLBACK_HOST = "litestcalback";
    public static String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

    public static String PINTEREST_CLIENT_ID = "4810701391936898083";


    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }


}
