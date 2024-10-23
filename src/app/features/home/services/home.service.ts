import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  // drag and drop tuto
  //https://stackblitz.com/edit/angular-drag-drop-autoscroll-long-press?file=src%2Fapp%2Fapp.component.css
  public isCardCreation = false;
  private apiUrl = "http://10.0.2.16:8080";

  constructor(private http: HttpClient) {
  }

  public openingCreation(): void {
    this.isCardCreation = !this.isCardCreation;
  }

  public testCall(): Observable<any> {
    console.log('test service');
    // return this.http.get("http://10.0.2.16:8080", {responseType: 'text'});
    return this.http.get(this.apiUrl, {params: new HttpParams(), observe: "response"});
    // https://stackoverflow.com/questions/46408537/angular-httpclient-http-failure-during-parsing RESPONSE TYPE TEXT BC OF  THE ANDROID METHOD
  }

  public testCall2(): Observable<any> {
    return this.http.post(this.apiUrl + "/testendpoint", {
      id: null,
      noteContent: "blabla la note vient du front",
      categoryId: 2
    }, {params: new HttpParams(), observe: "response"});
  }

  public testCall3(): Observable<any> {
    const params = new HttpParams().set("requested", "category");
    return this.http.get(this.apiUrl, {params, observe: "response"})
  }


}
