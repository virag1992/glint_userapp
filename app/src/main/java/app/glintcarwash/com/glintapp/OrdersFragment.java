package app.glintcarwash.com.glintapp;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import global.ProgressWheel;
import model.OrderInfo;

/**
 * Created by ACER on 05-04-2016.
 */
public class OrdersFragment extends Fragment {
    Dialog DialogForTransaction;
    ProgressWheel progressWheel;
    ArrayList<Integer> m_draw = new ArrayList<Integer>();
    ListView lstOrders;

    public static OrdersFragment getInstance() {
        OrdersFragment fragment = new OrdersFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_order, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_draw.add(Color.parseColor("#779ECB"));
        m_draw.add(Color.parseColor("#FFD1DC"));
        m_draw.add(Color.parseColor("#FFB347"));
        m_draw.add(Color.parseColor("#03C03C"));
        m_draw.add(Color.parseColor("#FDFD96"));
        lstOrders = (ListView) view.findViewById(R.id.lstOrders);
        ArrayList<OrderInfo> m_temp = new ArrayList<OrderInfo>();
        for (int i = 0; i < 4; i++) {
            OrderInfo car = new OrderInfo();
            car.OrderTitle = "Car was job " + i + 1;
            car.OrderDate = "Feb 15, 2016";
            car.OrderAddress = "A - 607, Titenium city center, Anand nagar main road, Ahmedabad";
            if (i == 0) {
                car.OrderStatus = "In Progress";
            } else if (i == 1) {
                car.OrderStatus = "Cancel";
            } else if (i == 2) {
                car.OrderStatus = "Completed";
            } else if (i == 3) {
                car.OrderStatus = "Pending";
            }
            m_temp.add(car);
        }
        CustomAdapter adapter = new CustomAdapter(getActivity(), m_temp);
        lstOrders.setAdapter(adapter);
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
            convertView = layoutInflater.inflate(R.layout.order_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtOrderTitle = (TextView) convertView.findViewById(R.id.txtOrderTitle);
            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtOrderStatus);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewHolder.txtLocation = (TextView) convertView.findViewById(R.id.txtAddress);
            viewHolder.imgStatus = (ImageView) convertView.findViewById(R.id.imgOrderStatus);
            convertView.setTag(viewHolder);
            final OrderInfo obj = getItem(position);
            viewHolder.txtOrderTitle.setText(obj.OrderTitle);
            viewHolder.txtStatus.setText(obj.OrderStatus);
            viewHolder.txtDate.setText(obj.OrderDate);
            viewHolder.txtLocation.setText(obj.OrderAddress);
            if (obj.OrderStatus.equalsIgnoreCase("In Progress")) {
                viewHolder.imgStatus.setImageResource(R.drawable.progress);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent color_picker = new Intent(getActivity(), OrderDetailActivity.class);
                        color_picker.putExtra("TYPE","In Progress");
                        startActivity(color_picker);
                    }
                });
            } else if (obj.OrderStatus.equalsIgnoreCase("Completed")) {
                viewHolder.imgStatus.setImageResource(R.drawable.done);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent color_picker = new Intent(getActivity(), OrderDetailActivity.class);
                        color_picker.putExtra("TYPE","Completed");
                        startActivity(color_picker);
                    }
                });
            } else if (obj.OrderStatus.equalsIgnoreCase("Cancel")) {
                viewHolder.imgStatus.setImageResource(R.drawable.cancle);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent color_picker = new Intent(getActivity(), OrderDetailActivity.class);
                        color_picker.putExtra("TYPE","Cancel");
                        startActivity(color_picker);
                    }
                });
            } else if (obj.OrderStatus.equalsIgnoreCase("Pending")) {
                viewHolder.imgStatus.setImageResource(R.drawable.pending);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent color_picker = new Intent(getActivity(), PendigOrderActivity.class);
                        color_picker.putExtra("TYPE","Pending");
                        startActivity(color_picker);
                    }
                });
            }


            return convertView;
        }


        protected class ViewHolder {
            private TextView txtOrderTitle, txtStatus, txtDate, txtLocation;
            ImageView imgStatus;
        }
    }
}