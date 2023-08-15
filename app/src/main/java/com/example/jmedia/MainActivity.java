package com.example.jmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jmedia.Models.NewsApiResponse;
import com.example.jmedia.Models.NewsHeadlines;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  SelectListener , View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    String category;

    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;


    List<NewsHeadlines>data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_main);
        b1=findViewById(R.id.btn_1);
        b2=findViewById(R.id.btn_2);
        b3=findViewById(R.id.btn_3);
        b4=findViewById(R.id.btn_4);
        b5=findViewById(R.id.btn_5);
        b6=findViewById(R.id.btn_6);
        b7=findViewById(R.id.btn_7);
        searchView=findViewById(R.id.search_view);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7 .setOnClickListener(this);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news artcles of " + query);
                dialog.show();
                RetrofitInstance.getInstance().callNewsApi.callHeadlines("us","general",query,getApplication().getString(R.string.API_KEY)).enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                        dialog.dismiss();
                        data= response.body().getArticles();
                        showNews(data);
                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    }
                });


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog= new ProgressDialog(this);
        dialog.setTitle("Fetching news Articles");
        dialog.show();

        RetrofitInstance.getInstance().callNewsApi.callHeadlines("us","general",null,getApplication().getString(R.string.API_KEY)).enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {

                data= response.body().getArticles();
                showNews(data);
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });






    }




    private void showNews(List<NewsHeadlines> list) {

        dialog.dismiss();
        recyclerView= findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {

            startActivity(new Intent(MainActivity.this, DetailsActivity.class).putExtra("data",headlines));


    }


    @Override
    public void onClick(View v) {
        Button button =(Button) v;
         category=  button.getText().toString();
        dialog.setTitle("Fetching news article of "+ category);
        dialog.show();
        RetrofitInstance.getInstance().callNewsApi.callHeadlines("us",category,null,getApplication().getString(R.string.API_KEY)).enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                dialog.dismiss();
                data= response.body().getArticles();
                showNews(data);
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });

    }
}
