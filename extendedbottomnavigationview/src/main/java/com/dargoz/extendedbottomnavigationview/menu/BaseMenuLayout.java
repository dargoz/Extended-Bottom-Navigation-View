package com.dargoz.extendedbottomnavigationview.menu;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dargoz.extendedbottomnavigationview.R;


import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class BaseMenuLayout implements MenuLayout {
    MenuOnClickListener menuOnClickListener;
    protected Context context;

    public BaseMenuLayout(Context context) {
        this.context = context;
    }

    @Override
    public void setOnMenuClickListener(MenuOnClickListener listener) {
        menuOnClickListener = listener;
    }

    @Override
    public LinearLayout constructMenu(@NonNull final Menu menu, int itemIndex) {
        TextView titleText = constructTitleTextView(menu.getItem(itemIndex).getTitle().toString());
        ImageView imageView = constructIconImageView(menu.getItem(itemIndex).getIcon());
        return buildMenuItemLayout(menu, context, titleText, imageView, itemIndex);
    }
    protected TextView constructTitleTextView(String text) {
        TextView titleText = new TextView(context);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 0.3);
        titleParams.setMargins(
                0, context.getResources().getDimensionPixelSize(R.dimen.baseline_2dp),
                0, 0);
        titleText.setLayoutParams(titleParams);
        titleText.setText(text);
        titleText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        titleText.setTextSize(TypedValue.DENSITY_DEFAULT, 26);
        titleText.setTypeface(Typeface.DEFAULT_BOLD);
        titleText.setSelected(false);
        return titleText;
    }

    private ImageView constructIconImageView(Drawable drawable) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, WRAP_CONTENT);
        imageView.setLayoutParams(imageParams);
        imageView.setImageDrawable(drawable);
        imageView.setSelected(false);
        return imageView;
    }

    @NonNull
    protected LinearLayout buildMenuItemLayout(final Menu menu, Context context, TextView titleText,
                                               ImageView imageView, final int itemIndex) {
        LinearLayout menuItemContainer = new LinearLayout(context);
        menuItemContainer.setOrientation(LinearLayout.VERTICAL);
        menuItemContainer.addView(imageView);
        menuItemContainer.addView(titleText);
        menuItemContainer.setId(View.generateViewId());
        menuItemContainer.setGravity(Gravity.CENTER);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout
                .LayoutParams(0, WRAP_CONTENT);
        menuItemContainer.setLayoutParams(layoutParams);

        int padding = context.getResources().getDimensionPixelSize(R.dimen.baseline_15dp);
        menuItemContainer.setPadding(0, padding, 0, padding);
        menuItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOnClickListener != null) {
                    menuOnClickListener.onMenuItemClick(menu, itemIndex);
                }
            }
        });
        return menuItemContainer;
    }
}
