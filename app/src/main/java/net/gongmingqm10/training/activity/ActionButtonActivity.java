package net.gongmingqm10.training.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.gongmingqm10.training.R;

public class ActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_button, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));

        setUpSearchView(searchView);
        setUpShareProvider(shareActionProvider);
        return true;
    }

    private void setUpShareProvider(ShareActionProvider shareActionProvider) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        shareActionProvider.setShareIntent(intent);
    }

    private void setUpSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ActionButtonActivity.this, "Text submit event: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(ActionButtonActivity.this, "Text change event: " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

}
