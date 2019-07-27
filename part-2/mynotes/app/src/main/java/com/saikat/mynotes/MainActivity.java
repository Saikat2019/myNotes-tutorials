package com.saikat.mynotes;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TabLayout tabLayout;
    private TextView nav_header_name;
    private TextView nav_header_email;

    private final String TAG = "XXXMainActivity";

    private int[] tabIcons = { R.drawable.ic_notes_gray, R.drawable.ic_notifications_gray };
    private int[] tabLabels = { R.string.notes_tab, R.string.notifications_tab };
    private int[] tabActiveIcons = { R.drawable.ic_notes_white, R.drawable.ic_notifications_white };

    private DrawerLayout drawer;

    private String user_id;
    private String user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.nav_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.viewpager_main);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);

        tabLayout = findViewById(R.id.tabLayout_main);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_notes_gray);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_gray);

        setCustomTab();
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //this method changes the selected tab icon & img to bright white
                changeToTabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //this method changes the selected tab icon & img back to gray
                changeToTabUnselected(tab);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void setCustomTab(){

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_single, null);

            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label =  tab.findViewById(R.id.nav_label);
            ImageView tab_icon =  tab.findViewById(R.id.nav_icon);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.setText(getResources().getString(tabLabels[i]));

            // set the Notes to be active at first
            if(i == 0) {
                tab_label.setTextColor(getResources().getColor(R.color.BrightWhiteColor));
                tab_icon.setImageResource(tabActiveIcons[i]);
            } else {
                tab_label.setTextColor(getResources().getColor(R.color.GrayColor));
                tab_icon.setImageResource(tabIcons[i]);
            }

            // finally publish this custom view to navigation tab
            tabLayout.getTabAt(i).setCustomView(tab);
        }

    }


    private void changeToTabSelected(TabLayout.Tab tab) {
        View tabView = tab.getCustomView();

        // get inflated children Views the icon and the label by their id
        TextView tab_label = tabView.findViewById(R.id.nav_label);
        ImageView tab_icon = tabView.findViewById(R.id.nav_icon);

        // change the label color, by getting the color resource value
        tab_label.setTextColor(getResources().getColor(R.color.BrightWhiteColor));
        // change the image Resource
        // i defined all icons in an array ordered in order of tabs appearances
        // call tab.getPosition() to get active tab index.
        tab_icon.setImageResource(tabActiveIcons[tab.getPosition()]);
    }

    private void changeToTabUnselected(TabLayout.Tab tab) {
        View tabView = tab.getCustomView();
        TextView tab_label =  tabView.findViewById(R.id.nav_label);
        ImageView tab_icon =  tabView.findViewById(R.id.nav_icon);

        // back to the black color
        tab_label.setTextColor(getResources().getColor(R.color.GrayColor));
        // and the icon resouce to the old black image
        // also via array that holds the icon resources in order
        // and get the one of this tab's position
        tab_icon.setImageResource(tabIcons[tab.getPosition()]);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
