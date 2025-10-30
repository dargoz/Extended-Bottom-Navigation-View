package com.dargoz.extendedbottomnavigationview.menu;

import android.view.Menu;
import android.widget.LinearLayout;

import androidx.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public interface MenuLayout {
    LinearLayout constructMenu(final Menu menu, int itemIndex);
    void setOnMenuClickListener(MenuOnClickListener listener);
}
