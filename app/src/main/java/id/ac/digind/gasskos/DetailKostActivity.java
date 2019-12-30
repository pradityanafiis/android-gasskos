package id.ac.digind.gasskos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
}
