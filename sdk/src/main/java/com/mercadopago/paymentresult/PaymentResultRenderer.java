package com.mercadopago.paymentresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.components.RendererFactory;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultRenderer extends Renderer<PaymentResultContainer> {

    private View containerView;
    private Renderer headerRenderer;
    private Renderer bodyRenderer;
    private Renderer footerRenderer;
    private ViewGroup parentViewGroup;

    @Override
    public View render() {

        headerRenderer = RendererFactory.create(context, component.getHeaderComponent());
        View header = renderHeader();
        this.parentViewGroup.addView(header);

        bodyRenderer = RendererFactory.create(context, component.getBodyComponent());
        View body = renderBody();
        this.parentViewGroup.addView(body);

        footerRenderer = RendererFactory.create(context, component.getFooterComponent());
        View footer = renderFooter();
        this.parentViewGroup.addView(footer);

        return containerView;
    }

    @Override
    protected void bindViews(Context context) {
        containerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_container, null, false);
        parentViewGroup = (ViewGroup) containerView.findViewById(R.id.mpsdkPaymentResultContainer);
    }

    private View renderHeader() {
        return headerRenderer.render();
    }

    private View renderBody() {
        return bodyRenderer.render();
    }

    private View renderFooter() {
        return footerRenderer.render();
    }
}
