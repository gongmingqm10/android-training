package net.gongmingqm10.training.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.fragment.MainFragment;
import net.gongmingqm10.training.fragment.TabAFragment;
import net.gongmingqm10.training.fragment.TabBFragment;
import net.gongmingqm10.training.fragment.TabCFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @InjectView(R.id.navigation_view)
    protected NavigationView navigationView;

    @InjectView(R.id.tool_bar)
    protected Toolbar toolBar;

    private MainFragment mainFragment;
    private TabAFragment tabAFragment;
    private TabBFragment tabBFragment;
    private TabCFragment tabCFragment;
    private String[] drawerTitle;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolBar);
        setUpDrawer();

        updateFragment(0);
    }

    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolBar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        drawerTitle = new String[]{"Main Fragment", "Tab A Fragment", "Tab B Fragment", "Tab C Fragment"};
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawer(navigationView);
                switch (menuItem.getItemId()) {
                    case R.id.main_fragment_item:
                        updateFragment(0);
                        return true;
                    case R.id.a_fragment_item:
                        updateFragment(1);
                        return true;
                    case R.id.b_fragment_item:
                        updateFragment(2);
                        return true;
                    case R.id.c_fragment_item:
                        updateFragment(3);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.floating_action_btn)
    protected void clickFAB(View view) {
        Snackbar.make(findViewById(R.id.coordinator_view),
                "This item already has the label \"travel\". You can add a new label.",
                Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }

    private void updateFragment(int position) {
        setTitle(drawerTitle[position]);
        Fragment fragment = getFragmentByIndex(position);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    private Fragment getFragmentByIndex(int index) {
        switch (index) {
            case 0:
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                }
                return mainFragment;
            case 1:
                if (tabAFragment == null) {
                    tabAFragment = new TabAFragment();
                }
                return tabAFragment;
            case 2:
                if (tabBFragment == null) {
                    tabBFragment = new TabBFragment();
                }
                return tabBFragment;
            case 3:
                if (tabCFragment == null) {
                    tabCFragment = new TabCFragment();
                }
                return tabCFragment;
        }
        return null;
    }

}
