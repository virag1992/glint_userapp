package global;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import app.glintcarwash.com.glintapp.R;

/**
 * Created by Dharmesh on 9/12/2015.
 */
public class AnimUtils {
    public static void listItemAnimation(View row){
        YoYo.with(Techniques.FadeInUp)
                .duration(100)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(row);
    }
    public static void gridItemAnimation(View row){
        YoYo.with(Techniques.FadeInUp)
                .duration(100)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(row);
    }
    public static void activityenterAnim(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_left);

    }

    public static void activityexitAnim(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_right);
    }
}
