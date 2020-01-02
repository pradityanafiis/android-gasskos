package id.ac.digind.gasskos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Locale;
import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import id.ac.digind.gasskos.models.DetailTransaksiResponse;
import id.ac.digind.gasskos.models.StandartResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class DetailTransaksiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_NamaPenginapan, tv_DetailKamar, tv_TotalHarga, tv_TanggalMasuk, tv_TanggalKeluar;
    private EditText et_Komentar;
    private ImageView imgRating1, imgRating2, imgRating3, imgRating4, imgRating5;
    private Button btnKirim;
    private int flagStar = 5;
    private int idTransaksi;

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
        imgRating1 = findViewById(R.id.img_rating1);
        imgRating2 = findViewById(R.id.img_rating2);
        imgRating3 = findViewById(R.id.img_rating3);
        imgRating4 = findViewById(R.id.img_rating4);
        imgRating5 = findViewById(R.id.img_rating5);
        btnKirim = findViewById(R.id.btn_kirim);

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
                setStar(response.body().getTransaksi().getRating());
                setIdTransaksi(response.body().getTransaksi().getIdTransaksi());
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        imgRating1.setOnClickListener(this);
        imgRating2.setOnClickListener(this);
        imgRating3.setOnClickListener(this);
        imgRating4.setOnClickListener(this);
        imgRating5.setOnClickListener(this);
        btnKirim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_rating1:
                setStar(1);
                break;
            case R.id.img_rating2:
                setStar(2);
                break;
            case R.id.img_rating3:
                setStar(3);
                break;
            case R.id.img_rating4:
                setStar(4);
                break;
            case R.id.img_rating5:
                setStar(5);
                break;
            case R.id.btn_kirim:
                String review = et_Komentar.getText().toString();
                if (review.equals("")) {
                    et_Komentar.setError("Harus diisi");
                } else {
                    et_Komentar.setError(null);
                    SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
                    Call<StandartResponse> ulasan = RetrofitClient.getInstance().getAPI().ulasan(sharedPreferences.getString("token", ""), idTransaksi, flagStar, review);
                    ulasan.enqueue(new Callback<StandartResponse>() {
                        @Override
                        public void onResponse(Call<StandartResponse> call, Response<StandartResponse> response) {
                            Toast.makeText(DetailTransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<StandartResponse> call, Throwable t) {
                            Toast.makeText(DetailTransaksiActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
        }
    }

    private void setStar(int count){
        if (count >= 1) {
            imgRating1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgRating1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 2) {
            imgRating2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgRating2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 3) {
            imgRating3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgRating3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 4) {
            imgRating4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgRating4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        if (count >= 5) {
            imgRating5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_24px));
        } else {
            imgRating5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_24px));
        }

        flagStar = count;
    }

    private void setIdTransaksi(int id) {
        idTransaksi = id;
    }
}
