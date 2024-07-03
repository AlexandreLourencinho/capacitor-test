package com.example.capacitortest.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
    Log.d(CLASSNAME, session.getMethod().toString());
    Log.d(CLASSNAME, session.getUri());
    if (Method.OPTIONS.equals(session.getMethod())) {
      return createCorsResponse();
    }
    var test = "hellow world";
    Log.d(CLASSNAME, "answer is : " + test);
    var response = newFixedLengthResponse(Response.Status.OK, "application/json", test);// TODO VOIR POUR DU BON JSON
    // voir sur chatty

    var test2 = new JSONObject();
    try {
      test2.put("response", test);
      var stringTest = test2.toString();
      Log.d(CLASSNAME, stringTest);
      response = newFixedLengthResponse(Response.Status.OK, "application/json",
        stringTest);// TODO VOIR POUR DU BON JSON
      response.addHeader("Access-Control-Allow-Origin", "*");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }


    return response;
  }

  private Response createCorsResponse() {
    Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
    addCorsHeaders(response);
    return response;
  }

  private void addCorsHeaders(Response response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
   // return response;
  }

}
