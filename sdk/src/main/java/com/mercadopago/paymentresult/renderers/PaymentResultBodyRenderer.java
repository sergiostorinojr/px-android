package com.mercadopago.paymentresult.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.paymentresult.components.PaymentResultBodyComponent;
import com.mercadopago.util.LayoutUtil;

/**
 * Created by vaserber on 10/23/17.
 */

public class PaymentResultBodyRenderer extends Renderer<PaymentResultBodyComponent> {

    private View bodyView;
    private FrameLayout bodyContainer;
    private TextView textView;

    @Override
    public View render() {
        bodyView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_body, null, false);
        bodyContainer = (FrameLayout) bodyView.findViewById(R.id.mpsdkPaymentResultContainerBody);
        textView = (TextView) bodyView.findViewById(R.id.bodyText);

        renderHeight();
        textView.setText(component.getProps().status);
        return bodyView;
    }

    private void renderHeight() {
        if (component.getProps().height.equals("wrap")) {
            wrapHeight(bodyContainer);
        } else if (component.getProps().height.equals("stretch")) {
            stretchHeight(bodyContainer);
        }
    }
}
