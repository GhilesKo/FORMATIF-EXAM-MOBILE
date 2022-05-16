package com.example.formatif2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.formatif2.html.RetrofitUtil;
import com.example.formatif2.html.Service;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.Titre));

        Button btn = findViewById(R.id.btnReq);
        ProgressBar progressBar1 = findViewById(R.id.progressBar);
        progressBar1.setVisibility(View.GONE);
        btn.setEnabled(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                btn.setEnabled(false);
                Service service = RetrofitUtil.get();
                service.requeteExercice2().enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful())
                        {
                            progressBar1.setVisibility(View.GONE);
                            btn.setEnabled(true);
                            Toast.makeText(MainActivity.this, getString(R.string.reqSuccess), Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            progressBar1.setVisibility(View.GONE);
                            btn.setEnabled(true);
                            try {
                                Toast.makeText(MainActivity.this, response.errorBody().string() , Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressBar1.setVisibility(View.GONE);
                        btn.setEnabled(true);
                        Toast.makeText(MainActivity.this, getString(R.string.noInternet) , Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });



    }
}