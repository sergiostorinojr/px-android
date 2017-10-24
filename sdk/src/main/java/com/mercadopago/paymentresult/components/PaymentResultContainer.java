package com.mercadopago.paymentresult.components;

import android.support.annotation.NonNull;

import com.mercadopago.R;
import com.mercadopago.components.ActionDispatcher;
import com.mercadopago.components.Component;
import com.mercadopago.constants.PaymentMethods;
import com.mercadopago.constants.PaymentTypes;
import com.mercadopago.model.Payment;
import com.mercadopago.model.PaymentResult;
import com.mercadopago.paymentresult.props.PaymentResultBodyProps;
import com.mercadopago.paymentresult.props.PaymentResultHeaderProps;
import com.mercadopago.paymentresult.props.PaymentResultProps;

/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultContainer extends Component<PaymentResultProps> {

    private static final Integer DEFAULT_BACKGROUND_COLOR = R.color.mpsdk_blue_MP;
    private static final Integer GREEN_BACKGROUND_COLOR = R.color.mpsdk_green_MP;
    private static final Integer RED_BACKGROUND_COLOR = R.color.mpsdk_red_MP;
    private static final Integer ORANGE_BACKGROUND_COLOR = R.color.mpsdk_orange_MP;

    private static final Integer DEFAULT_ICON_IMAGE = R.drawable.mpsdk_icon_default;
    private static final Integer ITEM_ICON_IMAGE = R.drawable.mpsdk_icon_product;
    private static final Integer CARD_ICON_IMAGE = R.drawable.mpsdk_icon_card;
    private static final Integer BOLETO_ICON_IMAGE = R.drawable.mpsdk_icon_boleto;

    private static final Integer DEFAULT_BADGE_IMAGE = R.drawable.mpsdk_badge_pending;
    private static final Integer CHECK_BADGE_IMAGE = R.drawable.mpsdk_badge_check;
    private static final Integer PENDING_BADGE_IMAGE = R.drawable.mpsdk_badge_pending;
    private static final Integer ERROR_BADGE_IMAGE = R.drawable.mpsdk_badge_error;
    private static final Integer WARNING_BADGE_IMAGE = R.drawable.mpsdk_badge_warning;

    private PaymentResultHeaderComponent headerComponent;
    private PaymentResultBodyComponent bodyComponent;
    private PaymentResultFooterComponent footerComponent;

    public PaymentResultContainer(@NonNull final ActionDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void applyProps(@NonNull PaymentResultProps props) {

        PaymentResultHeaderProps headerProps = new PaymentResultHeaderProps.Builder()
                .setHeight(props.headerMode)
                .setBackground(getBackground(props.paymentResult))
                .setIconImage(getIconImage(props.paymentResult))
                .setBadgeImage(getBadgeImage(props.paymentResult))
                .build();
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

    // Background logic
    private Integer getBackground(PaymentResult paymentResult) {
        if (isGreenBackground(paymentResult)) {
            return GREEN_BACKGROUND_COLOR;
        } else if (isRedBackground(paymentResult)) {
            return RED_BACKGROUND_COLOR;
        } else if (isOrangeBackground(paymentResult)) {
            return ORANGE_BACKGROUND_COLOR;
        } else {
            return DEFAULT_BACKGROUND_COLOR;
        }
    }

    private boolean isGreenBackground(PaymentResult paymentResult) {
        return isPaymentResultValid(paymentResult) &&
                (paymentResult.getPaymentStatus().equals(Payment.StatusCodes.STATUS_APPROVED) ||
                        paymentResult.getPaymentStatus().equals(Payment.StatusCodes.STATUS_IN_PROCESS) ||
                        paymentResult.getPaymentStatus().equals(Payment.StatusCodes.STATUS_PENDING));
    }

    private boolean isRedBackground(PaymentResult paymentResult) {
        if (isPaymentResultValid(paymentResult)) {
            String status = paymentResult.getPaymentStatus();
            String statusDetail = paymentResult.getPaymentStatusDetail();
            return status.equals(Payment.StatusCodes.STATUS_REJECTED) &&
                    (statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_OTHER_REASON) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_REJECTED_REJECTED_BY_BANK) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_REJECTED_REJECTED_INSUFFICIENT_DATA) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_DUPLICATED_PAYMENT) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_MAX_ATTEMPTS) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_REJECTED_HIGH_RISK));

        }
        return false;
    }

    private boolean isOrangeBackground(PaymentResult paymentResult) {
        if (isPaymentResultValid(paymentResult)) {
            String status = paymentResult.getPaymentStatus();
            String statusDetail = paymentResult.getPaymentStatusDetail();
            return status.equals(Payment.StatusCodes.STATUS_REJECTED) &&
                    (statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_INVALID_ESC) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_CALL_FOR_AUTHORIZE) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_BAD_FILLED_CARD_NUMBER) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_BAD_FILLED_DATE) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_BAD_FILLED_SECURITY_CODE) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_BAD_FILLED_OTHER) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_CARD_DISABLED) ||
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_CC_REJECTED_INSUFFICIENT_AMOUNT));

        }
        return false;
    }


    // Icon Image logic

    private Integer getIconImage(PaymentResult paymentResult) {
        if (isItemIconImage(paymentResult)) {
            return ITEM_ICON_IMAGE;
        } else if (isCardIconImage(paymentResult)) {
            return CARD_ICON_IMAGE;
        } else if (isBoletoIconImage(paymentResult)) {
            return BOLETO_ICON_IMAGE;
        } else {
            return DEFAULT_ICON_IMAGE;
        }
    }

    private boolean isItemIconImage(PaymentResult paymentResult) {
        if (isPaymentResultValid(paymentResult)) {
            String status = paymentResult.getPaymentStatus();
            String statusDetail = paymentResult.getPaymentStatusDetail();
            return status.equals(Payment.StatusCodes.STATUS_APPROVED) ||
                    status.equals(Payment.StatusCodes.STATUS_IN_PROCESS) ||
                    (status.equals(Payment.StatusCodes.STATUS_PENDING) &&
                            statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_PENDING_WAITING_PAYMENT));
        }
        return false;
    }

    private boolean isCardIconImage(PaymentResult paymentResult) {
        if (isPaymentMethodIconImage(paymentResult) && isPaymentTypeValid(paymentResult)) {
            String paymentTypeId = paymentResult.getPaymentData().getPaymentMethod().getPaymentTypeId();
            return paymentTypeId.equals(PaymentTypes.PREPAID_CARD) || paymentTypeId.equals(PaymentTypes.DEBIT_CARD) ||
                    paymentTypeId.equals(PaymentTypes.CREDIT_CARD);
        }
        return false;
    }

    private boolean isBoletoIconImage(PaymentResult paymentResult) {
        if (isPaymentMethodIconImage(paymentResult) && isPaymentMethodValid(paymentResult)) {
            String paymentMethodId = paymentResult.getPaymentData().getPaymentMethod().getId();
            return paymentMethodId.equals(PaymentMethods.BRASIL.BOLBRADESCO);
        }
        return false;
    }

    private boolean isPaymentMethodIconImage(PaymentResult paymentResult) {
        if (isPaymentResultValid(paymentResult)) {
            String status = paymentResult.getPaymentStatus();
            String statusDetail = paymentResult.getPaymentStatusDetail();
            return ((status.equals(Payment.StatusCodes.STATUS_PENDING) && !statusDetail.equals(Payment.StatusCodes.STATUS_DETAIL_PENDING_WAITING_PAYMENT)) ||
                    status.equals(Payment.StatusCodes.STATUS_REJECTED));
        }
        return false;
    }

    // Badge Image logic

    private Integer getBadgeImage(PaymentResult paymentResult) {
        return R.drawable.mpsdk_badge_check;
    }


    private boolean isPaymentResultValid(PaymentResult paymentResult) {
        return paymentResult != null && paymentResult.getPaymentStatus() != null && paymentResult.getPaymentStatusDetail() != null;
    }

    private boolean isPaymentTypeValid(PaymentResult paymentResult) {
        return paymentResult != null && paymentResult.getPaymentData() != null && paymentResult.getPaymentData().getPaymentMethod() != null &&
                paymentResult.getPaymentData().getPaymentMethod().getPaymentTypeId() != null;
    }

    private boolean isPaymentMethodValid(PaymentResult paymentResult) {
        return paymentResult != null && paymentResult.getPaymentData() != null && paymentResult.getPaymentData().getPaymentMethod() != null &&
                paymentResult.getPaymentData().getPaymentMethod().getId() != null;
    }
}
