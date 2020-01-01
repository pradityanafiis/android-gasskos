package id.ac.digind.gasskos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import id.ac.digind.gasskos.models.User;
import id.ac.digind.gasskos.storage.SharedPreferencesManager;

public class PengaturanAkunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferencesManager spm = SharedPreferencesManager.getInstance(this);
        User user = spm.getUser();

        TextInputEditText etNama = findViewById(R.id.et_nama);
        TextInputEditText etEmail = findViewById(R.id.et_email);
        TextInputEditText etPassword = findViewById(R.id.et_password);
        Button btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> {
            //TODO : call API to update
            if (!user.getName().equals(etNama.getEditableText().toString()) && !user.getEmail().equals(etEmail.getEditableText().toString())) {
                spm.updateUser(etNama.getEditableText().toString(), etEmail.getEditableText().toString());
                Snackbar.make(findViewById(R.id.content), "Profil berhasil diupdate", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etNama.setText(user.getName());
        etEmail.setText(user.getEmail());
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
