package id.ac.digind.gasskos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.models.Kamar;
import id.ac.digind.gasskos.models.StandartResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

    public static String extra_kamar = "Extra Kamar";
    private TextView textViewTipeKamar, textViewHarga;
    private EditText tanggalMasuk, durasi;
    private Button datePick, buttonPesanKamar;
    private DatePickerDialog dialog;
    private Kamar kamar;
    private String flagTglYmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        kamar = getIntent().getParcelableExtra(extra_kamar);

        textViewTipeKamar = findViewById(R.id.textViewTipeKamar);
        textViewHarga = findViewById(R.id.textViewHarga);
        tanggalMasuk = findViewById(R.id.tanggalMasuk);
        datePick = findViewById(R.id.datePick);
        durasi = findViewById(R.id.editTextDurasi);
        buttonPesanKamar = findViewById(R.id.buttonPesanKamar);

        textViewTipeKamar.setText(kamar.getTipe());
        textViewHarga.setText(formatRupiah.format((double)kamar.getHarga()));

        tanggalMasuk.setOnClickListener(this);
        buttonPesanKamar.setOnClickListener(this);
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

    public void pesanKamar(){
        SharedPreferences spm = this.getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<StandartResponse> pesanKamar = RetrofitClient.getInstance().getAPI()
                .storeTransaksi(spm.getString("token", ""), spm.getInt("id_user", 0), kamar.getIdKamar(), flagTglYmd, Integer.parseInt(durasi.getText().toString()));
        pesanKamar.enqueue(new Callback<StandartResponse>() {
            @Override
            public void onResponse(Call<StandartResponse> call, Response<StandartResponse> response) {
                Toast.makeText(BookingActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BookingActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<StandartResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tanggalMasuk:
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalMasuk.setText(dayOfMonth + "-" + month+1 + "-" + year);
                        flagTglYmd = year + "-" + month+1 + "-" + dayOfMonth;
                    }
                }, year, month, day);
                dialog.show();
                break;

            case R.id.buttonPesanKamar:
                pesanKamar();
                break;
        }
    }
}
