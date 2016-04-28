package app.glintcarwash.com.glintapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ColorPickerActivity extends BaseActivity {
    private GradientView mTop;
    private GradientView mBottom;
    private TextView mTextView;
    private Drawable mIcon;
    Button btnSave;
    String clr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.color_picker, null);
        setContentView(view);
        mIcon = getResources().getDrawable(R.mipmap.ic_launcher);
        mTextView = (TextView)findViewById(R.id.color);
        btnSave = (Button)findViewById(R.id.btnsave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent color_picker = new Intent();
                color_picker.putExtra("color",clr);
                setResult(RESULT_OK, color_picker);
                finish();

            }
        });
        mTextView.setCompoundDrawablesWithIntrinsicBounds(mIcon, null, null, null);
        mTop = (GradientView)findViewById(R.id.top);
        mBottom = (GradientView)findViewById(R.id.bottom);
        mTop.setBrightnessGradientView(mBottom);
        mBottom.setOnColorChangedListener(new GradientView.OnColorChangedListener() {
            @Override
            public void onColorChanged(GradientView view, int color) {
                clr = String.valueOf("#" + Integer.toHexString(color));
                mTextView.setTextColor(color);
                mTextView.setText("#" + Integer.toHexString(color));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mIcon.setTint(color);
                }
            }
        });

        int color = 0xFF394572;
        mTop.setColor(color);
    }
    }

