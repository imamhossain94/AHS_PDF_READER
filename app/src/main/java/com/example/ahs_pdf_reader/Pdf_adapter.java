package com.example.ahs_pdf_reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class Pdf_adapter extends RecyclerView.Adapter<Pdf_view_holder> {
    private Context context;
    private List<File> Pdf_File;
    private OnPdfFileSelectListenrt onPdfFileSelectListenrt;

    public Pdf_adapter(Context context, List<File> pdf_File, OnPdfFileSelectListenrt onPdfFileSelectListenrt) {
        this.context = context;
        this.Pdf_File = pdf_File;
        this.onPdfFileSelectListenrt = onPdfFileSelectListenrt;
    }

    @NonNull
    @Override
    public Pdf_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Pdf_view_holder(LayoutInflater.from(context).inflate(R.layout.pdf_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Pdf_view_holder holder, int position) {
        holder.textView_name.setText(Pdf_File.get(position).getName());
        holder.textView_name.setSelected(true);
        holder.Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             onPdfFileSelectListenrt.OnPdfSelectedListener(Pdf_File.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Pdf_File.size();
    }
}
