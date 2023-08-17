package com.example.jmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.jmedia.Models.NewsApiResponse;
import com.example.jmedia.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_main);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Fetching news Articles");
        dialog.show();



        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);




    }


    private final OnFetchDataListener<NewsApiResponse>listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {

            showNews(list);

        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {

        dialog.dismiss();
        recyclerView= findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}