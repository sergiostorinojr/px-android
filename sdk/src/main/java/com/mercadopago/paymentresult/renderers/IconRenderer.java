package com.mercadopago.paymentresult.renderers;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.paymentresult.components.IconComponent;

/**
 * Created by vaserber on 10/23/17.
 */

public class IconRenderer extends Renderer<IconComponent> {

    private View iconView;
    private ImageView iconProduct;
    private ImageView iconBadge;

    @Override
    public View render() {
        iconView = LayoutInflater.from(context).inflate(R.layout.mpsdk_icon, null, false);
        iconProduct = (ImageView) iconView.findViewById(R.id.mpsdkIconProduct);
        iconBadge = (ImageView) iconView.findViewById(R.id.mpsdkIconBadge);

        Drawable iconProductImage = ContextCompat.getDrawable(context, component.getProps().iconProductId);
        iconProduct.setImageDrawable(iconProductImage);

        Drawable iconBadgeImage = ContextCompat.getDrawable(context, component.getProps().iconBadgeId);
        iconBadge.setImageDrawable(iconBadgeImage);

        return iconView;
    }
}
