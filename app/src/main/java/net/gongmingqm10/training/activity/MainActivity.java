package net.gongmingqm10.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.gongmingqm10.training.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.list)
    protected ListView featureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        inflateListView();
    }

    private void inflateListView() {
        String[] texts = new String[]{
                "ActionBar Tab Fragment",
                "Dynamic Fragment",
                "Communicate with Fragments"
        };
        featureList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts));

    }

    @OnItemClick(R.id.list)
    protected void openPage(int position) {
        switch(position) {
            case 0:
                startActivity(new Intent(this, ActionTabActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, FragmentDynamicActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, FragmentCommunicateActivity.class));
                break;
            default:
                break;
        }
    }

}
