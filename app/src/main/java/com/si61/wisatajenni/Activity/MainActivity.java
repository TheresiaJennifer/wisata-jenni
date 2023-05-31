package com.si61.wisatajenni.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.si61.wisatajenni.API.APIRequestData;
import com.si61.wisatajenni.API.RetroServer;
import com.si61.wisatajenni.Adapter.AdapterWisata;
import com.si61.wisatajenni.Model.ModelResponse;
import com.si61.wisatajenni.Model.ModelWisata;
import com.si61.wisatajenni.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //ini code pertama di java activity selain yg ada komennya
    private RecyclerView rvWisata;
    private ProgressBar pbWisata;
    private FloatingActionButton fabWisata;
    //ini code private stlh buat method dibawah
    private RecyclerView.Adapter adWisata;
    private RecyclerView.LayoutManager lmWisata;
    private List<ModelWisata> listWisata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWisata = findViewById(R.id.rv_wisata);
        pbWisata = findViewById(R.id.pb_wisata);
        fabWisata = findViewById(R.id.fab_tambah);

        //ini code stlh buat tambahan private diatas
        lmWisata = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//difalse kn agar scroll kebawah dia kebawah (tdk kebalik)
        rvWisata.setLayoutManager(lmWisata);

        fabWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }


    //ini dibuat setelah buat code adapter class
    public void retrieveWisata(){//method retrieve data dari API
        pbWisata.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.kenokRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listWisata = response.body().getData();

                adWisata = new AdapterWisata(MainActivity.this, listWisata);
                rvWisata.setAdapter(adWisata);
                adWisata.notifyDataSetChanged();

                pbWisata.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {//ini akan dieksekusi ketika posisi activitynya ter-resume maka akan me-retrieve data lg
        super.onResume();
        retrieveWisata();
    }
}