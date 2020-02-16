package com.dargoz.extendedbottomnavigationview.shape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.dargoz.extendedbottomnavigationview.R;

public class ShapeFactory {
    private ShapeFactory(){}

    public static Drawable createRoundedRectangle(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(context.getResources().getDimensionPixelSize(R.dimen.baseline_4dp));
        drawable.setColor(context.getResources()
                .getColor(R.color.default_sub_menu_background_color_state));
        return drawable;
    }
}
