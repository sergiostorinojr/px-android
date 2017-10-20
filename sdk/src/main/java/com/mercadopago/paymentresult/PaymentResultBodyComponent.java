package com.mercadopago.paymentresult;

import android.support.annotation.NonNull;

import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;

/**
 * Created by vaserber on 10/23/17.
 */

public class PaymentResultBodyComponent extends Component<PaymentResultBodyProps> {

    public PaymentResultBodyComponent(PaymentResultBodyProps props, @NonNull final ActionDispatcher dispatcher) {
        super(props, dispatcher);
    }

    @Override
    public void applyProps(@NonNull PaymentResultBodyProps props) {
        this.setProps(props);
    }
}