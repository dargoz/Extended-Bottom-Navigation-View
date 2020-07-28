[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![](https://jitpack.io/v/dargoz/Extended-Bottom-Navigation-View.svg)](https://jitpack.io/#dargoz/Extended-Bottom-Navigation-View)
[![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

# Extended-Bottom-Navigation-View
Android Custom View for Bottom Navigation (Using ConstraintLayout as baseLayout) with customizable sub-menu content.
[](https://i.imgur.com/ZHqcG9fm.png)


# Usage
supported attributes with default values:
```xml
<com.dargoz.extendedbottomnavigationview.BottomNavigationBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"/>
```

|**Attrs**|**Related methods**|**Default value**|
|:---|:---|:---|
| `menu` | N/A | N/A
| `menuBackgroundColor` | N/A | `@color/R.color.default_background_color`
| `menuItemTextColor` | N/A | `@color/R.color.default_color_state`
| `highlightMenuPosition` | `setHighlightMenuPosition` | `-1`
| `subMenu` | N/A | `unset`
| `subMenuType` | N/A | `1`
| `subMenuBackground` | N/A | `@color/R.color.default_sub_menu_background_color_state`
| `subMenuTextColor` | `setSubMenuTextColor` | ``


### Public Methods
|**Return type**|**Method name**|
|:---|:---|
| `void` | `addSubMenu(int menuResId, int indexRootMenu, SubMenuOrientation orientation)`
| `Menu` | `getMenu()`
| `ImageView` | `getMenuBackground()`
| `LinearLayout` | `getMenuChildAt(int position)`
| `void` | `setHighlightMenuPosition(int position)`
| `void` | [`setMenuOnClickListener(MenuOnClickListener menuOnClickListener)`](#set-menu-click-listener)
| `void` | `setSelectedMenuItem(int itemIndex)`
| `void` | `setSubMenuOnClickListener(MenuOnClickListener subMenuOnClickListener)`
| `void` | `setSubMenuTextColor(int colorResId)`
| `void` | `showSubMenu(int position, boolean visibility)`


### Set Menu List item
1. create `.xml` **menu resource** inside menu resource directory. (`res\menu\<your_resource_name>.xml`)
<br>Example:
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:title="@string/home_title_text"
        android:icon="@drawable/icon_home"
        />
    <item
        android:title="@string/transaction_title_text"
        android:icon="@drawable/icon_credit"
        />
    <item
        android:title="@string/scan_title_text"
        android:icon="@drawable/baseline_home_black_24"
        />
    <item
        android:title="@string/notification_title_text"
        android:icon="@drawable/icon_notification"
        />
    <item
        android:title="@string/account_title_text"
        android:icon="@drawable/icon_person"
        />
</menu>
```
2. add your menu resource as input to `app:menu` BottomNavigationBar attribute in `layout.xml`
```xml
<com.dargoz.extendedbottomnavigationview.BottomNavigationBar
        android:id="@+id/bottom_navigation_menu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:menu="@menu/bottom_navigation_menu_list"/>
```

### Set Menu Click Listener
Use `setMenuOnClickListener` to receive callback action from clicked menu context.
```kotlin
bottomNavBar.setMenuOnClickListener { menu, position ->
         bottomNavBar.setSelectedMenuItem(position)
         //Your code here
}
```
this method consist 2 parameters:
-  **menu** : return `Menu` object that contain menu `title` and menu `icon` value.
- **position** : return `integer` value of clicked menu position. (start from 0)
