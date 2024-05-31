package com.example.capacitortest.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

  public Note() {
  }

  public Note(Long id, String note_content, Long category_id) {
    this.id = id;
    this.note_content = note_content;
    this.category_id = category_id;
  }

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String note_content;
  private Long category_id = null;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNote_content() {
    return note_content;
  }

  public void setNote_content(String note_content) {
    this.note_content = note_content;
  }

  public Long getCategory_id() {
    return category_id;
  }

  public void setCategory_id(Long category_id) {
    this.category_id = category_id;
  }

}
