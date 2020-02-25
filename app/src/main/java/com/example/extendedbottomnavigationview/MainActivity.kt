package com.example.extendedbottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar
import com.dargoz.extendedbottomnavigationview.menu.SubMenuOrientation

class MainActivity : AppCompatActivity() {
    var visibility = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_menu)
        bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 1, SubMenuOrientation.VERTICAL)
        bottomNavBar.setSubMenuTextColor(R.color.colorPrimaryDark)
        bottomNavBar.setMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
            visibility = !visibility
            bottomNavBar.showSubMenu(position, visibility)
        }
        bottomNavBar.setSubMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
        }
    }
}
