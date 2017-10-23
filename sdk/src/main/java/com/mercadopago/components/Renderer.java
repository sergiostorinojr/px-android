package com.mercadopago.components;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by vaserber on 10/20/17.
 */

public abstract class Renderer<T extends Component> {

    protected T component;
    protected Context context;

    public void setComponent(T component) {
        this.component = component;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract View render();

    public void wrapHeight(ViewGroup viewGroup) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        viewGroup.setLayoutParams(params);
    }

    public void stretchHeight(ViewGroup viewGroup) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f
        );
        viewGroup.setLayoutParams(params);
    }
}
