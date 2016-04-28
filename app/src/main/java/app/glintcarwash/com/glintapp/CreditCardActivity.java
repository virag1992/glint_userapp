package app.glintcarwash.com.glintapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.cooltechworks.creditcarddesign.*;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class CreditCardActivity extends BaseActivity {
    public Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card);
        setActionBar();
        initDrawer(savedInstanceState);
        findViewById(R.id.add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditCardActivity.this, CardEditActivity.class);
                startActivityForResult(intent, 0);
            }
        });
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
            upArrow = getResources().getDrawable(R.drawable.back, CreditCardActivity.this.getTheme());
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

    private void initListener(final int index, CreditCardView creditCardView) {


        creditCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               CreditCardView creditCardView = (CreditCardView) v;
                String cardNumber = creditCardView.getCardNumber();
                String expiry = creditCardView.getExpiry();
                String cardHolderName = creditCardView.getCardHolderName();

                Intent intent = new Intent(CreditCardActivity.this, CardEditActivity.class);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME, cardHolderName);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_NUMBER, cardNumber);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_EXPIRY, expiry);
                intent.putExtra(CreditCardUtils.EXTRA_CARD_SHOW_CARD_SIDE, CreditCardUtils.CARD_SIDE_FRONT);

                startActivityForResult(intent, index);

            }
        });

    }



    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {

            LinearLayout cardContainer = (LinearLayout) findViewById(R.id.card_container);


            String name = data.getStringExtra(com.cooltechworks.creditcarddesign.CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(com.cooltechworks.creditcarddesign.CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(com.cooltechworks.creditcarddesign.CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);


            if(reqCode == 0) {


                CreditCardView creditCardView = new CreditCardView(this);

                creditCardView.setCVV(cvv);
                creditCardView.setCardHolderName(name);
                creditCardView.setCardExpiry(expiry);
                creditCardView.setCardNumber(cardNumber);

                int index = cardContainer.getChildCount();
                cardContainer.addView(creditCardView);
                initListener(index, creditCardView);



            }
            else {

                CreditCardView creditCardView = (CreditCardView) cardContainer.getChildAt(reqCode);

                creditCardView.setCardExpiry(expiry);
                creditCardView.setCardNumber(cardNumber);
                creditCardView.setCardHolderName(name);
                creditCardView.setCVV(cvv);

            }
        }


    }

}
