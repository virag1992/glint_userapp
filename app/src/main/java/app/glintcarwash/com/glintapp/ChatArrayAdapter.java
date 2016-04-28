package app.glintcarwash.com.glintapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

	private TextView chatText,chatTextRight;
	private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
	private LinearLayout singleMessageContainer,singleMessageContainerRight ;


	@Override
	public void add(ChatMessage object) {
		chatMessageList.add(object);
		super.add(object);
	}

	public ChatArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.chatMessageList.size();
	}

	public ChatMessage getItem(int index) {
		return this.chatMessageList.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final int lstPosition = position;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.activity_chat_singlemessage,
					parent, false);
		}
		singleMessageContainer = (LinearLayout) row
				.findViewById(R.id.singleMessageContainer);
		singleMessageContainerRight = (LinearLayout) row
				.findViewById(R.id.singleMessageContainerRight);
		ChatMessage chatMessageObj = getItem(position);

//			singleMessageContainer.setVisibility(View.VISIBLE);
			chatText = (TextView) row.findViewById(R.id.singleMessage);
		chatTextRight = (TextView) row.findViewById(R.id.singleMessageRight);
			chatText.setText(chatMessageObj.message);
		if(chatMessageObj.left){
			singleMessageContainer.setVisibility(View.VISIBLE);
			singleMessageContainerRight.setVisibility(View.GONE);
			chatText.setText(chatMessageObj.message);
		}else{
			chatTextRight.setText(chatMessageObj.message);
			singleMessageContainer.setVisibility(View.GONE);
			singleMessageContainerRight.setVisibility(View.VISIBLE);

		}
//			chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble_b
//					: R.drawable.bubble_a);
//			singleMessageContainer
//					.setGravity(chatMessageObj.left ? Gravity.LEFT
//							: Gravity.RIGHT);

		return row;
	}



}