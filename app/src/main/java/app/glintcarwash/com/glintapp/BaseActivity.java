package app.glintcarwash.com.glintapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class BaseActivity extends AppCompatActivity {
	ProgressDialog m_pd = null;
	// ImageView back;
	// TextView txtSync;
	// ImageView imgSync;
	ProgressBar pbar;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	RelativeLayout rlSync;
	ActionBar action;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		editor = settings.edit();
		m_pd = new ProgressDialog(BaseActivity.this);
		m_pd.setMessage(Html.fromHtml("Please wait..."));
		m_pd.setCancelable(false);

	}

	public void showProgress() {
		if (m_pd != null) {
			m_pd.show();
		}
	}

	public void showProgress(String msg) {
		if (m_pd != null) {
			m_pd.setMessage(Html.fromHtml("Start Sync With Server..."));
			m_pd.show();
		}
	}

	public void hideProgress() {
		if (m_pd != null) {
			m_pd.dismiss();
		}
	}

	// public void ShowActionBar(String Tag) {
	// ActionBar ab = getActionBar();
	// ab.setDisplayShowHomeEnabled(false);
	// ab.setDisplayShowTitleEnabled(false);
	// // getActionBar().setBackgroundDrawable(
	// // new ColorDrawable(getResources().getColor(R.color.black)));
	// final LayoutInflater inflater = (LayoutInflater)
	// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// View view = inflater.inflate(R.layout.custom_actionbar, null);
	// ab.setCustomView(view);
	// ab.setDisplayShowCustomEnabled(true);
	// // if (Tag.equalsIgnoreCase("DASHBOARD")) {
	// // ImageView back = (ImageView) findViewById(R.id.imgBack);
	// // back.setVisibility(View.GONE);
	// // }
	// SigSemiBold text = (SigSemiBold) findViewById(R.id.txtTag);
	// txtSync = (TextView) findViewById(R.id.txtSync);
	// imgSync = (ImageView) findViewById(R.id.imgSync);// pbar
	// rlSync = (RelativeLayout) findViewById(R.id.rlSync);
	// pbar = (ProgressBar) findViewById(R.id.pbar);
	// if (Tag.equalsIgnoreCase("ACTIVE ROUTES")
	// || Tag.equalsIgnoreCase("DAILY MANIFEST")) {
	// txtSync.setVisibility(View.GONE);
	// imgSync.setVisibility(View.VISIBLE);
	// pbar.setVisibility(View.GONE);
	// } else {
	// txtSync.setVisibility(View.GONE);
	// imgSync.setVisibility(View.GONE);
	// pbar.setVisibility(View.GONE);
	// }
	// text.setText(Tag);
	// text.setTextColor(Color.parseColor("#ffffff"));
	// back = (ImageView) findViewById(R.id.imgBack);
	// back.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//
	// onBackPressed();
	//
	// }
	// });
	//
	// }
	//
	// public void back_click() {
	//
	// onBackPressed();
	//
	// }
	//
	// public void AlertBack(String message, String title) {
	// AlertDialog.Builder alt_bld = new AlertDialog.Builder(BaseActivity.this);
	// alt_bld.setMessage(message)
	// .setCancelable(false)
	// .setPositiveButton("Okay",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// dialog.dismiss();
	// }
	// });
	//
	// AlertDialog alert = alt_bld.create();
	// alert.setTitle(title);
	// if (!alert.isShowing()) {
	// alert.show();
	// }
	// }
}
