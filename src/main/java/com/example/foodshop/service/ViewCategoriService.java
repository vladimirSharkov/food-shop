package com.example.foodshop.service;

import com.example.foodshop.model.view.ViewInterceptor;

public interface ViewCategoriService {
    void onRequest(String url);

    ViewInterceptor getStats();

    ViewInterceptor resetStats();
}
