package com.example.capacitortest.database.configuration;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.capacitortest.database.dao.CategoryDao;
import com.example.capacitortest.database.dao.NoteDao;
import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.database.entities.Note;

@Database(entities = {Note.class, Category.class}, version = 1)
public abstract class DatabaseConfiguration extends RoomDatabase {
  public abstract CategoryDao categoryDao();
  public abstract NoteDao noteDao();
}
