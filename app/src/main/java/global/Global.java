package global;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Pattern;

import app.glintcarwash.com.glintapp.GlintApplication;
import app.glintcarwash.com.glintapp.R;


public class Global {

    //    public static FragmentManager fm;
//
//
//    public static boolean isUpdatesAvailable = false;
//
//    public static boolean isloaded = false;
//    public static DBConnect dbHelper = null;
//    public static boolean isTab = false;
//    public static final int BOLD = 0;
//    public static final int ITALIC = 1;
//    public static final int REGULAR = 2;
//    public static Dialog d = null;
//    public static Context currentAppContext;
//
//    public static TimeZone timeZone = null;
//
    public static TimeZone setUserTimeZone() {
        TimeZone timeZone = null;

        timeZone = TimeZone.getTimeZone("US/Eastern");


        return timeZone;
    }
//
//
//    public static String getGUID() {
//        return UUID.randomUUID().toString();
//    }
//
//    public static void showProgressDialog(Context context, String title) {
////		d = new Dialog(context);
////		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////		d.setCancelable(false);
////		d.setCanceledOnTouchOutside(false);
////		d.setContentView(R.layout.custom_progress_dialog);
////
////		TextView tv = (TextView) d.findViewById(R.id.tvMessage);
////		tv.setText(title);
////		d.show();
//
//    }
//
//    public static boolean checkRights(Context context, Operationtype op) {
//
//        UserInfo _userInfo = null;
//        _userInfo = new Gson().fromJson(Global.getPreference(Constants.userObject, ""), UserInfo.class);
//
//        if (_userInfo != null) {
//            if (_userInfo.istrustable.equals("0")) {
//                if (TalkingBookzApplication.untrustedEmployee.contains(op)) {
//                    return true;
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle(context.getResources().getString(R.string.txt_title_trust_dialog));
//                    builder.setMessage(context.getResources().getString(R.string.txt_message_trust_dialog));
//
//                    builder.setPositiveButton(context.getResources().getString(R.string.txt_ok_trust_dialog), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    AlertDialog alert = builder.create();
//                    alert.show();
//
//                    return false;
//
//                }
//
//            } else {
//                if (TalkingBookzApplication.trustedEmployee.contains(op)) {
//                    return true;
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle(context.getResources().getString(R.string.txt_title_trust_dialog));
//                    builder.setMessage(context.getResources().getString(R.string.txt_message_trust_dialog));
//
//                    builder.setPositiveButton(context.getResources().getString(R.string.txt_ok_trust_dialog), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    AlertDialog alert = builder.create();
//                    alert.show();
//
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static void showDownloadingProgressDialog(Context context, String counter, String message) {
////		d = new Dialog(context);
////		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////		d.setCancelable(false);
////		d.setCanceledOnTouchOutside(false);
////		d.setContentView(R.layout.custom_downloading_progressdialog);
////
////		TextView tvCounter = (TextView) d.findViewById(R.id.tvCounter);
////		TextView tv = (TextView) d.findViewById(R.id.tvMessage);
////		tv.setText(message);
////		tvCounter.setText(context.getResources().getString(R.string.downloading_resource) + counter);
////		tv.setEllipsize(TruncateAt.START);
////		d.show();
//
//    }
//
//    public static void showProgressDialog(Context context) {
//        d = new Dialog(context);
////		d.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        d.setCancelable(false);
//        d.setCanceledOnTouchOutside(false);
//        d.setContentView(R.layout.custom_progress_dialog);
//        d.show();
//
//    }
//
//    public static void dismissProgressDialog() {
//
//        d.dismiss();
//
//    }
//
//    public static void showAlertDialog(Context context, String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(message);
//        builder.setTitle(context.getResources().getString(R.string.txt_title));
//        builder.setPositiveButton(context.getResources().getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog alert = builder.create();
//        alert.show();
//    }


    public static synchronized boolean isNetworkAvailable(Context context) {
        boolean flag = false;
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mgr != null) {
                boolean mobileNetwork = false;
                boolean wifiNetwork = false;

                NetworkInfo mobileInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (mobileInfo != null)
                    mobileNetwork = mobileInfo.isAvailable();
                if (wifiInfo != null)
                    wifiNetwork = wifiInfo.isAvailable();
                flag = (mobileNetwork || wifiNetwork);
            }
        }

        return flag;
    }


    public static void showAlertDialog(Context context, String title, String message, String positiveButton, String negativeButton, DialogInterface.OnClickListener dialogClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message).setPositiveButton(positiveButton, dialogClickListener)
                .setNegativeButton(negativeButton, dialogClickListener).show();

    }

    public static void showAlertDialog(Context context, String title, String message, String positiveButton, DialogInterface.OnClickListener dialogClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message).setPositiveButton(positiveButton, dialogClickListener)
                .show();

    }

    /**
     * Checking device has camera hardware or not
     */
    public static boolean isDeviceSupportCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static void storePreference(HashMap<String, String> parameters) {

        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> pairs = it.next();
            editor.putString((String) pairs.getKey(), (String) pairs.getValue());
        }
        editor.commit();
    }

    public static void storePreference(String key, String value) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void storePreference(String key, int value) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void storePreference(String key, long value) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void removePreference(String key) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removePreferences(String keys[]) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.commit();
    }

    public static void clearPrefernces() {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void storePreference(String key, Boolean value) {
        SharedPreferences.Editor editor = GlintApplication.sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static HashMap<String, String> getPreference(String[] keys) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        for (String key : keys) {
            parameters.put(key, GlintApplication.sharedPref.getString(key, null));
        }
        return parameters;
    }


    public static String getPreference(String key, String defValue) {
        return GlintApplication.sharedPref.getString(key, defValue);
    }

    public static long getPreference(String key, long defValue) {
        return GlintApplication.sharedPref.getLong(key, defValue);
    }

    public static int getPreference(String key, int defValue) {
        return GlintApplication.sharedPref.getInt(key, defValue);
    }

    public static Boolean getPreference(String key, Boolean defValue) {
        return GlintApplication.sharedPref.getBoolean(key, defValue);
    }

    public static void fileLog(String filename, String output) {
        try {
            File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.write(output);
            myOutWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateMD5(String inputString) {
        Log.v("MD5 InputString : ", inputString);
        try {
            MessageDigest digest;
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(inputString.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder md5String = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                md5String.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                md5String.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return md5String.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkEmail(String email) {
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static void hideSoftKeyboard(Context context, EditText editText) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public static void setTextWatcher(final Context context, EditText editText, final TextView textView) {

        TextWatcher textWatcher = new TextWatcher() {
            boolean flag = false;

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                if (arg0.length() == 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {

                if (s.length() == 0) {
                    Animation slidedown = AnimationUtils.loadAnimation(context, R.anim.hint_slide_down);
                    textView.startAnimation(slidedown);
                    textView.setVisibility(View.INVISIBLE);
                } else {
                    if (s.length() == 1 && flag) {
                        Animation slideup = AnimationUtils.loadAnimation(context, R.anim.hint_slide_up);
                        textView.startAnimation(slideup);
                    }
                    textView.setVisibility(View.VISIBLE);
                }
            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    public static void changeViewFont(Context mContext, View... views) {
        Typeface tfHelveticaNeueRegular = Typeface.createFromAsset(mContext.getAssets(), Constants.FontRegular);

        Typeface tfHelveticaNeueBold = Typeface.createFromAsset(mContext.getAssets(), Constants.FontBold);

        Typeface tfCurrView;
        for (View view : views) {
            if (view instanceof TextView) {
                tfCurrView = ((TextView) view).getTypeface();
                if (tfCurrView != null && tfCurrView.isBold()) {
                    ((TextView) view).setTypeface(tfHelveticaNeueBold);
                } else {
                    ((TextView) view).setTypeface(tfHelveticaNeueRegular);
                }
            } else if (view instanceof Button) {
                tfCurrView = ((Button) view).getTypeface();
                if (tfCurrView != null && tfCurrView.isBold()) {
                    ((Button) view).setTypeface(tfHelveticaNeueBold);
                } else {
                    ((Button) view).setTypeface(tfHelveticaNeueRegular);
                }
            } else if (view instanceof EditText) {
                tfCurrView = ((EditText) view).getTypeface();
                if (tfCurrView != null && tfCurrView.isBold()) {
                    ((EditText) view).setTypeface(tfHelveticaNeueBold);
                } else {
                    ((EditText) view).setTypeface(tfHelveticaNeueRegular);
                }
            }
        }

    }


    // Progress Indicator
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(Context context, final boolean show, final View statusView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);

            statusView.setVisibility(View.VISIBLE);
            statusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).translationX(0.5f).translationY(1.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            statusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            statusView.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }

    public static String streamToString(InputStream is) throws IOException {
        StringBuilder strBuilder = null;
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 8 * 1024);
        try {
            strBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                strBuilder.append(line);
                // w.write(line);
                System.gc();
                Runtime.getRuntime().gc();
            }
        } catch (Exception e) {
            Log.d("Exception", "" + e);
        }
        return strBuilder.toString();
    }

    @SuppressWarnings("deprecation")
    public static String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static Bitmap scaleImage(Context context, Uri photoUri, int maxSize, Bitmap srcBitmap) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }


		/*
         * if the orientation is not 0 (or -1, which means we don't know), we
		 * have to do a rotation.
		 */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            int w = srcBitmap.getWidth();
            int h = srcBitmap.getHeight();

            if (w < maxSize || h < maxSize) {
                srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix,
                        true);
            } else {
                float ratio = 0;

                if (srcBitmap.getWidth() < srcBitmap.getHeight()) {
                    ratio = (float) (srcBitmap.getWidth() / (float) maxSize);
                } else {
                    ratio = (float) (srcBitmap.getHeight() / (float) maxSize);
                }

                int width = Math.round((float) srcBitmap.getWidth() / ratio);
                int height = Math.round((float) srcBitmap.getHeight() / ratio);

                srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
            }
        } else {
            Matrix matrix = new Matrix();

            int w = srcBitmap.getWidth();
            int h = srcBitmap.getHeight();

            if (w < maxSize || h < maxSize) {
                srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
            } else {
                float ratio = 0;

                if (srcBitmap.getWidth() < srcBitmap.getHeight()) {
                    ratio = (float) (srcBitmap.getWidth() / (float) maxSize);
                } else {
                    ratio = (float) (srcBitmap.getHeight() / (float) maxSize);
                }

                int width = Math.round((float) srcBitmap.getWidth() / ratio);
                int height = Math.round((float) srcBitmap.getHeight() / ratio);

                srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
            }
        }

        return srcBitmap;
    }


    public static float getRatio(int maxSize, int width, int height) {
        float ratio = 0;

        try {
            if (width < maxSize || height < maxSize) {
                return ratio;
            } else {

                if (width < height) {
                    ratio = (float) (width / (float) maxSize);
                } else {
                    ratio = (float) (height / (float) maxSize);
                }

                return ratio;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ratio;
    }

    public static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static int getGelleryOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

//    public static Bitmap resizeMyCapturedImage(Context context, Uri fileUri, String profilePath, Bitmap srcBitmap,
//                                               int maxSize) {
//        Bitmap resized = null;
//        Bitmap tempBitmap = null;
//        try {
//            tempBitmap = srcBitmap;
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            tempBitmap.compress(Bitmap.CompressFormat.JPEG, 55, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            int rotate = 0;
//
//            rotate = getCameraRotateOrientation(context, fileUri, profilePath);
//
//            if (rotate != 0) {
//                switch (rotate) {
//                    case 90:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_90);
//                        break;
//                    case 180:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_180);
//                        break;
//                    case 270:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_270);
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            float ratio = 0;
//            ratio = getRatio(maxSize, tempBitmap.getWidth(), tempBitmap.getHeight());
//            if (ratio == 0) {
//                return tempBitmap;
//
//            } else {
//                ByteArrayOutputStream nStream = new ByteArrayOutputStream();
//                tempBitmap.compress(Bitmap.CompressFormat.JPEG, 55, nStream);
//                byte[] nByteArray = nStream.toByteArray();
//
//                int width = Math.round((float) tempBitmap.getWidth() / ratio);
//                int height = Math.round((float) tempBitmap.getHeight() / ratio);
//
//                resized = resize(nByteArray, width, height);
//                tempBitmap.recycle();
//                tempBitmap = null;
//                System.gc();
//                return resized;
//
//            }
//
//        } catch (Exception e) {
//            // TODO: handle exception
//        } catch (OutOfMemoryError e) {
//            // TODO: handle exception
//            return null;
//        }
//        return resized;
//    }
//
//    public static Bitmap resizeMyGalleryImage(Context context, Uri photoUri, int maxSize, Bitmap srcBitmap) {
//        Bitmap resized = null;
//        Bitmap tempBitmap = null;
//        try {
//            tempBitmap = srcBitmap;
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            tempBitmap.compress(Bitmap.CompressFormat.JPEG, 55, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            long lengthbmp = byteArray.length;
//
//
//            int rotate = 0;
//
//            rotate = getGelleryOrientation(context, photoUri);
//
//            if (rotate != 0) {
//                switch (rotate) {
//                    case 90:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_90);
//                        break;
//                    case 180:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_180);
//                        break;
//                    case 270:
//                        tempBitmap = rotate(byteArray, ImageRotation.CW_270);
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            float ratio = 0;
//            ratio = getRatio(maxSize, tempBitmap.getWidth(), tempBitmap.getHeight());
//            if (ratio == 0) {
//                resized = tempBitmap;
//                return resized;
//
//            } else {
//                ByteArrayOutputStream nStream = new ByteArrayOutputStream();
//                tempBitmap.compress(Bitmap.CompressFormat.JPEG, 55, nStream);
//                byte[] nByteArray = nStream.toByteArray();
//
//                int width = Math.round((float) tempBitmap.getWidth() / ratio);
//                int height = Math.round((float) tempBitmap.getHeight() / ratio);
//
//                resized = resize(nByteArray, width, height);
//
//                return resized;
//            }
//
//        } catch (Exception e) {
//
//        } catch (OutOfMemoryError e) {
//            // TODO: handle exception
//
//            return null;
//        }
//
//        return resized;
//    }

    public static int getCameraRotateOrientation(Context context, Uri fileUri, String profilePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(fileUri, null);
            File imageFile = new File(profilePath);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rotate;
    }

    public static String storeImageGallery(Bitmap imageToSave, Context context) {

        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String filenm = "ITP" + timeStamp + ".jpg";

        OutputStream output;

        // Retrieve the image from the res folder

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/" + Constants.MediaFolderName + "/");
        dir.mkdirs();

        // Create a name for the saved image
        File file = new File(dir, filenm);

        // Show a toast message on successful save
        try {

            output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            // imageToSave.compress(Bitmap.CompressFormat.PNG, 100, output);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 55, output);

            output.flush();
            output.close();
            //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return file.getAbsolutePath();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    public static String storeImageNotDisplay(Bitmap imageToSave, Context context) {

        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String filenm = "ITP" + timeStamp + ".jpg";

        OutputStream output;

        // Retrieve the image from the res folder

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/" + Constants.MediaFolderName + "/");
        dir.mkdirs();

        // Create a name for the saved image
        File file = new File(dir, filenm);

        // Show a toast message on successful save
        try {

            output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            // imageToSave.compress(Bitmap.CompressFormat.PNG, 100, output);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 55, output);

            output.flush();
            output.close();
            //		imageToSave.recycle();
            //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
            //	context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return file.getAbsolutePath();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}
