package id.ac.digind.gasskos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.models.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;
    private TextView daftar;
    private Button buttonDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword) ;
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewDaftar).setOnClickListener(this);
    }

    private void login(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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

        Call<LoginResponse> call = RetrofitClient.getInstance().getAPI().login(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.body().getError()){
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                login();
                break;
            case R.id.textViewDaftar:
                startActivity(new Intent(this, DaftarActivity.class));
                break;
        }
    }
}
