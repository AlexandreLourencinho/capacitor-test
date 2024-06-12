package com.example.capacitortest.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.database.entities.Note;

import java.util.List;

@Dao
public interface NoteDao {

  // continuer avec https://developer.android.com/training/data-storage/room/relationships?hl=fr#one-to-many
  // https://github.com/android/codelab-android-room-with-a-view/blob/master/app/src/main/java/com/example/android/roomwordssample/WordRoomDatabase.java
  // https://github.com/ionic-team/capacitor-plugins/tree/main

//  List<Note> findNotesByCategory(Category category);


  @Insert
  Long insertNote(Note note);

  @Update
  void updateNote(Note note);


}
