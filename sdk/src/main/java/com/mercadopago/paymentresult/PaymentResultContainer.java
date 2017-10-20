package com.mercadopago.paymentresult;

import android.support.annotation.NonNull;

import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;

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
        this.setProps(props);

        PaymentResultHeaderProps headerProps = new PaymentResultHeaderProps(props.status, props.headerHeight);
        this.headerComponent = new PaymentResultHeaderComponent(headerProps, getDispatcher());

        PaymentResultBodyProps bodyProps = new PaymentResultBodyProps(props.status, props.bodyHeight);
        this.bodyComponent = new PaymentResultBodyComponent(bodyProps, getDispatcher());

        this.footerComponent = new PaymentResultFooterComponent(props.status, getDispatcher());
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
}
