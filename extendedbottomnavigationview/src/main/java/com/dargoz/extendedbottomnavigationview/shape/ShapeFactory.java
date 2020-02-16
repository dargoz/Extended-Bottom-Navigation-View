package com.dargoz.extendedbottomnavigationview.shape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.dargoz.extendedbottomnavigationview.R;

public class ShapeFactory {
    private ShapeFactory(){}

    public static Drawable createRoundedRectangle(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(radius);
        drawable.setColor(color);
        return drawable;
    }
}
