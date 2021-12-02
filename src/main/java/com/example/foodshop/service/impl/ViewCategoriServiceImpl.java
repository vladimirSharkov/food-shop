package com.example.foodshop.service.impl;

import com.example.foodshop.model.view.ViewInterceptor;
import com.example.foodshop.service.ViewCategoriService;
import org.springframework.stereotype.Service;

@Service
public class ViewCategoriServiceImpl implements ViewCategoriService {
    private int countBioView, countDrinkView, countEggView, countMeatView, countBreadView;


    @Override
    public void onRequest(String url) {
        if (url.contains("/product/bio")) {
            countBioView++;
        } else if (url.contains("/product/drinks")) {
            countDrinkView++;
        } else if (url.contains("/product/dairy")) {
            countEggView++;
        }else if (url.contains("/product/meat")){
            countMeatView++;
        }else if (url.contains("/product/bread")){
            countBreadView++;
        }
    }

    @Override
    public ViewInterceptor getStats() {
        return new ViewInterceptor(countBioView, countDrinkView, countEggView, countMeatView, countBreadView);

    }

    @Override
    public ViewInterceptor resetStats() {

        this.countEggView = 0;
        this.countDrinkView = 0;
        this.countBioView = 0;
        this.countBreadView = 0;
        this.countMeatView = 0;
        ViewInterceptor stats = getStats();
        return stats
                .setCountEggView(countEggView)
                .setCountBioView(countBioView)
                .setCountDrinkView(countDrinkView)
                .setCountBreadView(countBreadView)
                .setCountMeatView(countMeatView);
    }
}
