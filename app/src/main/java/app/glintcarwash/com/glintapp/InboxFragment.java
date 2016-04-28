package app.glintcarwash.com.glintapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InboxFragment extends Fragment {
    ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.inbox_fragment,container,false);
        listView = (ListView) v.findViewById(R.id.listView);
        ListViewCustomAdapter adapter = new ListViewCustomAdapter(getActivity());
        listView.setAdapter(adapter);
        return v;
    }



    public class ListViewCustomAdapter extends BaseAdapter {
        public Activity context;
        LayoutInflater inflater;

        public ListViewCustomAdapter(Activity context)

        {
            super();
            this.context = context;
            this.inflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 15;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        public class Holder {
            TextView title1;
            TextView title2;
            RelativeLayout rlMain;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub

            Holder hv;

            if (arg1 == null) {
                hv = new Holder();
                arg1 = inflater.inflate(R.layout.inbox_custom_item, null);
                hv.title1 = (TextView) arg1.findViewById(R.id.txtTile1);
                hv.title2 = (TextView) arg1.findViewById(R.id.txtTile2);
                hv.rlMain = (RelativeLayout) arg1.findViewById(R.id.rlMain);
                arg1.setTag(hv);
            } else {
                hv = (Holder) arg1.getTag();
            }

            hv.rlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent start = new Intent(getActivity(),ChatBubbleActivity.class);
                    startActivity(start);
                }
            });

            return arg1;
        }

    }
}
