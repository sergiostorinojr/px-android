package com.mercadopago.paymentresult.props;

import android.support.annotation.NonNull;


/**
 * Created by vaserber on 10/20/17.
 */

public class PaymentResultHeaderProps {

    public final String height;
    public final Integer background;
    public final Integer iconImage;
    public final Integer badgeImage;
    public final Integer title;
    public final Integer label;

    public PaymentResultHeaderProps(String height, Integer background, Integer iconImage, Integer badgeImage, Integer title, Integer label) {
        this.height = height;
        this.background = background;
        this.iconImage = iconImage;
        this.badgeImage = badgeImage;
        this.title = title;
        this.label = label;
    }

    public PaymentResultHeaderProps(@NonNull final Builder builder) {
        this.height = builder.height;
        this.background = builder.background;
        this.iconImage = builder.iconImage;
        this.badgeImage = builder.badgeImage;
        this.title = builder.title;
        this.label = builder.label;
    }

    public Builder toBuilder() {
        return new Builder()
                .setHeight(this.height)
                .setBackground(this.background)
                .setIconImage(this.iconImage)
                .setBadgeImage(this.badgeImage)
                .setTitle(this.title)
                .setLabel(this.label);
    }

    public static class Builder {

        public String height;
        public Integer background;
        public Integer iconImage;
        public Integer badgeImage;
        public Integer title;
        public Integer label;

        public Builder setBackground(Integer background) {
            this.background = background;
            return this;
        }

        public Builder setIconImage(Integer iconImage) {
            this.iconImage = iconImage;
            return this;
        }

        public Builder setBadgeImage(Integer badgeImage) {
            this.badgeImage = badgeImage;
            return this;
        }

        public Builder setHeight(String height) {
            this.height = height;
            return this;
        }

        public Builder setTitle(Integer title) {
            this.title = title;
            return this;
        }

        public Builder setLabel(Integer label) {
            this.label = label;
            return this;
        }

        public PaymentResultHeaderProps build() {
            return new PaymentResultHeaderProps(this);
        }
    }
}
