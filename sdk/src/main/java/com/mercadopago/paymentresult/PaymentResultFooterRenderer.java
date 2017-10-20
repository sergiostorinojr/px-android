package com.mercadopago.paymentresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultFooterRenderer extends Renderer<PaymentResultFooterComponent> {

    private View footerView;
    private TextView textView;

    @Override
    public View render() {
        textView.setText(component.text);
        return footerView;
    }

    @Override
    protected void bindViews(Context context) {
        footerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_footer, null, false);
        textView = (TextView) footerView.findViewById(R.id.footerText);
    }
}
