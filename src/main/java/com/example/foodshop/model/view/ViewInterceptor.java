package com.example.foodshop.model.view;

public class ViewInterceptor {


    private int countBioView;
    private int countDrinkView;
    private int countEggView;
    private int countMeatView;
    private int countBreadView;

    public ViewInterceptor(int countBioView, int countDrinkView, int countEggView, int countMeatView, int countBreadView) {
        this.countBioView = countBioView;
        this.countDrinkView = countDrinkView;
        this.countEggView = countEggView;
        this.countMeatView = countMeatView;
        this.countBreadView = countBreadView;
    }

    public int getCountBioView() {
        return countBioView;
    }

    public int getCountDrinkView() {
        return countDrinkView;
    }

    public int getCountEggView() {
        return countEggView;
    }


    public ViewInterceptor setCountBioView(int countBioView) {
        this.countBioView = countBioView;
        return this;
    }

    public ViewInterceptor setCountDrinkView(int countDrinkView) {
        this.countDrinkView = countDrinkView;
        return this;
    }

    public ViewInterceptor setCountEggView(int countEggView) {
        this.countEggView = countEggView;
        return this;
    }

    public int getCountMeatView() {
        return countMeatView;
    }

    public ViewInterceptor setCountMeatView(int countMeatView) {
        this.countMeatView = countMeatView;
        return this;
    }

    public int getCountBreadView() {
        return countBreadView;
    }

    public ViewInterceptor setCountBreadView(int countBreadView) {
        this.countBreadView = countBreadView;
        return this;
    }
}
