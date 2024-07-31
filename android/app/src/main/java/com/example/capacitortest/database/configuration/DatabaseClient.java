package com.example.capacitortest.database.configuration;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

  private static DatabaseClient instance;
  private final DatabaseConfiguration database;

  private DatabaseClient(Context context) {
      // Créer la base de données Room
    database = Room.databaseBuilder(context, DatabaseConfiguration.class, "test-db")
      .allowMainThreadQueries()  // À éviter en production
      .fallbackToDestructiveMigration()
      .build();
  }

  public static synchronized DatabaseClient getInstance(Context context) {
    if (instance == null) {
      instance = new DatabaseClient(context);
    }
    return instance;
  }

  public DatabaseConfiguration getDatabase() {
    return database;
  }

}
