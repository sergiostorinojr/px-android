package com.mercadopago.paymentresult;

import android.support.annotation.NonNull;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultProps {

    public final String status;
    public final String headerHeight;
    public final String bodyHeight;

    public PaymentResultProps(String status, String headerHeight, String bodyHeight) {
        this.status = status;
        this.headerHeight = headerHeight;
        this.bodyHeight = bodyHeight;
    }

    public PaymentResultProps(@NonNull final Builder builder) {
        this.status = builder.status;
        this.headerHeight = builder.headerHeight;
        this.bodyHeight = builder.bodyHeight;
    }

    public Builder toBuilder() {
        return new Builder()
                .setStatus(this.status)
                .setHeaderHeight(this.headerHeight)
                .setBodyHeight(this.bodyHeight);
    }

    public class Builder {

        public String status;
        public String headerHeight;
        public String bodyHeight;

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setHeaderHeight(String headerHeight) {
            this.headerHeight = headerHeight;
            return this;
        }

        public Builder setBodyHeight(String bodyHeight) {
            this.bodyHeight = bodyHeight;
            return this;
        }

        public PaymentResultProps build() {
            return new PaymentResultProps(this);
        }
    }
}
