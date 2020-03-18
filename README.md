# Extended-Bottom-Navigation-View
Android Custom View for Bottom Navigation with extra content

ChangeList
- v 0.0.1
  - initial release still under development

- v 0.0.2
  - add Default background & textColor state list so no need to always set it anymore for usage.

- v 0.0.3
  - add sub menu implementation
  
- v 0.0.4
  - fixing sub menu functionality
  
- v 0.0.5
  - add show/hide sub menu method
  
- v 0.0.6
  - add custom subMenu Background color from xml (programmatically coming soon)
  - add subMenu param for vertical/horizontal style
  - add subMenu type :
    - 1: full background with text on the right icon.
    - 2: background per item with text below icon.

- v 0.0.7
  - add custom sub menu text color
  
- v 0.0.8
  - add SELECTED_NONE for bottom navigation
  - disable multitouch on bottom navigation bar
  
- v 0.0.9
  - fixing error logic for highlight menu shouldn't be changed on setSelectedMenuItem()
  
- v 0.1.0
  - change subMenuBackgroundColor attribute name to subMenuBackground
  - make subMenuBackground support drawable resource
  
- v 0.1.1
  - fix click area on sub menu
  - hardcoded font size for main bottom nav menu
  
- v 0.1.2
  - add get menuBackground View object so we can't connect it using constraint from view outside the library
  
- v 0.1.3
  - give access to bottom nav menu item view.
  
- v 0.1.4
  - fix add external view caused imageIcon cannot on state selected.
    Caused :
    selected wrong View after add external view to Menu Container (ex: add a badgeView icon), so setSelected run on wrong view .
    
    Solution :
    change algorithm selection by ViewId
    
- v 0.1.5
  - adjustment dimen fot bottom navigation text and height size on small device