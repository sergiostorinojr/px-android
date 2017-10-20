package com.mercadopago.paymentresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.util.LayoutUtil;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultHeaderRenderer extends Renderer<PaymentResultHeaderComponent> {

    private View headerView;
    private FrameLayout headerContainer;
    private TextView statusTextView;

    @Override
    protected void bindViews(Context context) {
        headerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_header, null, false);
        headerContainer = (FrameLayout) headerView.findViewById(R.id.mpsdkPaymentResultContainerHeader);
        statusTextView = (TextView) headerView.findViewById(R.id.mpsdkHeaderStatus);
    }

    @Override
    public View render() {
        renderHeight();
        statusTextView.setText(component.getProps().status);
        return headerView;
    }

    private void renderHeight() {
        if (component.getProps().height.equals("minHeight")) {
            LayoutUtil.convertLayoutToMinHeight(headerContainer);
        } else if (component.getProps().height.equals("maxHeight")) {
            LayoutUtil.convertLayoutToMaxHeight(headerContainer);
        }
    }
}
