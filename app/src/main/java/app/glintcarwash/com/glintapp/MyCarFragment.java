package app.glintcarwash.com.glintapp;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import global.Constants;
import global.ProgressWheel;
import global.Utils;
import model.CarInfo;

/**
 * Created by ACER on 05-04-2016.
 */
public class MyCarFragment extends Fragment {
    Dialog DialogForTransaction;
    ProgressWheel progressWheel;
    ArrayList<Integer> m_draw = new ArrayList<Integer>();
    ListView lstCars;

    public static MyCarFragment getInstance() {
        MyCarFragment fragment = new MyCarFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mycars, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_draw.add(Color.parseColor("#779ECB"));
        m_draw.add(Color.parseColor("#FFD1DC"));
        m_draw.add(Color.parseColor("#FFB347"));
        m_draw.add(Color.parseColor("#03C03C"));
        m_draw.add(Color.parseColor("#FDFD96"));
        lstCars = (ListView) view.findViewById(R.id.lstMyCar);
        ArrayList<CarInfo> m_temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CarInfo car = new CarInfo();
            car.CarName = "Audi " + i;
            car.CarNumber = "ABC - 421 " + i;
            car.CarModel = "White Wagon " + i;
            car.CarColor = "White " + i;
            m_temp.add(car);
        }
        CustomAdapter adapter = new CustomAdapter(getActivity(), m_temp);
        lstCars.setAdapter(adapter);
    }

    public void showProgress() {
//    i = 0;
        if (DialogForTransaction == null) {
            DialogForTransaction = new Dialog(getActivity());

            DialogForTransaction.requestWindowFeature(Window.FEATURE_NO_TITLE);
            DialogForTransaction.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));
            DialogForTransaction.getWindow().setLayout(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            View v1 = getActivity().getLayoutInflater().inflate(R.layout.progress_dialog,
                    null);
            progressWheel = (ProgressWheel) v1
                    .findViewById(R.id.progress_wheel);
            final TextView txtMessage = (TextView) v1
                    .findViewById(R.id.txtMessage);
//        txtMessage.setText(m_str.get(getRandom()));
            progressWheel.setBarColor(Color.BLUE);
            progressWheel.setCallback(new ProgressWheel.ProgressCallback() {
                @Override
                public void onProgressUpdate(float progress) {
                    if (progress == -1.0) {
                        try {
                            progressWheel.setBarColor(m_draw.get(getRandom()));

//                        if (i % 3 == 0) {
//                            txtMessage.setText(m_str.get(getRandom()));
//                        }
//                        i++;
                            // Thread.sleep(2000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            DialogForTransaction.setContentView(v1);
            DialogForTransaction.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            DialogForTransaction.setCancelable(false);
            DialogForTransaction.show();
        } else {
            DialogForTransaction.show();
        }
    }

    public int getRandom() {
        int min = 0;
        int max = 4;
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }

    public void hideProgress() {
        if (DialogForTransaction != null) {
            DialogForTransaction.dismiss();
            DialogForTransaction = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
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
