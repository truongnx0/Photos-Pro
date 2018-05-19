package com.example.ngoch.sqlite_image;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageHinh;
    ImageButton btnCamera,btnFolder;
    GridView listHinh;
    HinhAdapter adapter;
    ArrayList<Hinh> hinhArrayList;
    int REQUEST_FOLDER = 123,REQUEST_CAMERA = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CAMERA);*/
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                        Manifest.permission.CAMERA
                },REQUEST_CAMERA);
            }
        });
        btnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_FOLDER);
            }
        });
        listHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hinh hinh = hinhArrayList.get(position);
                imageHinh.setImageBitmap(hinh.getBitmap());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_FOLDER && resultCode == RESULT_OK && data != null){
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageHinh.setImageBitmap(bitmap);
                Hinh hinh = new Hinh( bitmap);
                hinhArrayList.add(0,hinh);
                adapter.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageHinh.setImageBitmap(bitmap);
            Hinh hinh = new Hinh(bitmap);
            hinhArrayList.add( 0,hinh);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CAMERA);
        }
    }

    private void anhXa() {
        imageHinh = (ImageView) findViewById(R.id.imageHinh);
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnFolder = (ImageButton) findViewById(R.id.btnFolder);
        listHinh = (GridView) findViewById(R.id.listHinh);
        hinhArrayList = new ArrayList<>();
        adapter = new HinhAdapter(MainActivity.this,R.layout.dong_layout_list,hinhArrayList);
        listHinh.setAdapter(adapter);
    }
}
