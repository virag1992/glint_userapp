package app.glintcarwash.com.glintapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class LoginActivity extends BaseActivity implements OnClickListener {

    RelativeLayout rlSigninRadioButton, rlsignupRadio, rlSignInView,
            rlSignupview;
    TextView txtSigninLable, txtSigninDiv, txtSigupLable, txtSignupdiv,
            txtForgotPassword;
    Button btnSignin;
    LinearLayout llco;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        rlSigninRadioButton = (RelativeLayout) findViewById(R.id.rlsigninbutton);
        rlsignupRadio = (RelativeLayout) findViewById(R.id.rlsignupbutton);
        rlSignInView = (RelativeLayout) findViewById(R.id.rlSIGNINVIEW);
        rlSignupview = (RelativeLayout) findViewById(R.id.rlSIGNUPVIEW);
        llco = (LinearLayout) findViewById(R.id.llco);
        txtSigninLable = (TextView) findViewById(R.id.txtsignin);
        txtSigninDiv = (TextView) findViewById(R.id.div1);
        txtSigupLable = (TextView) findViewById(R.id.txtSignup);
        txtSignupdiv = (TextView) findViewById(R.id.div2);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        btnSignin = (Button) findViewById(R.id.btnSignIn);

        rlSigninRadioButton.setOnClickListener(this);
        rlsignupRadio.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        btnSignin.setText("SIGN IN");
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        if (v.equals(rlSigninRadioButton)) {
            rlSignInView.setVisibility(View.VISIBLE);
            rlSignupview.setVisibility(View.GONE);
            llco.setVisibility(View.VISIBLE);
            txtSigninDiv.setBackgroundColor(Color.parseColor("#3CB4DA"));
            txtSigninLable.setTextColor(Color.parseColor("#3CB4DA"));
            txtSignupdiv.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtSigupLable.setTextColor(Color.parseColor("#FFFFFF"));
            btnSignin.setText("SIGN IN");
        } else if (v.equals(rlsignupRadio)) {
            rlSignInView.setVisibility(View.GONE);
            rlSignupview.setVisibility(View.VISIBLE);
            llco.setVisibility(View.GONE);
            txtSignupdiv.setBackgroundColor(Color.parseColor("#3CB4DA"));
            txtSigupLable.setTextColor(Color.parseColor("#3CB4DA"));
            txtSigninDiv.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txtSigninLable.setTextColor(Color.parseColor("#FFFFFF"));
            btnSignin.setText("SIGN UP");
        } else if (v.equals(btnSignin)) {
            if (btnSignin.getText().toString().equalsIgnoreCase("SIGN IN")) {
                Intent i = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(LoginActivity.this,
                        VerifyActivity.class);
                startActivity(i);
            }
        } else if (v.equals(txtForgotPassword)) {
            Intent i = new Intent(LoginActivity.this,
                    ForgotPasswordActvity.class);
            startActivity(i);
        }

    }
}
