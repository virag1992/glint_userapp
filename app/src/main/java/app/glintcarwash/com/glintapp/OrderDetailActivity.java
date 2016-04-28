package app.glintcarwash.com.glintapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by ACER on 11-04-2016.
 */
public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    public Toolbar toolbar;
    ImageView imgOrderStatus;
    String type = "";
    Button btnPayment;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            type = b.getString("TYPE");
        }
        setActionBar();
        initDrawer(savedInstanceState);
        action = getActionBar();
        imgOrderStatus = (ImageView) findViewById(R.id.imgOrderStatus);
        btnPayment = (Button) findViewById(R.id.btnPayment);
        if (type.equalsIgnoreCase("In Progress")) {
            btnPayment.setVisibility(View.GONE);
            imgOrderStatus.setImageResource(R.drawable.progress);
        } else if (type.equalsIgnoreCase("Completed")) {
            btnPayment.setVisibility(View.VISIBLE);
            imgOrderStatus.setImageResource(R.drawable.done);
        } else if (type.equalsIgnoreCase("Cancel")) {
            btnPayment.setVisibility(View.GONE);
            imgOrderStatus.setImageResource(R.drawable.cancle);
        }
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                mDrawerLayout.openDrawer(Gravity.LEFT);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBar() {
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.app_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_action_list));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //tvtitle.setText(getResources().getString(R.string.home_title));
        Drawable upArrow;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            upArrow = getResources().getDrawable(R.drawable.back, OrderDetailActivity.this.getTheme());
        } else {
            upArrow = getResources().getDrawable(R.drawable.back);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public void onClick(View v) {


    }
}