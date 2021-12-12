package com.nhom02.g702;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText edtID, edtTen, edtNhaXB, edtSoLan, edtGia;

    Button btnChupHinh, btnLuu, btnTroVe;


    ImageView imvAnh;

    BottomSheetDialog sheetDialog = null;

    LinearLayout sheetOpenCamera,sheetOpenGallery;

    ActivityResultLauncher<Intent> activityResultLauncher;

    boolean isCamera;

    public static MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();
        addEvents();
        createBottomSheet();

        db=new MyDataBase(this);


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                    if(isCamera){
                        //Lấy dữ liệu hình từ Camera
                        Bitmap bitmap=(Bitmap) result.getData().getExtras().get("data");
                        imvAnh.setImageBitmap(bitmap);
                    }else{
                        //Lấy dữ liệu hình từ Gallery
                        Uri uri = result.getData().getData();
                        if(uri!=null){
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                                imvAnh.setImageBitmap(bitmap);
                                //File không tồn tại, hoặc không hợp lệ
                            } catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        });
    }

    private void linkViews() {
        edtID = findViewById(R.id.edtID);
        edtTen = findViewById(R.id.edtTen);
        edtGia= findViewById(R.id.edtGia);
        edtNhaXB= findViewById(R.id.edtNhaXB);
        edtSoLan= findViewById(R.id.edtSoLan);
        btnChupHinh=findViewById(R.id.btnChupHinh);
        btnLuu=findViewById(R.id.btnLuu);
        btnTroVe=findViewById(R.id.btnTroVe);
        imvAnh=findViewById(R.id.imvBook);
    }

    private void addEvents() {
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open bottom sheet
                sheetDialog.show();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id, ten, gia, nhaxb, solan;
                id = edtID.getText().toString();
                ten=edtTen.getText().toString();
                gia=edtGia.getText().toString();
                nhaxb=edtNhaXB.getText().toString();
                solan=edtSoLan.getText().toString();
                if(!ten.equals("")&& !gia.equals("")&& !nhaxb.equals("")&& !solan.equals("")&& !id.equals("")){
                    boolean flag = db.insertData(id, ten, nhaxb, solan, gia, convertPhoto());
                    if(flag==true){
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        //Open list Activity
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private byte[] convertPhoto() {
        //lấy hình trong ImageView trả về Byte
        BitmapDrawable drawable=(BitmapDrawable) imvAnh.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }

    private void createBottomSheet() {
        if(sheetDialog==null){
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet,null);
            sheetOpenCamera = view.findViewById(R.id.sheetOpenCamera);
            sheetOpenCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Open Camera
                    isCamera=true;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                }
            });
            sheetOpenGallery=view.findViewById(R.id.sheetOpenGallery);
            sheetOpenGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Open Photo gallery
                    isCamera=false;
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                }
            });
            sheetDialog = new BottomSheetDialog(this);
            sheetDialog.setContentView(view);
        }
    }

}