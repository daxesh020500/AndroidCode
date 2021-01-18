package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furnico.retrofit.model.Customer;
import com.example.furnico.retrofit.model.ResponseString;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    EditText name,pass,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name  = (EditText) findViewById(R.id.et_activity_register_fullname);
        pass = (EditText) findViewById(R.id.et_activity_registration_password);
        email = (EditText) findViewById(R.id.et_activity_login_email);
        address = (EditText) findViewById(R.id.et_activity_registration_address);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Button btnRegister = findViewById(R.id.bt_activity_registeration_register);
        btnRegister.setOnClickListener(v -> {
            if (email.getText().toString().length() == 0){// || pass.length() == 0 || name.length() == 0 || address.length() == 0 || phone.length() == 0) {
                Toast.makeText(RegistrationActivity.this, "Length should be greater than 0", Toast.LENGTH_SHORT).show();
            } else {
                Customer customer = new Customer();
                //customer.setEmail(email);customer.setPassword(pass);customer.setPhone(phone);customer.setAddress(address);customer.setName(name);
                Retrofit retrofit = RetrofitBuilderUserService.getInstance();
                IPostApi iPostApi = retrofit.create(IPostApi.class);
                Call<ResponseString> responses = iPostApi.registerPost(customer);
                Callback<ResponseString> userCallback = new Callback<ResponseString>() {
                    @Override
                    public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                        Log.d("Register", "Reponse" + response.body());
                        if (response.body().getValue().equals("failure"))
                            Toast.makeText(RegistrationActivity.this, "Account Already Exists", Toast.LENGTH_SHORT).show();
                        else {
                            int isCart = getIntent().getIntExtra("cartLogin", 0);
                            if (isCart == 1) {
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                intent.putExtra("cartLogin", 1);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseString> call, Throwable t) { t.printStackTrace(); }
                };
                responses.enqueue(userCallback);
            }
        });
    }
}