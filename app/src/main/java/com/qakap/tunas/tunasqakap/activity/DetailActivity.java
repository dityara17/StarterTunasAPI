package com.qakap.tunas.tunasqakap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qakap.tunas.tunasqakap.R;
import com.qakap.tunas.tunasqakap.client.ApiService;
import com.qakap.tunas.tunasqakap.client.RetroClient;
import com.qakap.tunas.tunasqakap.model.BeritaModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.qakap.tunas.tunasqakap.Config.EXTRA_ID;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.nama_berita)
    TextView nama_berita;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.detail)
    TextView detail;
    BeritaModel beritaModel;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.title_berita)
    EditText title_berita;
    @BindView(R.id.isi_berita)
    EditText isi_berita;
    @BindView(R.id.author_berita)
    EditText author_berita;
    @BindView(R.id.save_berita)
    Button save_berita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final int extraID = Integer.parseInt(intent.getStringExtra(EXTRA_ID));

        getBeritafromID(extraID);

        delete.setOnClickListener(v -> {
            deleteBerita(extraID);
        });

        save_berita.setOnClickListener(v -> {
            String nama_edit, berita_edit, author_edit;
            nama_edit = title_berita.getText().toString().trim();
            berita_edit = isi_berita.getText().toString().trim();
            author_edit = author_berita.getText().toString().trim();

            final ApiService apiServices = RetroClient.updateBerita();
            apiServices.updateBerita(extraID, nama_edit, berita_edit,author_edit).enqueue(new Callback<BeritaModel>() {
                @Override
                public void onResponse(Call<BeritaModel> call, Response<BeritaModel> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(DetailActivity.this, "Berhasil edit berita", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }

                @Override
                public void onFailure(Call<BeritaModel> call, Throwable t) {

                }
            });
        });
    }

    private void deleteBerita(int extraID) {
        final ApiService apiServices = RetroClient.deleteBerita();
        apiServices.deleteBerita(extraID).enqueue(new Callback<BeritaModel>() {
            @Override
            public void onResponse(Call<BeritaModel> call, Response<BeritaModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Berhasil hapus berita", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<BeritaModel> call, Throwable t) {

            }
        });
    }

    private void getBeritafromID(int extraID) {
        final ApiService apiServices = RetroClient.getBeritaFromID();
        apiServices.getBeritaFromID(extraID).enqueue(new Callback<BeritaModel>() {
            @Override
            public void onResponse(Call<BeritaModel> call, Response<BeritaModel> response) {
                beritaModel = response.body();
                if (response.isSuccessful()) {
                    nama_berita.setText(beritaModel.getName());
                    title_berita.setText(beritaModel.getName());
                    author.setText(beritaModel.getAuthor());
                    author_berita.setText(beritaModel.getAuthor());
                    detail.setText(beritaModel.getDescription());
                    isi_berita.setText(beritaModel.getDescription());
                }
            }

            @Override
            public void onFailure(Call<BeritaModel> call, Throwable t) {

            }
        });
    }
}
