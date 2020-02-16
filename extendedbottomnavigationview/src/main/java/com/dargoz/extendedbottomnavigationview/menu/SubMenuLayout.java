package com.dargoz.extendedbottomnavigationview.menu;

import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.dargoz.extendedbottomnavigationview.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class SubMenuLayout extends BaseMenuLayout {

    public SubMenuLayout(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected LinearLayout buildMenuItemLayout(final Menu menu, Context context, TextView titleText,
                                               ImageView imageView, final int itemIndex) {
        LinearLayout menuItemContainer = new LinearLayout(context);
        menuItemContainer.setOrientation(LinearLayout.VERTICAL);
        menuItemContainer.addView(imageView);
        menuItemContainer.addView(titleText);
        menuItemContainer.setId(View.generateViewId());
        menuItemContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
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
}
