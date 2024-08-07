package com.example.capacitortest.configuration;

import android.app.Application;
import android.content.Context;

public class AppConfig extends Application {

  private static AppConfig INSTANCE;

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;
  }

  public static AppConfig getInstance() {
    return INSTANCE;
  }

  public static Context getContext() {
    return INSTANCE.getApplicationContext();
  }


}
