package com.dargoz.extendedbottomnavigationview;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class BottomNavigationMenu extends MenuBuilder {
    private static final int MAX_ITEM_COUNT = 5;
    public BottomNavigationMenu(Context context) {
        super(context);
    }

    @Override
    protected MenuItem addInternal(int group, int id, int categoryOrder, CharSequence title) {
        if (this.size() + 1 > MAX_ITEM_COUNT) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5.");
        } else {
            this.stopDispatchingItemsChanged();
            MenuItem item = super.addInternal(group, id, categoryOrder, title);
            if (item instanceof MenuItemImpl) {
                ((MenuItemImpl)item).setExclusiveCheckable(true);
            }

            this.startDispatchingItemsChanged();
            return item;
        }
    }
}
