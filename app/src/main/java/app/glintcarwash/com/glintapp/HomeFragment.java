package app.glintcarwash.com.glintapp;

import android.app.ActionBar;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import global.ProgressWheel;

/**
 * Created by ACER on 05-04-2016.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    Dialog DialogForTransaction;
    ProgressWheel progressWheel;
    ImageView imgCar,imgOrder;
    ArrayList<Integer> m_draw = new ArrayList<Integer>();

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        imgOrder = (ImageView) v.findViewById(R.id.imgOrder);
        imgCar = (ImageView) v.findViewById(R.id.imgCar);
        imgOrder.setOnClickListener(this);
        imgCar.setOnClickListener(this);
        m_draw.add(Color.parseColor("#779ECB"));
        m_draw.add(Color.parseColor("#FFD1DC"));
        m_draw.add(Color.parseColor("#FFB347"));
        m_draw.add(Color.parseColor("#03C03C"));
        m_draw.add(Color.parseColor("#FDFD96"));

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

    @Override
    public void onClick(View v) {
        if(v==imgCar){
            Intent color_picker = new Intent(getActivity(), BookOrderActivity.class);
            startActivity(color_picker);
        }else if(v==imgOrder){
            Intent color_picker = new Intent(getActivity(), BookOrderActivity.class);
            startActivity(color_picker);
        }
    }
}
