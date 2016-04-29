package global.global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import app.glintcarwash.com.glintapp.GlintApplication;

public class NetworkConnectivity {

	public static boolean isConnected() {
		try {
			ConnectivityManager manager = (ConnectivityManager) GlintApplication
					.getContext()
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo[] infos = manager.getAllNetworkInfo();

			for (NetworkInfo info : infos) {
				if (info.isConnectedOrConnecting()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

		// try {
		// ConnectivityManager cm = (ConnectivityManager) FieldworkApplication
		// .getContext()
		// .getSystemService(Context.CONNECTIVITY_SERVICE);
		// NetworkInfo netInfo = cm.getActiveNetworkInfo();
		//
		// if (netInfo != null && netInfo.isConnected()) {
		// return true;
		// } else {
		// return false;
		// }
		// } catch (Exception e) {
		// return false;
		// }
	}
}
