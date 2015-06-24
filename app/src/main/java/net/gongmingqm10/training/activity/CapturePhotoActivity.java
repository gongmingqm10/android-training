package net.gongmingqm10.training.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import net.gongmingqm10.training.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CapturePhotoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 100;

    @InjectView(R.id.image_view)
    protected ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photo);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.capture_photo_btn)
    protected void clickTakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFile();
        if (photoFile != null || takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE & resultCode == RESULT_OK && data != null) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(imageBitmap);
            galleryAddFile();
        }
    }

    private void galleryAddFile() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(new File(currentPhotoPath));
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    private String currentPhotoPath;

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            File image = File.createTempFile(imageFileName, ".jpg", storageDir);
            currentPhotoPath = "file:" + image.getAbsolutePath();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
