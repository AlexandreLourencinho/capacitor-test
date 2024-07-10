package com.example.capacitortest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "notes")
public class Note {

  public Note() {
  }

  public Note(Long id, String noteContent, Long categoryId) {
    this.id = id;
    this.noteContent = noteContent;
    this.categoryId = categoryId;
  }

  @PrimaryKey(autoGenerate = true)
  private Long id;
  @ColumnInfo(name = "note_content")
  private String noteContent;
  @ColumnInfo(name = "category_id")
  private Long categoryId = null;
  //TODO revoir relations + requête notes + catégorie !

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNoteContent() {
    return noteContent;
  }

  public void setNoteContent(String noteContent) {
    this.noteContent = noteContent;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

}
