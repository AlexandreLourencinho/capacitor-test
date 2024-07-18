import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiUrl = "/api";

  constructor(private http: HttpClient) {
  }

  public testCall(): Observable<any> {
    console.log('test service');
    // return this.http.get("http://10.0.2.16:8080", {responseType: 'text'});
    return this.http.get("http://10.0.2.16:8080", {params: new HttpParams(), observe: "response"});
    // https://stackoverflow.com/questions/46408537/angular-httpclient-http-failure-during-parsing RESPONSE TYPE TEXT BC OF  THE ANDROID METHOD
  }

  public testCall2(): Observable<any> {
    return this.http.post("http://10.0.2.16:8080/testendpoint", {
      id: null,
      noteContent: "blabla la note vient du front",
      categoryId: 2
    }, {params: new HttpParams(), observe: "response"});
  }


}
