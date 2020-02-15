package com.dargoz.extendedbottomnavigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class BottomNavigationBar extends FrameLayout {
    private final String TAG = this.getClass().getSimpleName();
    private View rootView;
    private MenuInflater menuInflater;
    ConstraintLayout bottomNavBaseContainer;
    ImageView menuBackground;
    private Menu menu;
    private int currentSelectedItem;
    private int highlightMenuPosition = -1;
    private  MenuOnClickListener menuOnClickListener;


    public BottomNavigationBar(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BottomNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BottomNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressWarnings("unused")
    public Menu getMenu() {
        return menu;
    }

    @SuppressWarnings("unused")
    public void setMenuOnClickListener(MenuOnClickListener menuOnClickListener) {
        this.menuOnClickListener = menuOnClickListener;
    }

    @SuppressWarnings("unused")
    public void setHighlightMenuPosition(int position) {
        this.highlightMenuPosition = position;
        LinearLayout menuLayout = getMenuChildAt(position);
        TextView titleText = (TextView) menuLayout.getChildAt(1);
        titleText.setTextSize(TypedValue.DENSITY_DEFAULT,32);
        titleText.setSelected(true);
    }

    private void init(Context context) {
        rootView = LayoutInflater.from(context)
                .inflate(R.layout.bottom_navigation_base_layout,this);
        menu = new BottomNavigationMenu(context);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        setAttributes(context, attrs);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        bottomNavBaseContainer = rootView.findViewById(R.id.bottom_nav_base_container);
        TypedArray tmpArrStyleAttributes = getContext().getTheme()
                .obtainStyledAttributes(
                        attrs, R.styleable.BottomNavigationBar, 0, 0);

        int menuResId = tmpArrStyleAttributes
                .getResourceId(R.styleable.BottomNavigationBar_menu, 0);
        int menuBackgroundColor = tmpArrStyleAttributes
                .getResourceId(R.styleable.BottomNavigationBar_menuBackgroundColor, -1);
        int menuItemTextColor = tmpArrStyleAttributes
                .getResourceId(R.styleable.BottomNavigationBar_menuItemTextColor, -1);
        highlightMenuPosition = tmpArrStyleAttributes
                .getInt(R.styleable.BottomNavigationBar_highlightMenuPosition, -1);
        Log.i("DRG","menuResId : " + menuResId);
        this.getMenuInflater().inflate(menuResId, this.menu);
        for(int i=0; i<menu.size() ;i++) {

            Log.i("DRG","get bottom_nav_menu title : " + menu.getItem(i).getTitle());
        }
        buildMenu(menu, context);
        setMenuBackground(menuBackgroundColor);
        setItemsTextColor(menuItemTextColor);
        setSelectedMenuItem(0);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(this.getContext());
        }
        return this.menuInflater;
    }

    private void buildMenu(Menu menu, Context context) {
        bottomNavBaseContainer = rootView.findViewById(R.id.bottom_nav_base_container);
        addMenuToContainer(menu, context);
        setMenuConstraints(menu, bottomNavBaseContainer);
        constructBackground(context);
    }

    private void addMenuToContainer(final Menu menu, Context context) {

        for (int itemIndex = 0; itemIndex < menu.size() ; itemIndex++) {
            LinearLayout menuItemContainer = constructMenuItem(menu, context, itemIndex);
            bottomNavBaseContainer.addView(menuItemContainer);
        }
    }

    @NonNull
    private LinearLayout constructMenuItem(final Menu menu, Context context, int itemIndex) {



        TextView titleText = new TextView(context);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 0, (float) 0.3);
        titleParams.setMargins(
                0, getResources().getDimensionPixelSize(R.dimen.baseline_2dp),
                0 ,0);
        titleText.setLayoutParams(titleParams);
        titleText.setText(menu.getItem(itemIndex).getTitle());
        titleText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        titleText.setTextSize(TypedValue.DENSITY_DEFAULT,26);
        titleText.setTypeface(Typeface.DEFAULT_BOLD);
        titleText.setSelected(false);


        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, WRAP_CONTENT);
        imageView.setLayoutParams(imageParams);
        imageView.setImageDrawable(menu.getItem(itemIndex).getIcon());
        imageView.setSelected(false);

        return buildMenuItemLayout(menu, context, titleText, imageView, itemIndex);
    }

    @NonNull
    private LinearLayout buildMenuItemLayout(final Menu menu, Context context,
                                             TextView titleText, ImageView imageView, final int itemIndex) {
        LinearLayout menuItemContainer = new LinearLayout(context);
        menuItemContainer.setOrientation(LinearLayout.VERTICAL);
        menuItemContainer.addView(imageView);
        menuItemContainer.addView(titleText);
        menuItemContainer.setId(View.generateViewId());
        menuItemContainer.setGravity(Gravity.CENTER);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout
                .LayoutParams(0, WRAP_CONTENT);
        menuItemContainer.setLayoutParams(layoutParams);

        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.baseline_15dp);
        menuItemContainer.setPadding(0, padding, 0, padding);
        menuItemContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuOnClickListener != null) {
                    menuOnClickListener.onMenuItemClick(menu, itemIndex);
                }
            }
        });
        return menuItemContainer;
    }

    private void setMenuConstraints(Menu menu, ConstraintLayout bottomNavBaseContainer) {
        View previousItem = null;
        int[] viewIds = new int[bottomNavBaseContainer.getChildCount()];
        ConstraintSet constraintSet = new ConstraintSet();
        for (int menuIndex = 0; menuIndex < bottomNavBaseContainer.getChildCount(); menuIndex++) {
            constraintSet.clone(bottomNavBaseContainer);
            View menuItemContainer = bottomNavBaseContainer.getChildAt(menuIndex);
            constraintSet.connect(menuItemContainer.getId(),
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,
                    highlightMenuPosition == menuIndex
                            ? getContext().getResources().getDimensionPixelSize(R.dimen.baseline_4dp) : 0);
            constraintSet.applyTo(bottomNavBaseContainer);
        }
        for (int menuIndex = 0; menuIndex < bottomNavBaseContainer.getChildCount(); menuIndex++) {
            constraintSet.clone(bottomNavBaseContainer);
            View menuItemContainer = bottomNavBaseContainer.getChildAt(menuIndex);
            viewIds[menuIndex] = menuItemContainer.getId();
            Log.d("DRG","current id : " + menuItemContainer.getId());
            int id = previousItem != null ? previousItem.getId() : -1;

            Log.d("DRG","prev id : " + id);
            Log.w("DRG"," ===================== ");

            if(previousItem != null) {
                constraintSet.connect(
                        menuItemContainer.getId(), ConstraintSet.START,
                        previousItem.getId(), ConstraintSet.END,0);
            } else {
                constraintSet.connect(
                        menuItemContainer.getId(), ConstraintSet.START,
                        ConstraintSet.PARENT_ID, ConstraintSet.START,0);
            }

            if(menuIndex == menu.size() - 1) {
                constraintSet.connect(
                        menuItemContainer.getId(), ConstraintSet.END,
                        ConstraintSet.PARENT_ID, ConstraintSet.END,0);
            } else {
                if(previousItem != null) {
                    constraintSet.connect(
                            previousItem.getId(), ConstraintSet.END,
                            menuItemContainer.getId(), ConstraintSet.START,0);
                }
            }
            previousItem = menuItemContainer;

        }

        Log.v("DRG","view Ids : " + Arrays.toString(viewIds));


        constraintSet.createHorizontalChain(
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
                viewIds, null, ConstraintSet.CHAIN_SPREAD
        );

        constraintSet.applyTo(bottomNavBaseContainer);
    }

    private void constructBackground(Context context) {
        menuBackground = new ImageView(context);
        menuBackground.setId(View.generateViewId());
        ConstraintLayout.LayoutParams bgParams = new ConstraintLayout
                .LayoutParams(MATCH_PARENT, 0);
        menuBackground.setLayoutParams(bgParams);
        bottomNavBaseContainer.addView(menuBackground,0);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(bottomNavBaseContainer);
        constraintSet.connect(
                menuBackground.getId(), ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        constraintSet.connect(
                menuBackground.getId(), ConstraintSet.TOP,
                bottomNavBaseContainer.getChildAt(1).getId(), ConstraintSet.TOP, 0);
        constraintSet.constrainDefaultHeight(menuBackground.getId(), ConstraintSet.MATCH_CONSTRAINT_SPREAD);
        constraintSet.applyTo(bottomNavBaseContainer);
    }

    private void setMenuBackground(int drawable) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            menuBackground.setBackgroundColor(getResources().getColor(drawable, null));
        } else {
            menuBackground.setBackgroundColor(getResources().getColor(drawable));
        }
    }

    private void setItemsTextColor(int menuItemTextColor) {
        ColorStateList colorStateList = getResources().getColorStateList(menuItemTextColor);
        for(int i = 0; i < menu.size(); i++) {
            LinearLayout menuLayout = getMenuChildAt(i);
            TextView textView = (TextView) menuLayout.getChildAt(1);
            textView.setTextColor(colorStateList);
        }
    }

    public void setSelectedMenuItem(int itemIndex) {
        if(itemIndex != highlightMenuPosition) {
            try {
                setMenuSelected(currentSelectedItem, false);
                setMenuSelected(itemIndex, true);
                currentSelectedItem = itemIndex;
            } catch (Exception e) {
                Log.w(TAG ,"Exception on setSelectedMenuItem " + e.getMessage());
            }
        }
    }

    private void setMenuSelected(int itemIndex, boolean flagSelected) {
        LinearLayout menuLayout = getMenuChildAt(itemIndex);
        ImageView imageIcon = (ImageView) menuLayout.getChildAt(0);
        TextView titleText = (TextView) menuLayout.getChildAt(1);
        imageIcon.setSelected(flagSelected);
        titleText.setSelected(flagSelected);
    }

    private LinearLayout getMenuChildAt(int itemIndex) {
        return (LinearLayout) bottomNavBaseContainer.getChildAt(itemIndex + 1);
    }

}
