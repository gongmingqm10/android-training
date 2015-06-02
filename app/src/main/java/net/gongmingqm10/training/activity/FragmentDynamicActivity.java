package net.gongmingqm10.training.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.fragment.TabAFragment;
import net.gongmingqm10.training.fragment.TabCFragment;

public class FragmentDynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample);

        addFragmentDynamic(savedInstanceState);
    }

    private void addFragmentDynamic(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) == null || savedInstanceState != null) return;
        TabCFragment tabCFragment = new TabCFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, tabCFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fragment_replace) {
            replaceFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new TabAFragment());
        transaction.commit();
    }
}
