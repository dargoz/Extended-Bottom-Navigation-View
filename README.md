[![API](https://img.shields.io/badge/API-null%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=null)
[![](https://jitpack.io/v/dargoz/Extended-Bottom-Navigation-View.svg)](https://jitpack.io/#dargoz/Extended-Bottom-Navigation-View)
[![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

# Extended-Bottom-Navigation-View
Android Custom View for Bottom Navigation (Using ConstraintLayout as baseLayout) with customizable sub-menu content.

# Usage
supported attributes with default values:
```xml
<com.dargoz.extendedbottomnavigationview.BottomNavigationBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"/>
```

|**Attrs**|**Default**|
|:---|:---|
| `menuBackgroundColor` | `@color/R.color.default_background_color`
| `menuItemTextColor` | `@color/R.color.default_color_state`
| `highlightMenuPosition` | `-1`
| `subMenu` | `unset`
| `subMenuType` | `1`
| `subMenuBackground` | `@color/R.color.default_sub_menu_background_color_state`
| `subMenuTextColor` | ``

