package com.test.sam.imgviewerdeluxe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String IMGUR_API_URL = "https://api.imgur.com/3/";
    private static final String DEFAULT_SUBREDDIT = "food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(IMGUR_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getCustomHttpClient())
                .build();

        ImgurService service = adapter.create(ImgurService.class);
        Call<GenericImgurData> call = service.receiveData(DEFAULT_SUBREDDIT);

        call.enqueue(new Callback<GenericImgurData>() {
            @Override
            public void onResponse(Call<GenericImgurData> call, Response<GenericImgurData> response) {
                if (response.isSuccessful()) {
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    ImgRecyclerView imgRecyclerView = new ImgRecyclerView(MainActivity.this, response.body().getData());
                    recyclerView.setAdapter(imgRecyclerView);
                } else {
                    Toast.makeText(MainActivity.this,
                            String.format("Error loading data! (Code: %d)", response.raw().code()),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<GenericImgurData> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        String.format("Data retrieval failed. '%s'", t.getMessage()),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private OkHttpClient getCustomHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder()
                        .addHeader("Authorization", "Client-ID " + getResources().getString(R.string.client_key));
                return chain.proceed(request.build());
            }
        }).build();
    }
}
