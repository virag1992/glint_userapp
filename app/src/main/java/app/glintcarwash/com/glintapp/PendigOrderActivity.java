package app.glintcarwash.com.glintapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.OrderInfo;

/**
 * Created by ACER on 11-04-2016.
 */
public class PendigOrderActivity extends BaseActivity implements View.OnClickListener {

    public Toolbar toolbar;
    ListView lstPending;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_order_list);
        setActionBar();
        initDrawer(savedInstanceState);
//        action = getActionBar();
        lstPending = (ListView) findViewById(R.id.lstPending);
        ArrayList<OrderInfo> m_temp = new ArrayList<OrderInfo>();
        for (int i = 0; i < 8; i++) {
            OrderInfo car = new OrderInfo();
            car.OrderTitle = "Car was job " + i + 1;
            car.OrderDate = "Feb 15, 2016";
            car.OrderAddress = "A - 607, Titenium city center, Anand nagar main road, Ahmedabad";
            car.OrderStatus = "In Progress";
            m_temp.add(car);
        }
        CustomAdapter adapter = new CustomAdapter(this, m_temp);
        lstPending.setAdapter(adapter);

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
            upArrow = getResources().getDrawable(R.drawable.back, PendigOrderActivity.this.getTheme());
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

    public class CustomAdapter extends BaseAdapter {

        //    private List<BookmarkObject> objects = new ArrayList<BookmarkObject>();
        private List<OrderInfo> objects = new ArrayList<OrderInfo>();

        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(Context context, List<OrderInfo> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public OrderInfo getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.pending_order_item, null);
            ViewHolder viewHolder = new ViewHolder();
//            viewHolder.txtOrderTitle = (TextView) convertView.findViewById(R.id.txtOrderTitle);
//            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtOrderStatus);
//            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
//            viewHolder.txtLocation = (TextView) convertView.findViewById(R.id.txtAddress);
//            viewHolder.imgStatus = (ImageView) convertView.findViewById(R.id.imgOrderStatus);
            convertView.setTag(viewHolder);
            final OrderInfo obj = getItem(position);
//            viewHolder.txtOrderTitle.setText(obj.OrderTitle);
//            viewHolder.txtStatus.setText(obj.OrderStatus);
//            viewHolder.txtDate.setText(obj.OrderDate);
//            viewHolder.txtLocation.setText(obj.OrderAddress);
//            if (obj.OrderStatus.equalsIgnoreCase("In Progress")) {
//                viewHolder.imgStatus.setImageResource(R.drawable.progress);
//            } else if (obj.OrderStatus.equalsIgnoreCase("Completed")) {
//                viewHolder.imgStatus.setImageResource(R.drawable.done);
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent color_picker = new Intent(getActivity(), OrderDetailActivity.class);
//                        startActivity(color_picker);
//                    }
//                });
//            } else if (obj.OrderStatus.equalsIgnoreCase("Cancel")) {
//                viewHolder.imgStatus.setImageResource(R.drawable.cancle);
//            } else if (obj.OrderStatus.equalsIgnoreCase("Pending")) {
//                viewHolder.imgStatus.setImageResource(R.drawable.pending);
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent color_picker = new Intent(getActivity(), PendigOrderActivity.class);
//                        startActivity(color_picker);
//                    }
//                });
//            }


            return convertView;
        }


        protected class ViewHolder {
            private TextView txtOrderTitle, txtStatus, txtDate, txtLocation;
            ImageView imgStatus;
        }
    }
}