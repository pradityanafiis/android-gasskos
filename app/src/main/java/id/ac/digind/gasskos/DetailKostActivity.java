package id.ac.digind.gasskos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.adapters.KamarAdapter;
import id.ac.digind.gasskos.adapters.PenginapanAdapter;
import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import id.ac.digind.gasskos.models.Kamar;
import id.ac.digind.gasskos.models.Penginapan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import id.ac.digind.gasskos.adapters.SliderAdapter;

public class DetailKostActivity extends AppCompatActivity {
    private TextView textViewGender, textViewNamaPenginapan, textViewAlamat, textViewFasilitas;
    private SliderView sliderView;
    private SliderAdapter fotoAdapter;
    private RecyclerView recyclerView;
    private KamarAdapter kamarAdapter;
    private List<Kamar> kamarList;

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
        sliderView = findViewById(R.id.imageSlider);

        recyclerView = findViewById(R.id.rv_kamar);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailKostActivity.this, 1));

        Bundle bundle = getIntent().getExtras();
        SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        Call<DetailPenginapanResponse> getDetailPenginapan = RetrofitClient.getInstance().getAPI()
                .getDetailPenginapan(sharedPreferences.getString("token", ""), bundle.getInt("id_penginapan"));
        getDetailPenginapan.enqueue(new Callback<DetailPenginapanResponse>() {
            @Override
            public void onResponse(Call<DetailPenginapanResponse> call, Response<DetailPenginapanResponse> response) {
                dialog.dismiss();
                textViewGender.setText(response.body().getPenginapan().getGender());
                textViewNamaPenginapan.setText(response.body().getPenginapan().getNama());
                textViewAlamat.setText(response.body().getPenginapan().getAlamat());
                textViewFasilitas.setText(response.body().fasilitasToString(response.body().getFasilitasList()));

                kamarList = response.body().getKamarList();
                kamarAdapter = new KamarAdapter(DetailKostActivity.this, kamarList);
                recyclerView.setAdapter(kamarAdapter);

                fotoAdapter = new SliderAdapter(DetailKostActivity.this, response.body().getFotoPenginapanList());
                sliderView.setSliderAdapter(fotoAdapter);
                sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(5); //set scroll delay in seconds :
                sliderView.startAutoCycle();
            }

            @Override
            public void onFailure(Call<DetailPenginapanResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(DetailKostActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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

