package id.ac.digind.gasskos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.adapters.RiwayatAdapter;
import id.ac.digind.gasskos.models.Riwayat;
import id.ac.digind.gasskos.models.Transaksi;
import id.ac.digind.gasskos.models.TransaksiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatActivity extends AppCompatActivity implements RiwayatAdapter.OnItemRiwayatListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<TransaksiResponse> getTransaksi = RetrofitClient.getInstance().getAPI().getTransaksi(sharedPreferences.getString("token", ""), sharedPreferences.getInt("id_user", 0));
        getTransaksi.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                RecyclerView recyclerView = findViewById(R.id.rv_RiwayatTransaksi);
                List<Transaksi> transaksiList = response.body().getTransaksi();
                RiwayatAdapter adapter = new RiwayatAdapter(transaksiList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void openDetailRiwayat(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id_transaksi", id);
        startActivity(new Intent(this, DetailTransaksiActivity.class).putExtras(bundle));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
