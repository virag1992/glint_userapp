package app.glintcarwash.com.glintapp;

import java.io.File;


import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class PlaceImageDialoug extends Activity {
	String imgUrl;
	ImageView imgPlace, imageCancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.place_image_dialoug);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			imgUrl = b.getString("img");
		}
		imgPlace = (ImageView) findViewById(R.id.imgPlace);
		imageCancle = (ImageView) findViewById(R.id.imageCancle);
		if (imgUrl != null) {
			imgPlace.setImageURI(Uri.parse(new File(imgUrl).toString()));
			// UrlImageViewHelper.setUrlDrawable(imgPlace, imgUrl,
			// new UrlImageViewCallback() {
			// @Override
			// public void onLoaded(ImageView imageView,
			// Bitmap loadedBitmap, String url,
			// boolean loadedFromCache) {
			// }
			// });

		}
	}

	public void cancleClick(View v) {
		onBackPressed();
	}
}
