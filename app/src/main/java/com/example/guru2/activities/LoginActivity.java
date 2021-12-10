package com.example.guru2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guru2.R;
import com.example.guru2.model.LoginResponse;
import com.example.guru2.networking.ServiceClient;
import com.example.guru2.networking.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etKodeGuru,etPass;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etKodeGuru = findViewById(R.id.et_kode_guru);
        etPass = findViewById(R.id.et_pass_guru);

        pd = new ProgressDialog(this);
    }

    public void login(View view) {
        pd.setMessage("loading..");
        pd.setCancelable(false);
        pd.show();

        if (etKodeGuru.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "Kode Guru Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etPass.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        String kodeGuru = etKodeGuru.getText().toString().trim().toUpperCase();
        String passGuru = etPass.getText().toString().trim();

        ServiceClient service  = ServiceGenerator.createService(ServiceClient.class);


        Call<LoginResponse> requestLogin = service.loginGuru("loginGuru","login",kodeGuru,passGuru);

        requestLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if (response.body().getHasil().equals("succes")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Koneksi eror", Toast.LENGTH_SHORT).show();
            }
        });
    }
}