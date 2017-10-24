package com.mercadopago.paymentresult.components;

import android.support.annotation.NonNull;

import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;
import com.mercadopago.paymentresult.props.IconProps;
import com.mercadopago.paymentresult.props.PaymentResultHeaderProps;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultHeaderComponent extends Component<PaymentResultHeaderProps> {

    private IconComponent iconComponent;

    public PaymentResultHeaderComponent(PaymentResultHeaderProps props, @NonNull final ActionDispatcher dispatcher) {
        super(props, dispatcher);
    }

    @Override
    public void applyProps(@NonNull PaymentResultHeaderProps props) {
        IconProps iconProps = new IconProps.Builder()
                .setIconImage(props.iconImage)
                .setBadgeImage(props.badgeImage)
                .build();
        this.iconComponent = new IconComponent(iconProps, getDispatcher());
    }

    public IconComponent getIconComponent() {
        return iconComponent;
    }


}
