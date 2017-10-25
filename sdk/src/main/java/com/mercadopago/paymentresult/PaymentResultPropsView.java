package com.mercadopago.paymentresult;

import com.mercadopago.model.PaymentResult;
import com.mercadopago.model.Site;
import com.mercadopago.mvp.MvpView;

import java.math.BigDecimal;

public interface PaymentResultPropsView extends MvpView {

//    void showCongrats(Site site, BigDecimal amount, PaymentResult paymentResult, Boolean discountEnabled);
//
//    void showCallForAuthorize(Site site, PaymentResult paymentResult);
//
//    void showRejection(PaymentResult paymentResult);
//
//    void showPending(PaymentResult paymentResult);
//
//    void showInstructions(Site site, BigDecimal amount, PaymentResult paymentResult);

    void showError(String errorMessage);

    void showError(String errorMessage, String errorDetail);

    void setPropPaymentResult(PaymentResult paymentResult);
}
