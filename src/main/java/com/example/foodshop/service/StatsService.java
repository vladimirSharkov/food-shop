package com.example.foodshop.service;

import com.example.foodshop.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
