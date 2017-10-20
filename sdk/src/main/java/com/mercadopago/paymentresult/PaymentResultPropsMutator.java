package com.mercadopago.paymentresult;

import com.mercadopago.components.Mutator;
import com.mercadopago.components.MutatorPropsListener;
import com.mercadopago.model.PaymentResult;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultPropsMutator implements Mutator, PaymentResultPropsView {

    private MutatorPropsListener propsListener;

    //Component props with default values
    private PaymentResultProps props = new PaymentResultProps("status default", "minHeight", "maxHeight");

    @Override
    public void setPropsListener(MutatorPropsListener listener) {
        this.propsListener = listener;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showError(String errorMessage, String errorDetail) {

    }

    @Override
    public void setPropPaymentResult(PaymentResult paymentResult) {
        props = props.toBuilder()
                .setStatus(paymentResult.getPaymentStatus())
                .setHeaderHeight("minHeight")
                .setBodyHeight("maxHeight")
                .build();
        notifyPropsChanged();
    }

    private void notifyPropsChanged() {
        if (propsListener != null) {
            propsListener.onProps(props);
        }
    }
}
