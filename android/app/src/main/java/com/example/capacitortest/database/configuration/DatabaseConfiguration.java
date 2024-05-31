package com.example.capacitortest.database.configuration;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.database.entities.Note;

@Database(entities = {Note.class, Category.class}, version = 1)
public abstract class DatabaseConfiguration extends RoomDatabase {
}
