package id.ac.digind.gasskos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import id.ac.digind.gasskos.adapters.SliderAdapter;

public class DetailKostActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bDetail, bFoto;
    private TextView tvDetailKos;
    private RecyclerView rvDetailFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bDetail = findViewById(R.id.b_detail);
        bFoto = findViewById(R.id.b_foto);
        tvDetailKos = findViewById(R.id.tv_detailkos);
        rvDetailFoto = findViewById(R.id.rv_detailfoto);

        bDetail.setOnClickListener(this);
        bFoto.setOnClickListener(this);

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapter adapter = new SliderAdapter(this);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b_detail) {
            bDetail.setBackgroundResource(R.drawable.tepi5);
            bFoto.setBackgroundResource(R.drawable.tepi6);
            tvDetailKos.setVisibility(View.VISIBLE);
            rvDetailFoto.setVisibility(View.GONE);
        }else if (v.getId() == R.id.b_foto) {
            bDetail.setBackgroundResource(R.drawable.tepi6);
            bFoto.setBackgroundResource(R.drawable.tepi5);
            tvDetailKos.setVisibility(View.GONE);
            rvDetailFoto.setVisibility(View.VISIBLE);
        }
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
