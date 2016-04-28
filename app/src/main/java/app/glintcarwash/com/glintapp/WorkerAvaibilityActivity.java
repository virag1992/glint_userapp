package app.glintcarwash.com.glintapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.OrderInfo;

/**
 * Created by ACER on 10-04-2016.
 */
public class WorkerAvaibilityActivity extends BaseActivity {

    public Toolbar toolbar;
    ListView lstWorker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wroker_avability);
        setActionBar();
        initDrawer(savedInstanceState);
        lstWorker = (ListView) findViewById(R.id.lstWorkers);
        ArrayList<String> m_temp = new ArrayList<String>();
        m_temp.add("11:30 AM");
        m_temp.add("12:00 PM");
        m_temp.add("12:30 PM");
        m_temp.add("01:00 PM");
        m_temp.add("01:30 PM");
        m_temp.add("02:00 PM");
        m_temp.add("02:30 PM");
        m_temp.add("03:00 PM");
        m_temp.add("03:30 PM");
        m_temp.add("04:00 PM");
        m_temp.add("04:30 PM");
        m_temp.add("05:00 PM");
        m_temp.add("05:30 PM");
        m_temp.add("06:00 PM");
        CustomAdapter adapter = new CustomAdapter(this, m_temp);
        lstWorker.setAdapter(adapter);
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
            upArrow = getResources().getDrawable(R.drawable.back, WorkerAvaibilityActivity.this.getTheme());
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

    public class CustomAdapter extends BaseAdapter {

        //    private List<BookmarkObject> objects = new ArrayList<BookmarkObject>();
        private List<String> objects = new ArrayList<String>();

        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(Context context, List<String> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public String getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.work_avaibility_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            convertView.setTag(viewHolder);
            final String obj = getItem(position);

            viewHolder.txtTime.setText(obj);
//            viewHolder.txtNumber.setText(obj.CarNumber);
//            viewHolder.txtModel.setText(obj.CarModel);
//            viewHolder.txtColor.setText(obj.CarColor);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent color_picker = new Intent(WorkerAvaibilityActivity.this, ColorPickerActivity.class);
//                    startActivity(color_picker);
                }
            });
            return convertView;
        }


        protected class ViewHolder {
            private TextView txtTime;
        }
    }
}