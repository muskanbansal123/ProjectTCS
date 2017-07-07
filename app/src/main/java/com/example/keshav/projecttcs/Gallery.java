package com.example.keshav.projecttcs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by keshav on 29-06-2017.
 */

public class Gallery extends MainActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    Button btnTakePhoto;
    ImageView imgTakePhoto;
    private static final int CAM_REQUEST = 1313;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        btnTakePhoto = (Button) findViewById(R.id.btn_camera);
        imgTakePhoto = (ImageView) findViewById(R.id.imgView);

        if (!hasCamera())
{
    btnTakePhoto.setEnabled(false);
}
        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());

    }

    private boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST)
        {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgTakePhoto.setImageBitmap(thumbnail);

        }

    }

    class btnTakePhotoClicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v){
            Intent cameraintent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent,CAM_REQUEST);
        }
    }


    /*public void onButtonClick(View v)
    {
            if (v.getId() == R.id.btn_camera) {
                Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraintent, CAM_REQUEST);
            }

    }*/

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

}
