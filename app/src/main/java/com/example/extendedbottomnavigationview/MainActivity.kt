package com.example.extendedbottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_menu)
        bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 1)
        bottomNavBar.setMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
        }
        /*bottomNavBar.setSubMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
        }*/
    }
}
