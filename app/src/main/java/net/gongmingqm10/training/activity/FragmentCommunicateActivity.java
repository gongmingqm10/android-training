package net.gongmingqm10.training.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.fragment.CommunicateFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentCommunicateActivity extends AppCompatActivity implements CommunicateFragment.OnFragmentInteractionListener {

    @InjectView(R.id.host_message_edit)
    protected EditText messageEdit;

    private CommunicateFragment communicateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_communicate);
        ButterKnife.inject(this);

        loadFragment(savedInstanceState);
    }

    private void loadFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) return;
        communicateFragment = CommunicateFragment.newInstance("Ming", "Gong");
        getSupportFragmentManager().beginTransaction().add(R.id.communicate_fragment_container, communicateFragment).commit();
    }

    @Override
    public void onFragmentInteraction(String message) {
        messageEdit.setText(message);
    }

    @OnClick(R.id.host_send_message)
    protected void sendMessage(View view) {
        String message = messageEdit.getText().toString();
        communicateFragment.receiveMessage(message);

    }
}
