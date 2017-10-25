package com.mercadopago.paymentresult;

import com.mercadopago.components.Mutator;
import com.mercadopago.components.MutatorPropsListener;
import com.mercadopago.model.PaymentResult;
import com.mercadopago.paymentresult.props.PaymentResultProps;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultPropsMutator implements Mutator, PaymentResultPropsView {

    private MutatorPropsListener propsListener;

    //Component props with default values
    private PaymentResultProps props = new PaymentResultProps();

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
    public void setPropPaymentResult(final PaymentResult paymentResult) {
        props = props.toBuilder()
                .setPaymentResult(paymentResult)
                .setHeaderMode("stretch")
                .build();
        notifyPropsChanged();
    }

    private void notifyPropsChanged() {
        if (propsListener != null) {
            propsListener.onProps(props);
        }
    }
}
