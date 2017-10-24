package com.mercadopago.hooks;

import android.support.annotation.NonNull;

import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.model.PaymentResult;

/**
 * Created by nfortuna on 10/24/17.
 */
public interface CheckoutHooks {
    Hook onPaymentMethodSelected(@NonNull final PaymentResult result,
                                 @NonNull final ActionDispatcher dispatcher);
}