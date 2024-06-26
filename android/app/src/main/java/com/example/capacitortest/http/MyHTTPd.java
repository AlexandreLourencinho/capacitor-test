package com.example.capacitortest.http;

import android.util.Log;

import java.util.Objects;

import fi.iki.elonen.NanoHTTPD;

public class MyHTTPd extends NanoHTTPD {

  private final String CLASSNAME = this.getClass().getName();

  public MyHTTPd() {
    super(8080);
  }

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
    var test = "hellow world";
    Log.d(CLASSNAME, "answer is : " + test);
    var response = newFixedLengthResponse(test);// TODO VOIR POUR DU BON JSON
    response.addHeader("Content-type", "application/json");


    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

    return response;
  }

}
