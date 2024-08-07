package com.example.capacitortest.http;

import static fi.iki.elonen.NanoHTTPD.MIME_PLAINTEXT;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

import android.os.Build;
import android.util.Log;

import com.example.capacitortest.configuration.AppConfig;
import com.example.capacitortest.database.entities.Note;
import com.example.capacitortest.services.NoteService;
import com.example.capacitortest.services.NoteServiceImpl;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD.*;

public class HttpController {

  private Response response;
  private NoteService noteService;

  private static final String CLASSNAME = HttpController.class.getName();
  private final Gson gson = new Gson();

  public Response manageOptionsRequest() {
    response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
    addCorsHeaders(response);
    return response;
  }

  public Response manageGetRequest(IHTTPSession session) {
    var test = "hellow world";
    var testParams = session.getParameters();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      testParams.forEach((key, value) -> {
        Log.d(CLASSNAME, key);
        value.forEach(valuePredicate -> {
          Log.d(CLASSNAME, valuePredicate);
        });
    });
    }
    Log.d(CLASSNAME, "answer is : " + test);
    try {
      Log.d(CLASSNAME, "entering post method");
      var test2 = new JSONObject();
      test2.put("response", test);
      var stringTest = test2.toString();
//      Log.d(CLASSNAME, stringTest);
      response = newFixedLengthResponse(Response.Status.OK, "application/json",
        stringTest);// TODO VOIR POUR DU BON JSON
      response.addHeader("Access-Control-Allow-Origin", "*");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return response;
  }

  public Response managePostRequest(IHTTPSession session) throws ResponseException, IOException, JSONException {
    Map<String, String> headers = session.getHeaders();
    this.noteService = new NoteServiceImpl(AppConfig.getContext());
    Map<String, String> params = new HashMap<>();
    session.parseBody(params);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      params.forEach((key, value) -> {
//        Log.d(CLASSNAME, "key is : " + key);
//        Log.d(CLASSNAME, "value is : " + value);
      });
    }
    // "{\"id\":null,\"noteContent":"blabla la note vient du front","categoryId":2}"
    //TODO GSON PARSE BODY
    var test = "hellow world";

    try {
      Log.i(CLASSNAME, "entering post method");
      var test2 = new JSONObject();
      test2.put("response", test);
      var stringTest = test2.toString();
      Log.d(CLASSNAME, stringTest);
      Note note = gson.fromJson(params.get("postData"), Note.class);
//      Log.d(CLASSNAME, "note details, content:" + note.getNoteContent());
//      Log.d(CLASSNAME, "note details, categoryId:" + note.getCategoryId());
      this.noteService.insertNote(note);
      var receiptTest = this.noteService.getNote();

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        receiptTest.forEach(predicate -> {
          Log.d(CLASSNAME, "content" + predicate.getNoteContent());
          Log.d(CLASSNAME, "category id " + predicate.getCategoryId());
          Log.d(CLASSNAME, "id" + predicate.getId());
        });
      }
      response = newFixedLengthResponse(Response.Status.OK, "application/json",
        stringTest);// TODO VOIR POUR DU BON JSON
      response.addHeader("Access-Control-Allow-Origin", "*");
      return response;
    } catch (JSONException e) {
      throw e;
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
