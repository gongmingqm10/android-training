package net.gongmingqm10.training.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.gongmingqm10.training.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ShareDataActivity extends AppCompatActivity {

    @InjectView(R.id.share_data_list)
    protected ListView shareList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data);
        ButterKnife.inject(this);
        handleShareIncomeEvent();
        initShareList();
    }

    private void handleShareIncomeEvent() {
        String type = getIntent().getType();
        if (type == null) return;

        switch (getIntent().getAction()) {
            case Intent.ACTION_SEND:
                if ("text/plain".equals(type)) {
                    String shareText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                    Toast.makeText(this, "Receive the share text: " + shareText, Toast.LENGTH_SHORT).show();
                } else if (type.startsWith("image/")) {
                    Uri imageUri = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
                }
                break;
            case Intent.ACTION_SEND_MULTIPLE:
                if (type.startsWith("image/")) {
                    ArrayList<Uri> imageUris = getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                }
                break;
        }
    }

    private void initShareList() {
        String[] texts = new String[]{
                "Send text content"
        };
        shareList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts));
    }

    @OnItemClick(R.id.share_data_list)
    protected void itemClick(int position) {
        switch (position) {
            case 0:
                shareText();
                break;
        }
    }

    private void shareText() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Sent to"));
    }

}
