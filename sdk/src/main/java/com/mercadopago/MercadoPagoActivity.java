package com.mercadopago;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.mercadopago.callbacks.FailureRecovery;
import com.mercadopago.model.DecorationPreference;
import com.mercadopago.model.PaymentPreference;
import com.mercadopago.util.JsonUtil;

public abstract class MercadoPagoActivity extends AppCompatActivity {

    private boolean mActivityActive;
    private String mMerchantPublicKey;
    protected String mMerchantBaseUrl;
    protected String mMerchantGetCustomerUri;
    protected String mMerchantCreatePaymentUri;
    protected String mMerchantAccessToken;
    protected DecorationPreference mDecorationPreference;
    protected PaymentPreference mPaymentPreference;
    private FailureRecovery mFailureRecovery;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityParameters();
        if(mDecorationPreference != null && mDecorationPreference.hasColors()) {
            setTheme(R.style.Theme_MercadoPagoTheme_NoActionBar);
        }
        setActivity();
        mActivityActive = true;
        setContentView();
        try {
            validateActivityParameters();
            initializeControls();
            onValidStart();
        } catch (IllegalStateException exception) {
            onInvalidStart(exception.getMessage());
        }
    }

    private void setActivity() {
        mActivity = this;
    }

    protected Activity getActivity() {
       return mActivity;
    }

    protected String getMerchantPublicKey() {
        return mMerchantPublicKey;
    }

    protected abstract void onValidStart();

    protected abstract void onInvalidStart(String message);

    protected abstract void setContentView();

    protected abstract void initializeControls();

    @CallSuper
    protected void getActivityParameters() {
        if(getIntent().getStringExtra("paymentPreference") != null) {
            mPaymentPreference = JsonUtil.getInstance().fromJson(getIntent().getStringExtra("paymentPreference"), PaymentPreference.class);
        }
        if(getIntent().getStringExtra("decorationPreference") != null) {
            mDecorationPreference = JsonUtil.getInstance().fromJson(getIntent().getStringExtra("decorationPreference"), DecorationPreference.class);
        }
        mMerchantPublicKey = getIntent().getStringExtra("merchantPublicKey");
        mMerchantBaseUrl = getIntent().getStringExtra("merchantBaseUrl");
        mMerchantGetCustomerUri = getIntent().getStringExtra("getCustomerUri");
        mMerchantCreatePaymentUri = getIntent().getStringExtra("createPaymentUri");
        mMerchantAccessToken = getIntent().getStringExtra("merchantAccessToken");
    };

    protected abstract void validateActivityParameters() throws IllegalStateException;

    @Override
    protected void onResume() {
        mActivityActive = true;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mActivityActive = false;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mActivityActive = false;
        super.onPause();
    }

    @Override
    protected void onStop() {
        mActivityActive = false;
        super.onStop();
    }

    protected boolean isActivityActive() {
        return mActivityActive;
    }

    protected void setFailureRecovery(FailureRecovery failureRecovery) {
        this.mFailureRecovery = failureRecovery;
    }

    protected void recoverFromFailure() {
        if (mFailureRecovery != null) {
            mFailureRecovery.recover();
        }
    }

    protected boolean isCustomColorSet() {
        return mDecorationPreference != null && mDecorationPreference.hasColors();
    }

    protected int getCustomBaseColor() {
        return mDecorationPreference.getBaseColor();
    }

    protected boolean isDarkFontEnabled() {
        return mDecorationPreference != null && mDecorationPreference.isDarkFontEnabled();
    }

    protected int getDarkFontColor() {
        return mDecorationPreference.getDarkFontColor(this);
    }

    protected void decorate(Button button) {
        if(isCustomColorSet()) {
            button.setBackgroundColor(getCustomBaseColor());
        }

        if(isDarkFontEnabled()) {
            button.setTextColor(getDarkFontColor());
        }
    }

    protected void decorate(Toolbar toolbar) {
        if(toolbar != null) {
            if (isCustomColorSet()) {
                toolbar.setBackgroundColor(getCustomBaseColor());
            }

            if (isDarkFontEnabled()) {
                int darkFont = getDarkFontColor();
                Drawable upArrow = toolbar.getNavigationIcon();
                if (upArrow != null) {
                    upArrow.setColorFilter(darkFont, PorterDuff.Mode.SRC_ATOP);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    }
                }
            }
        }
    }

    protected void decorate(TextView textView) {
        if(textView != null) {
            if (isDarkFontEnabled()) {
                textView.setTextColor(getDarkFontColor());
            }
        }
    }
}