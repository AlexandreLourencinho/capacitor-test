package com.example.capacitortest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.room.Room;

import com.example.capacitortest.database.configuration.DatabaseClient;
import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.dao.CategoryDao;
import com.example.capacitortest.database.dao.NoteDao;
import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.database.entities.Note;
import com.example.capacitortest.http.MyHTTPd;
import com.getcapacitor.BridgeActivity;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MainActivity extends BridgeActivity {

  //  https://developer.android.com/training/data-storage/room?hl=fr
  private static final String CLASSNAME = MainActivity.class.getName();

  private final Logger log = Logger.getLogger(CLASSNAME);
  private MyHTTPd server;
  private DatabaseConfiguration dbConfig;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    log.info("test 1 mais en logger");
    dbConfig = DatabaseClient.getInstance(getApplicationContext()).getDatabase();

    NoteDao noteDao = dbConfig.noteDao();
    CategoryDao cate = dbConfig.categoryDao();

//    Note note = new Note();
//    Category catToInsert = new Category();
//    Category catToInsert2 = new Category();
//    catToInsert.setName("name cate");
//    catToInsert2.setName("name cate 2");
//    catToInsert2.setParentId(1L);
//    cate.insertCategory(catToInsert);
//    cate.insertCategory(catToInsert2);
//    note.setNoteContent("blablablaaaaaaaaa la note");
//    note.setCategoryId(2L);
//    noteDao.insertNote(note);
    var catd = cate.findAllNotesAndCategory();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      catd.forEach((key, value) -> {
        Log.d(CLASSNAME, key.getName());
        Log.d(CLASSNAME, key.getId().toString());
        Log.d(CLASSNAME, "for this category :");
        value.forEach(predicate -> {
          Log.d(CLASSNAME, predicate.getId().toString());
          Log.d(CLASSNAME, predicate.getNoteContent());
          Log.d(CLASSNAME, predicate.getCategoryId().toString());
        });

      });
    }

    var notes = noteDao.findAllNotes();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      notes.forEach(predicate -> {
        Log.d(CLASSNAME, predicate.getNoteContent());
        Log.d(CLASSNAME, predicate.getCategoryId().toString());
      });
    }
    var list = cate.findAllRootCategories();
    var secondList = cate.findAllCategories();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      list.forEach(
        predicate -> log.info("predicate : " + predicate.getId() + " " + predicate.getName()));
      secondList.forEach(predicate -> log.info(
        "all categories , id :" + predicate.getId() + ", name : " + predicate.getName() + ", parent category " + (predicate.getParentId() != null ? predicate.getParentId() : "no parent category")));

    }

    server = new MyHTTPd();

    try {
      server.start();
      log.warning("http server running");
      Log.d(CLASSNAME, "hostname : " + server.getHostname());
      Log.d(CLASSNAME, Objects.requireNonNull(this.getIPAddress()));
      var port = server.getListeningPort();
      Log.d(CLASSNAME, "server port is : " + port);
    } catch (IOException e) {
      e.printStackTrace();
      log.warning(e.getMessage());
      log.warning(Objects.requireNonNull(e.getCause()).toString());
      throw new RuntimeException(e.getMessage());
    }

  }

  private String getIPAddress() {

    try {
      for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
        NetworkInterface intf = en.nextElement();
        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
          InetAddress inetAddress = enumIpAddr.nextElement();
          if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
          }
        }
      }
    } catch (Exception ex) {
      Log.e(CLASSNAME, "Failed to get IP address", ex);
    }
    return null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (this.server != null) {
      server.stop();
      log.info("server has been TERMINATED MOFO");
    }
  }

}
