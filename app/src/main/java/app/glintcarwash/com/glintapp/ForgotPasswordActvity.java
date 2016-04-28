package app.glintcarwash.com.glintapp;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ForgotPasswordActvity extends BaseActivity implements
		OnClickListener {

	Button btnSignin;
	public Toolbar toolbar;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpassword);
		setActionBar();
		initDrawer(savedInstanceState);
		btnSignin = (Button) findViewById(R.id.btnSend);
		btnSignin.setOnClickListener(this);
		action = getActionBar();
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
			upArrow = getResources().getDrawable(R.drawable.back, ForgotPasswordActvity.this.getTheme());
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