[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![](https://jitpack.io/v/dargoz/Extended-Bottom-Navigation-View.svg)](https://jitpack.io/#dargoz/Extended-Bottom-Navigation-View)
[![License](https://img.shields.io/badge/License-Apache%202.0-brightgreen.svg)](https://opensource.org/licenses/Apache-2.0)

# Extended-Bottom-Navigation-View
Android Custom View for Bottom Navigation (Using ConstraintLayout as baseLayout) with customizable sub-menu content.<br>
![](https://i.imgur.com/ZHqcG9fm.png)


# Setup
1. Add it in your root build.gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency
```
dependencies {
	implementation 'com.github.dargoz:Extended-Bottom-Navigation-View:Tag'
}
```

# Usage
supported attributes with default values:
```xml
<com.dargoz.extendedbottomnavigationview.BottomNavigationBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"/>
```
**NOTE: Due to better user experience for bottom navigation, this View only supports a maximum of 5 main content.**



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
| `void` | [`addSubMenu(int menuResId, int indexRootMenu, SubMenuOrientation orientation)`](#add-sub-menu)<br> add additional sub menu content to spesific main menu item position.
| `Menu` | `getMenu()`
| `ImageView` | `getMenuBackground()` <br> return bottomNavBar menu background container.
| `LinearLayout` | `getMenuChildAt(int position)` <br> return the layout container of given main menu item `position`.
| `void` | `setHighlightMenuPosition(int position)`<br>set spesific main menu item position as highlighted menu by giving extra `paddingBottom` on it.
| `void` | [`setMenuOnClickListener(MenuOnClickListener menuOnClickListener)`](#set-menu-click-listener)<br> set bottom navigation main menu callback action on user click.
| `void` | `setSelectedMenuItem(int itemIndex)` <br> set the given main menu `position` as **selected**. this method will make **`icon`** and **`title`** changes their selection state value as **`true`** (also known as `android:state_selected="true"` if using custom drawable selector state).
| `void` | [`setSubMenuOnClickListener(MenuOnClickListener subMenuOnClickListener)`](#set-submenu-click-listener)
| `void` | `setSubMenuTextColor(int colorResId)` <br>set color or colorState of sub menu **`title`** text.
| `void` | `showSubMenu(int position, boolean visibility)` <br>set sub menu `visibility` for given sub menu `position`. <br>`true`: set view to `VISIBLE`. <br>`false`: set view to `GONE`.


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
2. add your menu resource as input to `app:menu` BottomNavigationBar attribute in `layout.xml` <br>

```xml
<com.dargoz.extendedbottomnavigationview.BottomNavigationBar
        android:id="@+id/bottom_navigation_menu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:menu="@menu/bottom_navigation_menu_list"/>
```

### Add Sub Menu
```kotlin
bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 4, SubMenuOrientation.VERTICAL)
```
this method consist 3 input parameters:
- **`menuResId`** : required **menu** type of resources in menu resource directory.
- **`indexRootMenu`** : index of menu to add the sub menu content.
- **`orientation`** : sub menu container orientation style. you can choose between **`HORIZONTAL`** and **`VERTICAL`**.


### Set Menu Click Listener
Use `setMenuOnClickListener` to receive callback action from clicked menu context.
```kotlin
bottomNavBar.setMenuOnClickListener { menu, position ->
         bottomNavBar.setSelectedMenuItem(position)
         //Your code here
}
```
this method consist 2 parameters:
- **`menu`** : return **`Menu`** object that contain menu **`title`** and menu **`icon`** value.
- **`position`** : return **`integer`** value of clicked menu position. (start from 0)

### Set SubMenu Click Listener
```kotlin
bottomNavBar.setSubMenuOnClickListener { menu, position -> 
            //Your code here
}
```

# License
   Copyright 2020 Davin Reinaldo Gozali

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
