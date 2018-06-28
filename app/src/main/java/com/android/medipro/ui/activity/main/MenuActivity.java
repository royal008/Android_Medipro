package com.android.medipro.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.ui.fragments.home.HomeFragment;


public class MenuActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ListView lvMenuList;
    View main_content;
    MenuListAdapter menuListAdapter;
    String menuList[]={"My Orders","My Appointments","Offers","Reminder","Profile update","Invite to Earn","Contact Us","Log Out"};
    int menuImages[]={R.drawable.myorders_menu, R.drawable.myappointments_menu,R.drawable.offers,R.drawable.notifications_menu,
            R.drawable.profile_menu,R.drawable.invitetoearn_menu,R.drawable.contactus_menu,R.drawable.logout_menu};
    public static ImageView ivMenuLines,ivDownArrow,ivSearch,ivCart;
    public static LinearLayout llMenu,llTopBar;
    public static TextView tvPlaceName,tvCityName;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getView();
        setFragment(0);

    }

    private void setFragment(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment=new HomeFragment();
                break;
        }
        setMainFragment(fragment);
        if (drawerLayout.isDrawerOpen(llMenu)) {
            drawerLayout.closeDrawer(llMenu);
        }
    }

    private void setMainFragment(Fragment fragment) {
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.fl_container_main,fragment);
        ft.commit();
    }

    private void getView() {
        lvMenuList=(ListView)findViewById(R.id.lv_menu_list);
        menuListAdapter=new MenuListAdapter(this,menuList,menuImages);
        lvMenuList.setAdapter(menuListAdapter);
        ivMenuLines=(ImageView)findViewById(R.id.iv_menu_lines);
        llMenu=(LinearLayout)findViewById(R.id.ll_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_menu);
        llTopBar=(LinearLayout) findViewById(R.id.ll_top_bar);
        tvPlaceName=(TextView)findViewById(R.id.tv_place_name);
        tvCityName=(TextView)findViewById(R.id.tv_city_name);
//        ivDownArrow=(ImageView)findViewById(R.id.iv_down_arrow);
        ivSearch=(ImageView)findViewById(R.id.iv_search);
        ivCart=(ImageView)findViewById(R.id.iv_cart);

        main_content = findViewById(R.id.main_content);

        ivMenuLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggle();
            }
        });


        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                main_content.setX(drawerView.getWidth() * slideOffset);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void toggle() {
        if (drawerLayout.isDrawerOpen(llMenu)) {
            drawerLayout.closeDrawer(llMenu);
        } else {
            drawerLayout.openDrawer(llMenu);
        }
    }
}
