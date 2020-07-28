package com.example.extendedbottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.updateLayoutParams
import androidx.viewpager.widget.ViewPager
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar.SELECTED_NONE
import com.dargoz.extendedbottomnavigationview.menu.SubMenuOrientation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var visibility = true
    private var flagInit = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager,
            ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        val bottomNavBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_menu)
        bottomNavBar.viewTreeObserver.addOnGlobalLayoutListener {
            runOnUiThread {
                initViewPosition(bottomNavBar)
            }
        }
        bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 1, SubMenuOrientation.VERTICAL)
        bottomNavBar.setSubMenuTextColor(R.color.colorPrimaryDark)

        bottomNavBar.setMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
            viewPager.setCurrentItem(position, false)
            bottomNavBar.setSelectedMenuItem(position)

            if(position == 1) {
                visibility = !visibility
                bottomNavBar.showSubMenu(position, visibility)
            } else {
                visibility = false
                bottomNavBar.showSubMenu(1, false)
            }
        }
        bottomNavBar.setSubMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
        }
    }

    private fun initViewPosition(bottomNavBar: BottomNavigationBar) {
        if (!flagInit && bottomNavBar.menuBackground.height != 0) {
            flagInit = true
            val rootLayout = findViewById<RelativeLayout>(R.id.root_layout)
            val imageMask = ImageView(this)
            imageMask.id = View.generateViewId()
            val param = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                bottomNavBar.menuBackground.height
            )
            param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)

            imageMask.layoutParams = param

            rootLayout.addView(imageMask, 0)

            val viewPagerLayoutParam: RelativeLayout.LayoutParams =
                view_pager.layoutParams as RelativeLayout.LayoutParams

            viewPagerLayoutParam.apply {
                addRule(RelativeLayout.ABOVE, imageMask.id) }
        }

    }

}
