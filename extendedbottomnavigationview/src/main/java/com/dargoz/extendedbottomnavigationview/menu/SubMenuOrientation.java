package com.dargoz.extendedbottomnavigationview.menu;

import android.widget.LinearLayout;

public enum SubMenuOrientation {
    HORIZONTAL(LinearLayout.HORIZONTAL),
    VERTICAL(LinearLayout.VERTICAL);

    private int value;
    SubMenuOrientation(int orientation) {
        this.value = orientation;
    }

    public int getValue() {
        return value;
    }
}
