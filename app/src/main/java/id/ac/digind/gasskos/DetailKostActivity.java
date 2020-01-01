package id.ac.digind.gasskos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.adapters.KamarAdapter;
import id.ac.digind.gasskos.models.DetailPenginapanResponse;
import id.ac.digind.gasskos.models.Kamar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import id.ac.digind.gasskos.adapters.SliderAdapter;

public class DetailKostActivity extends AppCompatActivity implements View.OnClickListener, KamarAdapter.OnItemRvClicked {
    private TextView textViewGender, textViewNamaPenginapan, textViewAlamat, textViewFasilitas;
    private Button buttonWhatsapp;
    private String numberWhatsapp;
    private SliderView sliderView;
    private RecyclerView recyclerView;
    private SliderAdapter fotoAdapter;
    private KamarAdapter kamarAdapter;
    private List<Kamar> kamarList;
    private CardView cardViewKamar;
    private Kamar kamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewGender = findViewById(R.id.textViewGender);
        textViewNamaPenginapan = findViewById(R.id.textViewTipeKamar);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewFasilitas = findViewById(R.id.textViewFasilitas);
        buttonWhatsapp = findViewById(R.id.buttonWhatsapp);
        sliderView = findViewById(R.id.imageSlider);
        recyclerView = findViewById(R.id.rv_kamar);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailKostActivity.this, 1));
        buttonWhatsapp.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        SharedPreferences sharedPreferences = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<DetailPenginapanResponse> getDetailPenginapan = RetrofitClient.getInstance().getAPI().getDetailPenginapan(sharedPreferences.getString("token", ""), bundle.getInt("id_penginapan"));
        getDetailPenginapan.enqueue(new Callback<DetailPenginapanResponse>() {
            @Override
            public void onResponse(Call<DetailPenginapanResponse> call, Response<DetailPenginapanResponse> response) {
                dialog.dismiss();
                textViewGender.setText(response.body().getPenginapan().getGender());
                textViewNamaPenginapan.setText(response.body().getPenginapan().getNama());
                textViewAlamat.setText(response.body().getPenginapan().getAlamat());
                textViewFasilitas.setText(response.body().fasilitasToString(response.body().getFasilitasList()));
                numberWhatsapp = response.body().getPenginapan().getTelepon();

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

    @Override
    public void goToBooking(Kamar kamar) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra(BookingActivity.extra_kamar, kamar);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String url = "https://api.whatsapp.com/send?phone="+numberWhatsapp;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}

