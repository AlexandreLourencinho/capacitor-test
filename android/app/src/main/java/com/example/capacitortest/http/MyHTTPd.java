package com.example.capacitortest.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import fi.iki.elonen.NanoHTTPD;

public class MyHTTPd extends NanoHTTPD {

  public MyHTTPd() {
    super(8080);
  }

  private static final String CLASSNAME = MyHTTPd.class.getName();
  private final HttpController httpController = new HttpController();


  public MyHTTPd(int port) {
    super(port);
  }

  public MyHTTPd(String hostname, int port) {
    super(hostname, port);
  }

  @Override
  public Response serve(IHTTPSession session) {
    Log.d(this.getClass().getName(), "Request received: " + session.getUri());
    Log.d(CLASSNAME, session.getRemoteHostName());
    Log.d(CLASSNAME, session.getRemoteIpAddress());
    Log.d(CLASSNAME, session.getHeaders().toString());
    Log.d(CLASSNAME, session.getParameters().toString());
    Log.d(CLASSNAME, session.getMethod().toString());
    Log.d(CLASSNAME, session.getUri());

    switch (session.getMethod()) {

      case OPTIONS -> {
        return httpController.manageOptionsRequest();
      }

      case GET -> {
        return httpController.manageGetRequest();
      }

      case POST -> {
        return httpController.managePostRequest(session);
      }

      case PUT -> {
        return httpController.managePutRequest(session);
      }

      case DELETE -> {
        return httpController.manageDeleteRequest(session);
      }

      default -> {
        return null;
      }

    }
    // voir sur chatty
  }


}
