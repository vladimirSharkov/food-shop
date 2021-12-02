package com.example.foodshop.scheduler;

import com.example.foodshop.model.view.ViewInterceptor;
import com.example.foodshop.service.ViewCategoriService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCategoryStatsScheduler {
    private final ViewCategoriService viewCategoriService;

    public RemoveCategoryStatsScheduler(ViewCategoriService viewCategoriService) {
        this.viewCategoriService = viewCategoriService;
    }


//    @Scheduled(cron ="25 * * * * *")
    @Scheduled(cron ="0 0 1 * * *")
    public void removeCategory(){
       viewCategoriService.resetStats();
    }
}
