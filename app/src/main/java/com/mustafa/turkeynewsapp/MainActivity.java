package com.mustafa.turkeynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mustafa.turkeynewsapp.api.APiInterface;
import com.mustafa.turkeynewsapp.api.ApiClient;
import com.mustafa.turkeynewsapp.newsApiJSON.Article;
import com.mustafa.turkeynewsapp.newsApiJSON.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Adapter.NewsUrlGo {
     RecyclerView recyclerView;

     List<Article> articles = new ArrayList<>();
     Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setNestedScrollingEnabled(true);
        APiInterface aPiInterface = ApiClient.getInstance().create(APiInterface.class);
        Call<Root> call  = aPiInterface.getData();
         call.enqueue(new Callback<Root>() {
             @Override
             public void onResponse(Call<Root> call, Response<Root> response) {
                    if (response.isSuccessful() && response.body() != null){
                          articles = response.body().getArticles();
                          adapter = new Adapter(articles,MainActivity.this);
                          recyclerView.setLayoutManager(layoutManager);
                          recyclerView.setAdapter(adapter);
                          adapter.setNewsUrlGo(MainActivity.this::newsClick);
                          adapter.notifyDataSetChanged();
                    }
             }

             @Override
             public void onFailure(Call<Root> call, Throwable t) {

             }
         });



    }

    @Override
    public void newsClick(String url) { //  The click of this recyclerview  is going to news site
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}