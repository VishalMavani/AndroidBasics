package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Bansi..!! on 05-07-2016.
 */
public class Camera extends Activity implements View.OnClickListener{
    ImageView wallPaperImage;
    ImageButton takePicture;
    Button setWallPaper;
    Intent i;
    Bitmap bm;
    final static int cameraData = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        initialize();
    }

    private void initialize() {
        wallPaperImage = (ImageView) findViewById(R.id.ivWallPaper);
        takePicture = (ImageButton) findViewById(R.id.ibTakePicture);
        setWallPaper = (Button) findViewById(R.id.bSetWallPaper);
        takePicture.setOnClickListener(this);
        setWallPaper.setOnClickListener(this);
        InputStream is = getResources().openRawResource(+R.drawable.img1);
        bm = BitmapFactory.decodeStream(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibTakePicture:
                onTakePictureClick();
                break;
            case R.id.bSetWallPaper:
                onSetWallPaperClick();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle resultData = data.getExtras();
            bm = (Bitmap) resultData.get("data");
            wallPaperImage.setImageBitmap(bm);
        }
    }

    private void onTakePictureClick() {
        i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, cameraData);
    }

    private void onSetWallPaperClick() {
        try {
            getApplicationContext().setWallpaper(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
