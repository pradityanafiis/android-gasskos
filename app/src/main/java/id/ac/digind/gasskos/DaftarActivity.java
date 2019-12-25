package id.ac.digind.gasskos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DaftarActivity extends AppCompatActivity {

    Button verif;
    Button verif2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.toolbar_satu);
        setSupportActionBar(ToolBarAtas2);
//        ToolBarAtas2.setLogo(R.mipmap.ic_launcher);
        ToolBarAtas2.setLogoDescription(getResources().getString(R.string.app_name));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        verif = (Button)findViewById(R.id.PencariKos);
        verif.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(DaftarActivity.this, VerifikasiActivity.class));

        }
        });
        verif2 = (Button)findViewById(R.id.PemilikKos);
        verif2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarActivity.this, VerifikasiActivity.class));

            }
        });
    }
}
