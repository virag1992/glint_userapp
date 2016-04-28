package app.glintcarwash.com.glintapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    //    String[] tabNames;
    public TextView tvtitle;
    public Toolbar toolbar;
    public DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence mTitle;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavAdapter adapter;
    Bundle main_bundel;
    int current_item = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        initDrawer(savedInstanceState);
    }

    public void setTitleForAction(String h) {
        tvtitle.setText(h);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenDensity = metrics.densityDpi;
        int h = metrics.heightPixels;
        int w = metrics.widthPixels;
        ViewGroup.LayoutParams p = mDrawerList.getLayoutParams();
        p.width = w - (100 * screenDensity);
        mDrawerList.setLayoutParams(p);
    }

    public void setActionBar() {
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.app_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_action_list));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        tvtitle = (TextView) toolbar.findViewById(R.id.tvtitle);
        //tvtitle.setText(getResources().getString(R.string.home_title));
        Drawable upArrow;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            upArrow = getResources().getDrawable(R.drawable.ic_menu, MainActivity.this.getTheme());
        } else {
            upArrow = getResources().getDrawable(R.drawable.ic_menu);
        }

//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        //upArrow.setColorFilter(Color.parseColor("#33cc90"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }


    @Override
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        toggle.syncState();

    }


    private void initDrawer(Bundle savedInstanceState) {
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
                .getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
                .getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
                .getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
                .getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
                .getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
                .getResourceId(5, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
                .getResourceId(6, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons
                .getResourceId(7, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons
                .getResourceId(8, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons
                .getResourceId(9, -1)));
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons
//                .getResourceId(10, -1)));
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons
//                .getResourceId(9, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
//                setTitleForAction(mTitle.toString());
//                if (mTitle != null) {
//                    tvtitle.setText(mTitle);
//                }
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
//                setTitleForAction(mDrawerTitle.toString());
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                View v = findViewById(R.id.container_body);
                v.setTranslationX(slideOffset * drawerView.getWidth());
                toolbar.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };
        setTitleForAction(mTitle.toString());
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        displayView(1);
    }

    public class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        getSupportActionBar().show();
        tvtitle.setText(navMenuTitles[position]);
        Fragment fragment = null;
        switch (position) {
            case 0:
                SelectImage();
//                fragment = new MyCarFragment();
//                fragment = new ProfileFragment();


                break;
            case 1:
                fragment = new HomeFragment();
                break;
            case 2:
//                getSupportActionBar().hide();
                fragment = new ProfileFragment();
                break;
            case 3:
//                getSupportActionBar().hide();
                fragment = new NotificationFragment();
                break;
//            case 4:
////                getSupportActionBar().hide();
//                fragment = new MyCarFragment();
//                break;
            case 4:
                //if (UserInfo.getUser() != null) {
                fragment = new ReferralFragment();
                // }
                break;
            case 5:
                fragment = new OrdersFragment();
                break;
            case 6:
                //Cart
                fragment = new ContactUsFragment();
                break;
            case 7:
                fragment = new InboxFragment();
                //logout
//                if (UserInfo.getUser() == null) {
//                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
////                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
////                    finish();
//                } else {
//                    Global.showAlertDialog(HomeActivity.this, getResources().getString(R.string.txt_alert_title), getResources().getString(R.string.txt_exit_message), getResources().getString(R.string.txt_yes), getResources().getString(R.string.txt_no), dialogClickListener);
//                }
                break;
            case 8:
//                getSupportActionBar().hide();
                fragment = new HelpFragment();
                break;
            case 9:
//                getSupportActionBar().hide();
//                fragment = new MyCarFragment();
                finish();
                break;
            default:
                break;
        }
        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_body, fragment).commit();
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
//            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
            setTitleForAction(navMenuTitles[position]);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:


                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /*
     * Preparing the list data
     */
    public void SelectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            101);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}