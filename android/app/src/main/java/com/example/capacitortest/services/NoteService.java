package com.example.capacitortest.services;

import com.example.capacitortest.database.entities.Note;

import java.util.List;

public interface NoteService {


  void insertNote(Note note);

  List<Note> getNote();
}
