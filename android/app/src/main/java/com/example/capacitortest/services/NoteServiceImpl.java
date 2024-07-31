package com.example.capacitortest.services;

import android.content.Context;

import androidx.room.Room;

import com.example.capacitortest.database.configuration.DatabaseClient;
import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.entities.Note;

public class NoteServiceImpl implements NoteService {

  private final DatabaseConfiguration dbConfig;

  public NoteServiceImpl(Context context) {
    this.dbConfig = DatabaseClient.getInstance(context).getDatabase();
  }

  @Override
  public void insertNote(Note note) {
    this.dbConfig.noteDao().insertNote(note);
  }


}
