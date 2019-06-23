package com.example.instant;

import com.example.api.topsell.TopSellService;
import com.example.conts.Contstan;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TopSellService.init(Contstan.BASE_URL);
    }
}
