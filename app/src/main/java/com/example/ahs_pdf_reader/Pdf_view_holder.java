package com.example.ahs_pdf_reader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Pdf_view_holder extends RecyclerView.ViewHolder {
    public TextView textView_name;
    public CardView Container;

    public Pdf_view_holder(@NonNull View itemView) {
        super(itemView);
        textView_name=itemView.findViewById(R.id.pdf_name);
        Container=itemView.findViewById(R.id.Container);
    }
}
