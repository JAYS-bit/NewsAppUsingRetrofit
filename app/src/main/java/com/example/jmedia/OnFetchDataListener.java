package com.example.jmedia;

import com.example.jmedia.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {


    void onFetchData(List<NewsHeadlines>list, String message);
    void onError(String message);
}
