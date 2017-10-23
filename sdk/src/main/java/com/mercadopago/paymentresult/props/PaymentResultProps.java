package com.mercadopago.paymentresult.props;

import android.support.annotation.NonNull;

import com.mercadopago.model.PaymentResult;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultProps {

    public final PaymentResult paymentResult;
    public final String headerMode; //"wrap", "stretch"

    public PaymentResultProps() {
        this.paymentResult = null;
        this.headerMode = "wrap";
    }

    public PaymentResultProps(PaymentResult paymentResult, String headerMode) {
        this.paymentResult = paymentResult;
        this.headerMode = headerMode;
    }

    public PaymentResultProps(@NonNull final Builder builder) {
        this.paymentResult = builder.paymentResult;
        this.headerMode = builder.headerMode;
    }

    public Builder toBuilder() {
        return new Builder()
                .setPaymentResult(this.paymentResult)
                .setHeaderMode(this.headerMode);
    }

    public class Builder {

        public PaymentResult paymentResult;
        public String headerMode;

        public Builder setPaymentResult(PaymentResult paymentResult) {
            this.paymentResult = paymentResult;
            return this;
        }

        public Builder setHeaderMode(String headerMode) {
            this.headerMode = headerMode;
            return this;
        }

        public PaymentResultProps build() {
            return new PaymentResultProps(this);
        }
    }
}
