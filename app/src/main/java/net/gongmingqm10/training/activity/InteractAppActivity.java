package net.gongmingqm10.training.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.gongmingqm10.training.R;

import org.apache.http.protocol.HTTP;

import java.net.HttpURLConnection;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class InteractAppActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST_CODE = 100;

    @InjectView(R.id.actions_list)
    protected ListView actionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact_app);
        ButterKnife.inject(this);
        initActionList();
    }

    private void initActionList() {
        String[] texts = new String[]{
                "Dial phone number",
                "Navigate map",
                "Open web page",
                "Send an email",
                "Create calendar event",
                "Pick contact"
        };
        actionsList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, texts));
    }

    @OnItemClick(R.id.actions_list)
    protected void onActionClick(int position) {
        switch(position) {
            case 0:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789")));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California")));
                break;
            case 2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/")));
                break;
            case 3:
                startEmailIntent();
                break;
            case 4:
                createCalendarEvent();
                break;
            case 5:
                pickContact();
                break;
        }
    }

    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, PICK_CONTACT_REQUEST_CODE);
    }

    private void createCalendarEvent() {
        Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 5, 1, 9, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 5, 1, 10, 0);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.TITLE, "Month birthday");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home");

        startActivity(intent);
    }

    private void startEmailIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"gongmingqm10@foxmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Test Email Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "This is the testing email from Android training");

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getContentResolver().query(contactUri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Toast.makeText(this, "Phone number: " + phoneNumber, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_interact_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
