package com.example.capacitortest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
  tableName = "categories",
  foreignKeys = @ForeignKey(
    entity = Category.class,
    parentColumns = "id",
    childColumns = "parent_id",
    onDelete = ForeignKey.CASCADE
  )
)
public class Category {

  public Category() {
  }

  public Category(Long id, String name, Long parentId) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
  }

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String name;
  @ColumnInfo(name = "parent_id", index = true)
  private Long parentId = null;

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }
}
