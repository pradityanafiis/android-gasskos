package id.ac.digind.gasskos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import id.ac.digind.gasskos.models.Kamar;

public class BookingActivity extends AppCompatActivity {

    public static String extra_kamar = "Extra Kamar";
    private TextView textViewTipeKamar, textViewHarga;
    private EditText datePicker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        Kamar kamar = getIntent().getParcelableExtra(extra_kamar);

        textViewTipeKamar = findViewById(R.id.textViewTipeKamar);
        textViewHarga = findViewById(R.id.textViewHarga);
        datePicker = findViewById(R.id.datePicker);

        textViewTipeKamar.setText(kamar.getTipe());
        textViewHarga.setText(formatRupiah.format((double)kamar.getHarga()));

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datePicker.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                }, year, month, day);
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
