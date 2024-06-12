package com.example.capacitortest;

import android.os.Build;
import android.os.Bundle;

import androidx.room.Room;

import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.dao.CategoryDao;
import com.example.capacitortest.database.dao.NoteDao;
import com.example.capacitortest.database.entities.Category;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
//  https://developer.android.com/training/data-storage/room?hl=fr

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    System.out.println("test 1");
    DatabaseConfiguration dbConfig = Room.databaseBuilder(getApplicationContext(),
        DatabaseConfiguration.class, "test-db").allowMainThreadQueries().build();

    NoteDao noteDao = dbConfig.noteDao();
    CategoryDao cate = dbConfig.categoryDao();
    Category catToInsert = new Category();
    Category catToInsert2 = new Category();
    catToInsert.setName("name cate");
    catToInsert2.setName("name cate 2");
    catToInsert2.setParentId(2L);
    cate.insertCategory(catToInsert);
    cate.insertCategory(catToInsert2);
    var list = cate.findAllRootCategories();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      list.forEach(predicate -> {
        System.out.println("predicate : " + predicate.getId() + " " + predicate.getName());
        cate.deleteCategory(predicate);
      });

    }

  }

}
