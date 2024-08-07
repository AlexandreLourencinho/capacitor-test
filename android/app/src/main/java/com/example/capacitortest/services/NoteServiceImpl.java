package com.example.capacitortest.services;

import android.content.Context;

import com.example.capacitortest.database.configuration.DatabaseClient;
import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.entities.Note;

import java.util.List;

public class NoteServiceImpl implements NoteService {

  private final DatabaseConfiguration dbConfig;

  public NoteServiceImpl(Context context) {
    this.dbConfig = DatabaseClient.getInstance(context).getDatabase();
  }

  @Override
  public void insertNote(Note note) {
    this.dbConfig.noteDao().insertNote(note);
  }

  @Override
  public List<Note> getNote() {
    return this.dbConfig.noteDao().findAllNotes();
  }


}
