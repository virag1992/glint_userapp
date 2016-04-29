package global;

/**
 * Created by Dharmesh on 9/3/2015.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utils {

    private Context _context;
    private static volatile Utils _instance = null;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

    public Utils() {

    }

    public static Utils Instance() {
        if (_instance == null) {
            synchronized (Utils.class) {
                _instance = new Utils();
            }
        }
        return _instance;
    }

    public interface AddressCallDelegate {

        public void CallSuccessFull(String add);

        public void CallFailed(String error);

    }

    public static void LogInfo(String message) {
        Log.i("GLINT", "GLINT-- >" + message);
    }

	/*
     * Reading file paths from SDCard
	 */

    @SuppressLint("NewApi")
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("unused")
    private boolean IsSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
                filePath.length());
        List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg",
                "png");
        if (FILE_EXTN
                .contains(ext.toLowerCase(Locale.getDefault())))
            return true;
        else
            return false;

    }

    public static void LogException(Exception ex) {
        Log.d("GLINT Exception", "GLINT Exception -- > " + ex.getMessage()
                + "\n" + ex);
    }


    public static Address getLocationFromAddress(String Add, Activity act) {
        Geocoder coder = new Geocoder(act);
        List<Address> address;
        // Add =
        // "L D College Of Engineering, 120 Circular Road,University Area,Ahmedabad,Gujarat 380015";
        try {
            address = coder.getFromLocationName(Add, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            return location;
        } catch (Exception e) {
            Utils.LogException(e);
            return null;
        }

    }

    public static float ConvertToFloat(String val) {
        float intval = 0;
        try {
            intval = Float.parseFloat(val);
        } catch (Exception e) {
            Utils.LogException(e);
        }
        return intval;
    }

    /**
     * Convert String to java.utils.Date
     *
     * @param val
     * @return Date
     */
    public static Date ConvertToDate(String val) {
        Date retval = null;
        try {
            if (val == null || val.length() <= 0) {
                return retval;
            }
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            retval = dateformat.parse(val);
        } catch (Exception e) {
            Utils.LogException(e);
        }

        return retval;
    }

    public static Date ConvertToDate(String val, String format) {
        Date retval = null;
        try {
            if (val == null || val.length() <= 0) {
                return retval;
            }
            SimpleDateFormat dateformat = new SimpleDateFormat(format);
            retval = dateformat.parse(val);
        } catch (Exception e) {
            Utils.LogException(e);
        }

        return retval;
    }

    public static Location getCurrentLocation(Context act) {
        CheckGps(act);
        try {
            LocationManager lm = (LocationManager) act
                    .getSystemService(Context.LOCATION_SERVICE);
            boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (!isGPS) {
                Utils.LogInfo("GPS is Not Active ....................");
                Intent intent = new Intent(
                        "android.location.GPS_ENABLED_CHANGE");
                intent.putExtra("enabled", true);
                act.sendBroadcast(intent);
            }
            Location location;
            if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return null;
            }

            location = lm
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location == null) {
                location = lm
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            Utils.LogInfo("LOCATION Lat----->>>" + location.getLatitude()
                    + "  LOCATION Log----->>>" + location.getLongitude());
            return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void CheckGps(final Context act) {
        try {
            LocationManager locationManager = (LocationManager) act
                    .getSystemService(Context.LOCATION_SERVICE);

            if (!locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        act);
                alertDialogBuilder
                        .setMessage(
                                "GPS is disabled in your device. Enable it?")
                        .setCancelable(false)
                        .setPositiveButton("Enable GPS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Intent callGPSSettingIntent = new Intent(
                                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        act.startActivity(callGPSSettingIntent);
                                    }
                                });
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        } catch (Exception ex) {

        }
    }

    public static int getRandomInt() {
        int max = 100;
        int min = 1;
        Random r = new Random();
        int i = r.nextInt(max - min) + min;
        i = i * (-1);
        return i;
    }


    public String getAddressFromGoogle(double lat, double lon,
                                       AddressCallDelegate dele) {
        getAddressAsync load = new getAddressAsync(lat, lon, dele);
        load.execute();
        return "";
    }

    public class getAddressAsync extends AsyncTask<Void, Void, Void> {
        double latitude;
        double longitude;
        String responseString = "";
        AddressCallDelegate delegate;
        String google_address = "Can not get address from google!";

        public getAddressAsync(double lat, double lon, AddressCallDelegate dele) {
            latitude = lat;
            longitude = lon;
            delegate = dele;
        }

        @Override
        protected Void doInBackground(Void... params) {
            String mainurl = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
                    + String.valueOf(latitude)
                    + ","
                    + String.valueOf(longitude) + "&sensor=true";
            HttpURLConnection connection = null;
            InputStream is = null;
            try {
                URL url = new URL(mainurl);

                connection = (HttpURLConnection) url
                        .openConnection(Proxy.NO_PROXY);
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("GET");

                // get input stream response and convert it into String
                is = connection.getInputStream();
                responseString = convertinputStreamToString(new FlushedInputStream(
                        is));

            } catch (Exception e) {
                Utils.LogException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (responseString.length() > 0) {
                try {
                    JSONObject jobj = new JSONObject(responseString);
                    JSONArray arr = jobj.getJSONArray("results");
                    if (arr.length() > 0) {
                        JSONObject first = arr.getJSONObject(0);
                        google_address = first.optString("formatted_address");
                        delegate.CallSuccessFull(google_address);
                    }
                } catch (Exception e) {
                    Utils.LogException(e);
                }
            } else {
                delegate.CallFailed(google_address);
            }
        }

    }

    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < n) {
                long bytesSkipped = in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0L) {
                    int b = read();
                    if (b < 0) {
                        break; // we reached EOF
                    } else {
                        bytesSkipped = 1; // we read one byte
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }

}
