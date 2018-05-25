package com.drganh.exammobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Cuong on 3/17/2018.
 */

public class GetKmActivity extends AppCompatActivity {
    private EditText lat1;
    private EditText lng1;
    private EditText lat2;
    private EditText lng2;
    private Button ok;
    private TextView result;
    private InputStream is = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getkmactivity);

        lat1 = findViewById(R.id.lat1);
        lng1 = findViewById(R.id.lng1);
        lat2 = findViewById(R.id.lat2);
        lng2 = findViewById(R.id.lng2);
        ok = findViewById(R.id.btn_ok);
        result = findViewById(R.id.result);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lat1.getText().toString().equals("") && !lng1.getText().toString().equals("") && !lat2.getText().toString().equals("") && !lng2.getText().toString().equals("")) {
                    String url = "https://fir-test-43bd7.firebaseapp.com/latlng?lat1=" + lat1.getText().toString() + "&long1=" + lng1.getText().toString() + "&lat2=" + lat2.getText().toString() + "&long2=" + lng2.getText().toString();
                    GetAdress getAdress = new GetAdress();
                    getAdress.execute(url);
                }
            }
        });
    }

    class GetAdress extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            try {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(strings[0])
                        .build();

                Response response = client.newCall(request).execute();

                is = response.body().source().inputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

            } catch (IOException e) {

            }
            return builder.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result.setText(s + " Km");

        }
    }
}
