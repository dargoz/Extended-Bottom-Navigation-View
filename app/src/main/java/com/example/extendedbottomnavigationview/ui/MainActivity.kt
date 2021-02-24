package com.example.extendedbottomnavigationview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.dargoz.extendedbottomnavigationview.BottomNavigationBar
import com.dargoz.extendedbottomnavigationview.menu.SubMenuOrientation
import com.example.extendedbottomnavigationview.R
import com.example.extendedbottomnavigationview.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var visibility = false
    private var flagInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter =
            ViewPagerAdapter(
                supportFragmentManager,
                ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )

        val bottomNavBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_menu)
        bottomNavBar.viewTreeObserver.addOnGlobalLayoutListener {
            runOnUiThread {
                initViewPosition(bottomNavBar)
            }
        }
        bottomNavBar.replaceMenuTextToImage(2, R.drawable.baseline_credit_card_black_18)
        bottomNavBar.setHighlightMenuPosition(2)
        bottomNavBar.addSubMenu(R.menu.sub_menu_navigation_list, 4, SubMenuOrientation.VERTICAL)
        bottomNavBar.setSubMenuTextColor(R.color.colorPrimaryDark)
        bottomNavBar.showSubMenu(4, false)

        bottomNavBar.setMenuOnClickListener { menu, position ->
            Log.i("DRG", "menu : ${menu.getItem(position).title} :: pos : $position")
            viewPager.setCurrentItem(position, false)
            bottomNavBar.setSelectedMenuItem(position)

            visibility = if(position == 4) {
                !visibility
            } else {
                false
            }
            bottomNavBar.showSubMenu(4, visibility)
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
