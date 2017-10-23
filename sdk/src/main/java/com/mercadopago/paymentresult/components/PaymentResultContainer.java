package com.mercadopago.paymentresult.components;

import android.support.annotation.NonNull;

import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;
import com.mercadopago.paymentresult.props.PaymentResultHeaderProps;
import com.mercadopago.paymentresult.props.PaymentResultProps;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultContainer extends Component<PaymentResultProps> {

    private PaymentResultHeaderComponent headerComponent;
    private PaymentResultBodyComponent bodyComponent;
    private PaymentResultFooterComponent footerComponent;

    public PaymentResultContainer(@NonNull final ActionDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void applyProps(@NonNull PaymentResultProps props) {
        PaymentResultHeaderProps headerProps = new PaymentResultHeaderProps(props.paymentResult.getPaymentStatus(), props.headerMode);
        this.headerComponent = new PaymentResultHeaderComponent(headerProps, getDispatcher());
        this.footerComponent = new PaymentResultFooterComponent(props.paymentResult.getPaymentStatus(), getDispatcher());
    }

    public PaymentResultHeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public PaymentResultBodyComponent getBodyComponent() {
        return bodyComponent;
    }

    public PaymentResultFooterComponent getFooterComponent() {
        return footerComponent;
    }

    public boolean hasBody() {
        return bodyComponent != null;
    }
}
