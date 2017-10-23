package com.mercadopago.paymentresult.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.components.RendererFactory;
import com.mercadopago.paymentresult.components.PaymentResultHeaderComponent;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultHeaderRenderer extends Renderer<PaymentResultHeaderComponent> {

    private View headerView;
    private ViewGroup headerContainer;
    private TextView statusTextView;
    private Renderer iconRenderer;
    private ViewGroup iconParentViewGroup;

    @Override
    public View render() {
        headerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_header, null, false);
        headerContainer = (ViewGroup) headerView.findViewById(R.id.mpsdkPaymentResultContainerHeader);
        statusTextView = (TextView) headerView.findViewById(R.id.mpsdkHeaderStatus);
        iconParentViewGroup = (ViewGroup) headerView.findViewById(R.id.iconContainer);

        iconRenderer = RendererFactory.create(context, component.getIconComponent());
        View icon = renderIcon();
        this.iconParentViewGroup.addView(icon);

        statusTextView.setText(component.getProps().status);

        renderHeight();
        return headerView;
    }

    private void renderHeight() {
        if (component.getProps().height.equals("wrap")) {
            wrapHeight(headerContainer);
        } else if (component.getProps().height.equals("stretch")) {
            stretchHeight(headerContainer);
        }
    }

    private View renderIcon() {
        return iconRenderer.render();
    }
}
