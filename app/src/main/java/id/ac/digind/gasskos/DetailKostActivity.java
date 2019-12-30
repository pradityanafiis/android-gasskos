package id.ac.digind.gasskos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKostActivity extends AppCompatActivity {
    private TextView textViewGender, textViewNamaPenginapan, textViewAlamat, textViewFasilitas;
    private ViewPager viewPagerFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewGender = findViewById(R.id.textViewGender);
        textViewNamaPenginapan = findViewById(R.id.textViewNamaPenginapan);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewFasilitas = findViewById(R.id.textViewFasilitas);
        viewPagerFoto = findViewById(R.id.viewPagerFoto);

        Bundle bundle = getIntent().getExtras();
        SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<DetailPenginapanResponse> getDetailPenginapan = RetrofitClient.getInstance().getAPI().getDetailPenginapan(sharedPreferences.getString("token", ""), bundle.getInt("id_penginapan"));
        getDetailPenginapan.enqueue(new Callback<DetailPenginapanResponse>() {
            @Override
            public void onResponse(Call<DetailPenginapanResponse> call, Response<DetailPenginapanResponse> response) {
                textViewGender.setText(response.body().getPenginapan().getGender());
                textViewNamaPenginapan.setText(response.body().getPenginapan().getNama());
                textViewAlamat.setText(response.body().getPenginapan().getAlamat());
                textViewFasilitas.setText(response.body().fasilitasToString(response.body().getFasilitasList()));
            }

            @Override
            public void onFailure(Call<DetailPenginapanResponse> call, Throwable t) {
                Toast.makeText(DetailKostActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
