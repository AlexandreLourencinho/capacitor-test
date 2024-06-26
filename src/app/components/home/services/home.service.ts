import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiUrl = "/api";

  constructor(private http: HttpClient) { }

  public testCall(): Observable<any> {
    console.log('test service');
    return this.http.get("http://10.0.2.16:8080", {responseType: 'text'});
    // https://stackoverflow.com/questions/46408537/angular-httpclient-http-failure-during-parsing RESPONSE TYPE TEXT BC OF  THE ANDROID METHOD
  }


}
