package id.ac.digind.gasskos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import id.ac.digind.gasskos.API.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName, editTextEmail, editTextPassword, editTextPasswordConfirmation;
    private Button buttonRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordConfirmation = (EditText) findViewById(R.id.editTextRetypePassword);
        findViewById(R.id.buttonRegistrasi).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    public void register() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordConfirmation = editTextPasswordConfirmation.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Nama is required");
            editTextName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(passwordConfirmation.isEmpty()){
            editTextPasswordConfirmation.setError("Password confirmation is required");
            editTextPasswordConfirmation.requestFocus();
            return;
        }

        if(!password.equals(passwordConfirmation)){
            editTextPasswordConfirmation.setError("Enter a same password");
            editTextPasswordConfirmation.setText("");
            editTextPasswordConfirmation.requestFocus();
            return;
        }

        Call<ResponseBody> requestRegister = RetrofitClient.getInstance().getAPI().register(name, email, password, passwordConfirmation);
        requestRegister.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 400) {
                    Toast.makeText(DaftarActivity.this, "Email sudah digunakan", Toast.LENGTH_LONG).show();
                }else if(response.code() == 201) {
                    Toast.makeText(DaftarActivity.this, "Berhasil registrasi", Toast.LENGTH_LONG).show();
                    finishAffinity();
                    startActivity(new Intent(DaftarActivity.this, LoginActivity.class));
                }else {
                    Toast.makeText(DaftarActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DaftarActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegistrasi:
                register();
                break;
            case R.id.textViewLogin:
                finishAffinity();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
