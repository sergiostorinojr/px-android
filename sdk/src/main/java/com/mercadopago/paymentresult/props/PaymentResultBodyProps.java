package com.mercadopago.paymentresult.props;

import android.support.annotation.NonNull;

/**
 * Created by vaserber on 10/23/17.
 */

public class PaymentResultBodyProps {

    public final String status;
    public final String height;

    public PaymentResultBodyProps(String status, String height) {
        this.status = status;
        this.height = height;
    }

    public PaymentResultBodyProps(@NonNull final Builder builder) {
        this.status = builder.status;
        this.height = builder.height;
    }

    public Builder toBuilder() {
        return new Builder()
                .setStatus(this.status)
                .setHeight(this.height);
    }

    public class Builder {

        public String status;
        public String height;

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setHeight(String height) {
            this.height = height;
            return this;
        }

        public PaymentResultBodyProps build() {
            return new PaymentResultBodyProps(this);
        }
    }
}
