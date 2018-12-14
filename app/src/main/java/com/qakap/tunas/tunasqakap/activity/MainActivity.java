package com.qakap.tunas.tunasqakap.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.qakap.tunas.tunasqakap.R;
import com.qakap.tunas.tunasqakap.adapter.BeritaAdapter;
import com.qakap.tunas.tunasqakap.client.ApiService;
import com.qakap.tunas.tunasqakap.client.RetroClient;
import com.qakap.tunas.tunasqakap.model.BeritaModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rec_news)
    RecyclerView rec_news;
    @BindView(R.id.addberita)
    Button addberita;
    @BindView(R.id.refresh)
    Button refresh;
    ArrayList<BeritaModel> beritaModelArrayList;
    BeritaAdapter beritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addberita.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBerita.class);
            startActivity(intent);

        });

        refresh.setOnClickListener(v -> {
            getBerita();
        });

        getBerita();


    }

    private void getBerita() {
        final ApiService apiServices = RetroClient.getBerita();
        apiServices.getBerita().enqueue(new Callback<ArrayList<BeritaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BeritaModel>> call, Response<ArrayList<BeritaModel>> response) {
                if (response.isSuccessful()) {
                    beritaModelArrayList = new ArrayList<>();
                    beritaModelArrayList = response.body();
                    beritaAdapter = new BeritaAdapter(MainActivity.this, beritaModelArrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    rec_news.setLayoutManager(layoutManager);
                    rec_news.setAdapter(beritaAdapter);


                }
            }

            @Override
            public void onFailure(Call<ArrayList<BeritaModel>> call, Throwable t) {

            }
        });
    }
}
