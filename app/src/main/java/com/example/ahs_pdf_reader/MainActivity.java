package com.example.ahs_pdf_reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnPdfFileSelectListenrt {

    private Pdf_adapter pdf_adapter;
    private List<File> Pdf_list;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Run_Time_permission();
    }

    private void Run_Time_permission() {
        Dexter.withContext(MainActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Show_Pdf();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(MainActivity.this, "Permission Is Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    public ArrayList<File> Find_Pdf(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File SingleFile : files) {
            if (SingleFile.isDirectory() && !SingleFile.isHidden()) {
                arrayList.addAll(Find_Pdf(SingleFile));
            } else {
                if (SingleFile.getName().endsWith(".pdf")) {
                    arrayList.add(SingleFile);
                }
            }
        }
        return arrayList;
    }

    private void Show_Pdf() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        Pdf_list = new ArrayList<>();
        Pdf_list.addAll(Find_Pdf(Environment.getExternalStorageDirectory()));
        pdf_adapter = new Pdf_adapter(this, Pdf_list, this);
        recyclerView.setAdapter(pdf_adapter);
    }

    @Override
    public void OnPdfSelectedListener(File file) {
        startActivity(new Intent(MainActivity.this,ShowPdf.class).putExtra("path",file.getAbsolutePath()));
    }
}