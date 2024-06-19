package com.example.capacitortest;

import android.os.Build;
import android.os.Bundle;

import androidx.room.Room;

import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.dao.CategoryDao;
import com.example.capacitortest.database.dao.NoteDao;
import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.plugins.CreateCategoryPlugin;
import com.getcapacitor.BridgeActivity;
import java.util.logging.Logger;

public class MainActivity extends BridgeActivity {
//  https://developer.android.com/training/data-storage/room?hl=fr

  private final Logger log = Logger.getLogger(this.getClass().getName());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    registerPlugin(CreateCategoryPlugin.class);
    super.onCreate(savedInstanceState);

    log.info("test 1 mais en logger");
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
    var secondList = cate.findAllCategories();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      list.forEach(predicate -> {
        log.info("predicate : " + predicate.getId() + " " + predicate.getName());
      });
      secondList.forEach(predicate -> {
        log.info("all categories , id :" + predicate.getId() + ", name : " + predicate.getName() + ", parent category " +
                 (predicate.getParentId() != null ? predicate.getParentId() : "no parent category"));
      });

    }

  }

}
