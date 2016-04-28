package app.glintcarwash.com.glintapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.CarInfo;

/**
 * Created by ACER on 29-04-2016.
 */
public class MyCarListActivity extends BaseActivity implements View.OnClickListener {

    public Toolbar toolbar;
    ListView lstCars;
    Button btnAddCar;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycars);
        setActionBar();
        initDrawer(savedInstanceState);
        action = getActionBar();
        lstCars = (ListView) findViewById(R.id.lstMyCar);
        btnAddCar = (Button) findViewById(R.id.btnAddCar);
        ArrayList<CarInfo> m_temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CarInfo car = new CarInfo();
            car.CarName = "Audi " + i;
            car.CarNumber = "ABC - 421 " + i;
            car.CarModel = "White Wagon " + i;
            car.CarColor = "White " + i;
            m_temp.add(car);
        }
        CustomAdapter adapter = new CustomAdapter(MyCarListActivity.this, m_temp);
        lstCars.setAdapter(adapter);
        btnAddCar.setOnClickListener(this);
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
            upArrow = getResources().getDrawable(R.drawable.back, MyCarListActivity.this.getTheme());
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
        if (v.equals(btnAddCar)) {
            Intent start = new Intent(MyCarListActivity.this, AddCarActivity.class);
            startActivity(start);
        }
    }

    public class CustomAdapter extends BaseAdapter {

        //    private List<BookmarkObject> objects = new ArrayList<BookmarkObject>();
        private List<CarInfo> objects = new ArrayList<CarInfo>();

        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(Context context, List<CarInfo> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public CarInfo getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.my_car_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtCarName = (TextView) convertView.findViewById(R.id.txtCarName);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.txtCarNumber);
            viewHolder.txtModel = (TextView) convertView.findViewById(R.id.txtCarModelType);
            viewHolder.txtColor = (TextView) convertView.findViewById(R.id.txtCarColor);
            convertView.setTag(viewHolder);
            final CarInfo obj = getItem(position);

            viewHolder.txtCarName.setText(obj.CarName);
            viewHolder.txtNumber.setText(obj.CarNumber);
            viewHolder.txtModel.setText(obj.CarModel);
            viewHolder.txtColor.setText(obj.CarColor);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent color_picker = new Intent(getActivity(),ColorPickerActivity.class);
//                    startActivity(color_picker);
                }
            });
            return convertView;
        }


        protected class ViewHolder {
            private TextView txtCarName, txtNumber, txtModel, txtColor;
        }
    }

}
