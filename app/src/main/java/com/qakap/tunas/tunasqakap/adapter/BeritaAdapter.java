package com.qakap.tunas.tunasqakap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qakap.tunas.tunasqakap.R;
import com.qakap.tunas.tunasqakap.model.BeritaModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {

    private Context context;
    private List<BeritaModel> beritaModels;


    public BeritaAdapter(Context context, ArrayList<BeritaModel> beritaModels) {
        this.context = context;
        this.beritaModels = beritaModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_news,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nama_berita.setText(beritaModels.get(i).getName());
        myViewHolder.author.setText(beritaModels.get(i).getAuthor());
    }

    @Override
    public int getItemCount() {
        return beritaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nama_berita)
        TextView nama_berita;
        @BindView(R.id.author)
        TextView author;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
