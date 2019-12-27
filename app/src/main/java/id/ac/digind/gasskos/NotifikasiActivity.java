package id.ac.digind.gasskos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import id.ac.digind.gasskos.adapters.NotifikasiAdapter;
import id.ac.digind.gasskos.adapters.RekomendasiAdapter;
import id.ac.digind.gasskos.models.Kost;
import id.ac.digind.gasskos.models.Notifikasi;

public class NotifikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rv_notifikasi);
        List<Notifikasi> dataList = Notifikasi.dummyData(10);
        NotifikasiAdapter adapter = new NotifikasiAdapter(dataList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

}
