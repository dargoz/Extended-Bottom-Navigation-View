package com.example.extendedbottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_menu)
        bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 1)
    }
}
