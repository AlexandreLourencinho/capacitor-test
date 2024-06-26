package com.example.capacitortest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.room.Room;

import com.example.capacitortest.database.configuration.DatabaseConfiguration;
import com.example.capacitortest.database.dao.CategoryDao;
import com.example.capacitortest.database.dao.NoteDao;
import com.example.capacitortest.database.entities.Category;
import com.example.capacitortest.http.MyHTTPd;
import com.example.capacitortest.plugins.CreateCategoryPlugin;
import com.getcapacitor.BridgeActivity;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class MainActivity extends BridgeActivity {
//  https://developer.android.com/training/data-storage/room?hl=fr
  private final String CLASSNAME = this.getClass().getName();

  private final Logger log = Logger.getLogger(CLASSNAME);
  private MyHTTPd server;


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
    // Méthode pour obtenir l'adresse IP de l'appareil
    try {
      for (java.util.Enumeration<java.net.NetworkInterface> en = java.net.NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
        java.net.NetworkInterface intf = en.nextElement();
        for (java.util.Enumeration<java.net.InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
          java.net.InetAddress inetAddress = enumIpAddr.nextElement();
          if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address) {
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
