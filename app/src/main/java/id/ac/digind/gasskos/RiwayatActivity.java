package id.ac.digind.gasskos;

import android.content.Intent;
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

import id.ac.digind.gasskos.adapters.RiwayatAdapter;
import id.ac.digind.gasskos.models.Riwayat;

public class RiwayatActivity extends AppCompatActivity implements RiwayatAdapter.OnItemRiwayatListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rv_notifikasi);
        List<Riwayat> dataList = Riwayat.dummyData(10);
        RiwayatAdapter adapter = new RiwayatAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openDetailRiwayat(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id_riwayat", id);
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
