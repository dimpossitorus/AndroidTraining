package com.dimpossitorus.internal_externalstorage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int EXTERNAL_STORAGE = 1;
    private static final int INTERNAL_STORAGE = 2;
    private String internalFilename = "myfile";
    private Context context;

    // Photo Directory
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button internalStorage = (Button) findViewById(R.id.internalStorage);
        internalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "Hello world!";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(internalFilename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                    Toast.makeText(MainActivity.this, "Saved to Internal Storage", Toast.LENGTH_LONG).show();
                    TextView fileContent = (TextView) findViewById(R.id.fileContent);
                    FileInputStream inputStream;

                    try {
                        inputStream = openFileInput(internalFilename);
                        byte[] buffer = new byte[inputStream.available()];// This is not safe
                        inputStream.read(buffer);
                        String str = new String(buffer, "UTF-8");
                        fileContent.setText("File name : "+internalFilename+"\n"+"Content : "+str);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed to save to Internal Storage", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button externalStorage = (Button) findViewById(R.id.externalStorage);
        externalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (photoFile!=null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        Log.d("DEBUG", photoFile.toString());
                        startActivityForResult(cameraIntent, EXTERNAL_STORAGE);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == EXTERNAL_STORAGE) {
            //do something
            Toast.makeText(MainActivity.this, "Saved to External", Toast.LENGTH_LONG).show();
            galleryAddPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name'
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_"+timeStamp+"_";
        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                mediaStorageDir /* directory */
        );

        // Save a file : path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:"+image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
