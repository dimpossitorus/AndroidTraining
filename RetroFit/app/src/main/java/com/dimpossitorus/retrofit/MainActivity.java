package com.dimpossitorus.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dimpossitorus.retrofit.api.GitApi;
import com.dimpossitorus.retrofit.model.GitModel;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private String url = "https://api.github.com";

    @ViewById(R.id.username)
    EditText username;

    @ViewById(R.id.load)
    Button load;

    @ViewById(R.id.detail)
    TextView detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Click(R.id.load)
    void showDetail() {
        Toast.makeText(MainActivity.this, "Button load is clicked", Toast.LENGTH_LONG).show();
        String user = username.getText().toString();

        //Retrofit parts
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpUrl.parse("https://api.github.com")).addConverterFactory(GsonConverterFactory.create()).build();

        GitApi service = retrofit.create(GitApi.class);

        Call<GitModel> call =service.getFeed(username.getText().toString());

        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                if (!response.isSuccessful()) {
                    System.out.println(call.request().url() + ": failed: " + response.code());
                    return;
                }
                GitModel gitModel = response.body();
                Log.d("CallBack", " response is " + response.body());
                detail.setText("Github name : "+gitModel.getName()+"\nWebsite : "+gitModel.getBlog()+"\nCompany Name : "+gitModel.getCompany());
                detail.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Succed to connect to GitHub Api", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to connect to GitHub Api", Toast.LENGTH_LONG).show();
                Log.d("CallBack", " Throwable is " +t);
            }
        });
    }
}
