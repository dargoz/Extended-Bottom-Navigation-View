package com.dargoz.extendedbottomnavigationview.menu;

import android.content.Context;
import android.graphics.Typeface;
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

import com.dargoz.extendedbottomnavigationview.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class SubMenuLayout extends BaseMenuLayout {
    private SubMenuOrientation orientation;

    public SubMenuLayout(Context context, SubMenuOrientation orientation) {
        super(context);
        this.orientation = orientation;
    }

    @NonNull
    @Override
    protected LinearLayout buildMenuItemLayout(final Menu menu, Context context, TextView titleText,
                                               ImageView imageView, final int itemIndex) {
        LinearLayout menuItemContainer = new LinearLayout(context);
        menuItemContainer.setOrientation(orientation.getValue());
        menuItemContainer.addView(imageView);
        menuItemContainer.addView(titleText);
        menuItemContainer.setId(View.generateViewId());
        menuItemContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        int margin = context.getResources().getDimensionPixelSize(R.dimen.baseline_8dp);
        layoutParams.setMargins(0, 0, margin, 0);
        menuItemContainer.setLayoutParams(layoutParams);

        int padding = context.getResources().getDimensionPixelSize(R.dimen.baseline_15dp);
        menuItemContainer.setPadding(padding, padding, padding, padding);
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

    @Override
    protected TextView constructTitleTextView(String text) {
        TextView titleText = new TextView(context);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 0.3);
        titleParams.setMargins(
                context.getResources().getDimensionPixelSize(R.dimen.baseline_4dp), 0,
                0, 0);
        titleText.setLayoutParams(titleParams);
        titleText.setText(text);
        titleText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        titleText.setTextSize(TypedValue.DENSITY_DEFAULT, 26);
        titleText.setTypeface(Typeface.DEFAULT_BOLD);
        titleText.setSelected(false);
        return titleText;
    }
}
