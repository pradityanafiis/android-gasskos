package id.ac.digind.gasskos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import id.ac.digind.gasskos.models.DetailTransaksiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    private TextView tv_NamaPenginapan, tv_DetailKamar, tv_TotalHarga, tv_TanggalMasuk, tv_TanggalKeluar;
    private EditText et_Komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        tv_NamaPenginapan = findViewById(R.id.tv_NamaPenginapan);
        tv_DetailKamar = findViewById(R.id.tv_DetailKamar);
        tv_TotalHarga = findViewById(R.id.tv_TotalHarga);
        tv_TanggalMasuk = findViewById(R.id.tv_TanggalMasuk);
        tv_TanggalKeluar = findViewById(R.id.tv_TanggalKeluar);
        et_Komentar = findViewById(R.id.et_ulasan);

        SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<DetailTransaksiResponse> getDetailTransaksi = RetrofitClient.getInstance().getAPI().getDetailTransaksi(sharedPreferences.getString("token", ""), bundle.getInt("id_transaksi"));
        getDetailTransaksi.enqueue(new Callback<DetailTransaksiResponse>() {
            @Override
            public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

                tv_NamaPenginapan.setText(response.body().getTransaksi().getPenginapan().getNama());
                tv_DetailKamar.setText(response.body().getTransaksi().getKamar().getTipe());
                tv_TotalHarga.setText(formatRupiah.format((double)response.body().getTransaksi().getTotalHarga()));
                tv_TanggalMasuk.setText(response.body().getTransaksi().getTanggalMasuk());
                tv_TanggalKeluar.setText(response.body().getTransaksi().getTanggalKeluar());
                et_Komentar.setText(response.body().getTransaksi().getKomentar());
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
