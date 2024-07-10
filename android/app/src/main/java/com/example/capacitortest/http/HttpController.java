package com.example.capacitortest.http;

import static fi.iki.elonen.NanoHTTPD.MIME_PLAINTEXT;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import fi.iki.elonen.NanoHTTPD.*;

public class HttpController {

  private static final String CLASSNAME = HttpController.class.getName();
  private Response response;

  public Response manageOptionsRequest() {
    response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
    addCorsHeaders(response);
    return response;
  }

  public Response manageGetRequest() {
    var test = "hellow world";
    Log.d(CLASSNAME, "answer is : " + test);
    try {
      Log.d(CLASSNAME, "entering post method");
      var test2 = new JSONObject();
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

  public Response managePostRequest(IHTTPSession session) {
    var test = "hellow world";

    try {
      Log.d(CLASSNAME, "entering post method");
      var test2 = new JSONObject();
      test2.put("response", test);
      var stringTest = test2.toString();
      Log.d(CLASSNAME, stringTest);
      response = newFixedLengthResponse(Response.Status.OK, "application/json",
        stringTest);// TODO VOIR POUR DU BON JSON
      response.addHeader("Access-Control-Allow-Origin", "*");
      return response;
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  public Response managePutRequest(IHTTPSession session) {
    return null;
  }

  public Response manageDeleteRequest(IHTTPSession session) {
    return null;
  }

  private void addCorsHeaders(Response response) {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.addHeader("Access-Control-Allow-Headers",
      "origin, content-type, accept, authorization");
  }

}
