package com.mercadopago.paymentresult.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.paymentresult.components.PaymentResultHeaderComponent;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultHeaderRenderer extends Renderer<PaymentResultHeaderComponent> {

    private View headerView;
    private FrameLayout headerContainer;
    private TextView statusTextView;

    @Override
    public View render() {
        headerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_header, null, false);
        headerContainer = (FrameLayout) headerView.findViewById(R.id.mpsdkPaymentResultContainerHeader);
        statusTextView = (TextView) headerView.findViewById(R.id.mpsdkHeaderStatus);

        renderHeight();
        statusTextView.setText(component.getProps().status);
        return headerView;
    }

    private void renderHeight() {
        if (component.getProps().height.equals("wrap")) {
            wrapHeight(headerContainer);
        } else if (component.getProps().height.equals("stretch")) {
            stretchHeight(headerContainer);
        }
    }
}
