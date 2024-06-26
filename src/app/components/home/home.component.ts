import { Component } from '@angular/core';
import {HomeService} from "./services/home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  public response: string | undefined;

  constructor(private readonly service: HomeService) {
  }

  testNanoHTTPDrequest() {
    this.service.testCall().subscribe((predicate) => {
      console.log(predicate);
      for (let key in predicate) {
        console.log(key);
      }
      console.log('this response is working');
      console.log('predicate', predicate);
      this.response = predicate;
    }, (error) => {
      console.log('error', error);
      console.log('error.message', error.message);
      console.log('error.header', error.header);
      console.log('error.status', error.status);
      console.log('error.url', error.url);
      console.log('error.ok', error.ok);
      console.log('error.name', error.name);
      console.log('error.statusText', error.statusText);
      for (let key in error) {
        console.log('key', key);
      }
    });

  }
}
