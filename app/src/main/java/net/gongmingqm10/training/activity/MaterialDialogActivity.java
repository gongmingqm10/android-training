package net.gongmingqm10.training.activity;

import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.fragment.FullScreenDialogFragment;
import net.gongmingqm10.training.fragment.MaterialDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialog);
        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_material_dialog, menu);
        return true;
    }

    @OnClick(R.id.small_dialog_btn)
    protected void launchSmallDialog(View view) {
        DialogFragment fragment = new MaterialDialogFragment();

        fragment.show(getSupportFragmentManager(), "fragmentid");

    }

    @OnClick(R.id.full_screen_dialog_btn)
    protected void launchFullScreenDialog(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, new FullScreenDialogFragment()).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
