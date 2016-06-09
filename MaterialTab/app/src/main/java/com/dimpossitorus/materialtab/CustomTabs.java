package com.dimpossitorus.materialtab;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomTabs extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbarCustom);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpagerCustom);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsCustom);
        tabLayout.setupWithViewPager(viewPager);

        TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab.setText("Tab 1");
        tab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tab_favorite,0,0,0);
        tabLayout.getTabAt(0).setCustomView(tab);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setText("Tab 2");
        tab2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_tab_call,0,0);
        tabLayout.getTabAt(1).setCustomView(tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setText("Tab 3");
        tab3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_tab_contact,0,0);
        tabLayout.getTabAt(2).setCustomView(tab3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager _viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i=0; i<3; i++) {
            adapter.addFragment (new SimpleUIFragment(), "TAB "+i);
        }
        _viewPager.setAdapter(adapter);
    }
}
