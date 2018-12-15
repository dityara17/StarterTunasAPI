package com.qakap.tunas.tunasqakap.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
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

public class AddBerita extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_berita);
        ButterKnife.bind(this);

        save_berita.setOnClickListener(v -> {
            String title, deskripsi, penulis;

            title = title_berita.getText().toString().trim();
            deskripsi = isi_berita.getText().toString().trim();
            penulis = author_berita.getText().toString().trim();

            postBerita(title, deskripsi, penulis);

        });

    }

    private void postBerita(String title, String deskripsi, String penulis) {
        final ApiService apiServices = RetroClient.postBerita();
        apiServices.postBerita(1, title, deskripsi, penulis).enqueue(new Callback<BeritaModel>() {
            @Override
            public void onResponse(Call<BeritaModel> call, Response<BeritaModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddBerita.this, "Tambah berita berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BeritaModel> call, Throwable t) {

            }
        });
    }
}
