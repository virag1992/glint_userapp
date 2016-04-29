package app.glintcarwash.com.glintapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import global.global.Utils;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;
import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;

public class MapsActivity extends ActivityManagePermission implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, LocationListener,
        GoogleMap.OnCameraChangeListener {


    private static LatLng ABAD = new LatLng(33.8887635, -117.924707);
    // private static final LatLng LONDON = new LatLng(28.867988, 70.654545);
    // private static final LatLng AFG = new LatLng(29.867988, 70.456465);
    private GoogleMap mMap;
    private Marker m1;
    double mlat;
    private double mlon;
    EditText edtAddress;
    TextView txtHeaderTitle;
    Button btnDone;
    ImageView btnSearch;
    RelativeLayout rl_addressBar;
    String lat, lon, user_address;
    int show;
    double p_lat, p_lon;

    public static boolean mMapIsTouched = false;

    public  boolean doCAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rl_addressBar = (RelativeLayout) findViewById(R.id.rl_addressBar);
        btnDone = (Button) findViewById(R.id.btnDone);
        setUpMapIfNeeded();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            lat = b.getString("lat");
            lon = b.getString("lang");
            p_lat = Double.parseDouble(lat);
            p_lon = Double.parseDouble(lon);
            show = b.getInt("show_all");
            user_address = b.getString("address");
            if (show == 1) {
                rl_addressBar.setVisibility(View.VISIBLE);
                btnDone.setVisibility(View.VISIBLE);
            } else {
                rl_addressBar.setVisibility(View.GONE);
                btnDone.setVisibility(View.GONE);
            }
        }
        askPermissions(new String[]{PermissionUtils.Manifest_ACCESS_FINE_LOCATION, PermissionUtils.Manifest_ACCESS_COARSE_LOCATION, PermissionUtils.Manifest_CAMERA})
                .setPermissionResult(new PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        doCAll = true;
                        //permission granted
                        //replace with your action
//                                            mMap.setMyLocationEnabled(true);
                        Toast.makeText(getApplicationContext(),"Please accept permission.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void permissionNotGranted() {
                        doCAll = false;
                        //permission denied
                        //replace with your action
                    }
                })
                .requestPermission(PermissionUtils.KEY_ACCESS_COARSE_LOCATION);

        edtAddress = (EditText) findViewById(R.id.edtAddress);
        txtHeaderTitle = (TextView) findViewById(R.id.txtHeaderTitle);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String address = edtAddress.getText().toString();
                if (address != null && address.length() > 0) {
                    Address add = Utils.getLocationFromAddress(address,
                            MapsActivity.this);
                    if (add != null) {
                        LatLng hd = new LatLng(add.getLatitude(), add
                                .getLongitude());
                        mlat = add.getLatitude();
                        mlon = add.getLongitude();
                        LatLngBounds bounds = new LatLngBounds.Builder()
                                .include(hd).build();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(
                                bounds, 50));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        mMap.animateCamera(CameraUpdateFactory.scrollBy(Utils
                                        .ConvertToFloat(String.valueOf(hd.latitude)),
                                Utils.ConvertToFloat(String
                                        .valueOf(hd.longitude))));
                    }
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
        if(doCAll) {
            Location l = Utils.getCurrentLocation(this);
            if (l != null) {
                if (p_lat != 0 && p_lon != 0) {
                    ABAD = new LatLng(p_lat, p_lon);
                } else {
                    ABAD = new LatLng(l.getLatitude(), l.getLongitude());
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the
        // map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        // addMarker();
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        final View mapView = getFragmentManager().findFragmentById(R.id.map)
                .getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressWarnings("deprecation")
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                                mapView.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            } else {
                                mapView.getViewTreeObserver()
                                        .removeOnGlobalLayoutListener(this);
                            }
                            LatLngBounds bounds = new LatLngBounds.Builder()
                                    .include(ABAD).build();
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngBounds(bounds, 50));








//                            if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                // TODO: Consider calling
//                                //    ActivityCompat#requestPermissions
//                                // here to request the missing permissions, and then overriding
//                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                //                                          int[] grantResults)
//                                // to handle the case where the user grants the permission. See the documentation
//                                // for ActivityCompat#requestPermissions for more details.
//                                mMap.setMyLocationEnabled(true);
//                                return;
//                            }

                            // mMap.animateCamera(CameraUpdateFactory
                            // .newLatLngZoom(ABAD, 15.0f));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                            //
                        }
                    });
        }
        mMap.setOnCameraChangeListener(this);
    }

    private void addMarker() {
        m1 = mMap.addMarker(new MarkerOptions()
                .position(ABAD)
                .title("401 West 2nd St")
                .snippet("San Bernardino, CA 92401")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        m1.showInfoWindow();
    }

//    public HttpClient getHttpClient() {
//        final HttpParams httpParams = new BasicHttpParams();
//        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
//        HttpClient httpclient = new DefaultHttpClient(httpParams);
//        return httpclient;
//    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        final View l_custom;

        @Override
        public View getInfoWindow(Marker marker) {
            customPopup(marker);
            return l_custom;
        }

        CustomInfoWindowAdapter() {
            l_custom = View.inflate(getApplicationContext(),
                    R.layout.custom_info_window, null);
        }

        private void customPopup(Marker marker) {
            TextView tv = (TextView) l_custom.findViewById(R.id.title);
            TextView snipest = (TextView) l_custom.findViewById(R.id.snippet);
            tv.setText(marker.getTitle());
            snipest.setText(marker.getSnippet());
        }

        @Override
        public View getInfoContents(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onCameraChange(CameraPosition arg0) {


        if (arg0 != null) {
            LatLng newlat = arg0.target;
            if (newlat != null) {
                if (p_lat != 0 && p_lon != 0) {
                    Utils.Instance().getAddressFromGoogle(p_lat, p_lon,
                            new Utils.AddressCallDelegate() {

                                @Override
                                public void CallSuccessFull(String add) {

                                    if (add != null) {
                                        try {
                                            String ad = add;
                                            if (add.contains("\n")) {
                                                ad = add.replace("\n", " ");
                                            }
                                            Utils.LogInfo("Address----->" + ad);
                                            edtAddress.setText(user_address);
                                        } catch (Exception e) {
                                        }
                                    }
                                }

                                @Override
                                public void CallFailed(String error) {
                                    edtAddress.setText(user_address);

                                }

                            });
                }
                mlat = newlat.latitude;
                mlon = newlat.longitude;
                Utils.Instance().getAddressFromGoogle(newlat.latitude,
                        newlat.longitude, new Utils.AddressCallDelegate() {

                            @Override
                            public void CallSuccessFull(String add) {
                                if (add != null) {
                                    try {
                                        String ad = add;
                                        if (add.contains("\n")) {
                                            ad = add.replace("\n", " ");
                                        }
                                        Utils.LogInfo("Address----->" + ad);
                                        edtAddress.setText(user_address);
                                    } catch (Exception e) {
                                    }
                                }

                            }

                            @Override
                            public void CallFailed(String error) {
                                edtAddress.setText(user_address);
                            }
                        });

            }
        }



    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
