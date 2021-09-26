package com.example.ahs_pdf_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ShowPdf extends AppCompatActivity {

    String File_path="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);
        PDFView pdfView=findViewById(R.id.pdfView);
        File_path=getIntent().getStringExtra("path");
        File file =new File(File_path);
        Uri path =Uri.fromFile(file);
        pdfView.fromUri(path).load();
    }
}