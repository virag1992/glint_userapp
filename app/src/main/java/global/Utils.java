package global;

/**
 * Created by Dharmesh on 9/3/2015.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Utils {

    private Context _context;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

	/*
	 * Reading file paths from SDCard
	 */

    @SuppressLint("NewApi")
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }
    @SuppressWarnings("unused")
    private boolean IsSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
                filePath.length());
        List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg",
                "png");
        if (FILE_EXTN
                .contains(ext.toLowerCase(Locale.getDefault())))
            return true;
        else
            return false;

    }

}
