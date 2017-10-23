package com.mercadopago.paymentresult.props;

import android.support.annotation.NonNull;

/**
 * Created by vaserber on 10/23/17.
 */

public class IconProps {

    public final Integer iconProductId;
    public final Integer iconBadgeId;

    public IconProps(Integer iconProductId, Integer iconBadgeId) {
        this.iconProductId = iconProductId;
        this.iconBadgeId = iconBadgeId;
    }

    public IconProps(@NonNull final Builder builder) {
        this.iconProductId = builder.iconProductId;
        this.iconBadgeId = builder.iconBadgeId;
    }

    public Builder toBuilder() {
        return new Builder()
                .setIconProductId(this.iconProductId)
                .setIconBadgeId(this.iconBadgeId);
    }

    public class Builder {

        public Integer iconProductId;
        public Integer iconBadgeId;

        public Builder setIconProductId(Integer iconProductId) {
            this.iconProductId = iconProductId;
            return this;
        }

        public Builder setIconBadgeId(Integer iconBadgeId) {
            this.iconBadgeId = iconBadgeId;
            return this;
        }

        public IconProps build() {
            return new IconProps(this);
        }
    }
}
