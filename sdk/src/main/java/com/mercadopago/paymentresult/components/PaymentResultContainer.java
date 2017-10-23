package com.mercadopago.paymentresult.components;

import android.support.annotation.NonNull;

import com.mercadopago.R;
import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;
import com.mercadopago.model.Payment;
import com.mercadopago.model.PaymentResult;
import com.mercadopago.paymentresult.props.PaymentResultBodyProps;
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
        PaymentResultHeaderProps headerProps = new PaymentResultHeaderProps(props.paymentResult.getPaymentStatus(),
                props.headerMode, getIconProductId(), getIconBadgeId(props.paymentResult));
        this.headerComponent = new PaymentResultHeaderComponent(headerProps, getDispatcher());

        PaymentResultBodyProps bodyProps = new PaymentResultBodyProps(props.paymentResult.getPaymentStatus());
        this.bodyComponent = new PaymentResultBodyComponent(bodyProps, getDispatcher());

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

    private Integer getIconProductId() {
        return R.drawable.mpsdk_icon_product;
    }

    private Integer getIconBadgeId(PaymentResult paymentResult) {
        if (paymentResult.getPaymentStatus() != null) {
            String status = paymentResult.getPaymentStatus();
            if (status.equals(Payment.StatusCodes.STATUS_APPROVED)) {
                return R.drawable.mpsdk_badge_check;
            }
        }
        //TODO return default icon
        return R.drawable.mpsdk_badge_check;
    }
}
