package com.example.capacitortest.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.capacitortest.database.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {

  //select queries
  @Query("SELECT * FROM categories WHERE parent_id = :parentId")
  List<Category> findCategoriesByParentId(Long parentId);

  @Query("SELECT * FROM categories WHERE name = :name")
  Category findCategoryByName(String name);

  @Query("SELECT * FROM categories WHERE name LIKE '%' || :name || '%'")
  List<Category> findCategoriesByNameLike(String name);

  @Query("SELECT * FROM categories WHERE id = :id")
  Category findCategoryById(Long id);

  @Query("SELECT * FROM categories WHERE parent_id IS NULL")
  List<Category> findAllRootCategories();


  // insert queries
  @Insert
  Long insertCategory(Category category);

  @Insert
  List<Long> insertAllCategories(Category... category);


  // update query
  @Update
  void updateCategory(Category category);


  // delete queries
  @Delete
  void deleteCategory(Category category);

  @Delete
  void deleteMultiplesCategories(Category... category);

}
