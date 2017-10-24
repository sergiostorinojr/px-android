package com.mercadopago.paymentresult.props;

import android.support.annotation.NonNull;

import com.mercadopago.model.PaymentResult;

/**
 * Created by vaserber on 10/23/17.
 */

public class IconProps {

    public final Integer iconImage;
    public final Integer badgeImage;

    public IconProps(Integer iconImage, Integer badgeImage) {
        this.iconImage = iconImage;
        this.badgeImage = badgeImage;
    }

    public IconProps(@NonNull final Builder builder) {
        this.iconImage = builder.iconImage;
        this.badgeImage = builder.badgeImage;
    }

    public Builder toBuilder() {
        return new Builder()
                .setIconImage(iconImage)
                .setBadgeImage(badgeImage);
    }

    public static class Builder {

        public Integer iconImage;
        public Integer badgeImage;

        public Builder setIconImage(Integer iconImage) {
            this.iconImage = iconImage;
            return this;
        }

        public Builder setBadgeImage(Integer badgeImage) {
            this.badgeImage = badgeImage;
            return this;
        }

        public IconProps build() {
            return new IconProps(this);
        }
    }
}
