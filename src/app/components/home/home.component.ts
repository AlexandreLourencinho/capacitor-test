import {Component} from '@angular/core';
import {HomeService} from "./services/home.service";
import {catchError, finalize, map, tap} from "rxjs";
import {error} from "@angular/compiler-cli/src/transformers/util";

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
    this.service.testCall().pipe(
      map(mapping => mapping.body),
      tap(predicate => {
        console.log('predicate.response avant parse', predicate);
        for (let key in predicate) {
          console.log("key :", key);
          console.log('predicate[key]', predicate[key]);
          console.log('predicate.response', predicate.response);
          this.response = predicate.response;
        }
      }),
      catchError((error, test) => {
        console.log('error', error);
        console.log('test', test);
        for (let key in test) {
          console.log("key :", key);
          test.subscribe(predtest => {
            console.log('predtest', predtest);
          })
        }
        return test;
      }),
      finalize(() => {
        console.log('finit!');
      })
    ).subscribe();

    this.service.testCall2().pipe(
      map(mapping => {
        console.log("on rentre dans la deuxième méthode")
        console.log('mapping', mapping);
        return mapping.body;
      }),
      tap(predicate => {
        console.log('predicate.response avant parse', predicate);
        for (let key in predicate) {
          console.log("key :", key);
          console.log('predicate[key]', predicate[key]);
          console.log('predicate.response', predicate.response);
          this.response = predicate.response;
        }
      }),
      catchError((error, test) => {
        console.log('error', error);
        console.log('test', test);
        for (let key in test) {
          console.log("key :", key);
          // @ts-ignore
          console.log('test[key]', test[key]);
          test.subscribe(predtest => {
            console.log('predtest', predtest);
          })
        }
        return test;
      }),
      finalize(() => {
        console.log('finit!');
      })
    ).subscribe();
  }

  public testListNotesAndCategories(): void {
    this.service.testCall3().pipe(
      tap(predicate => {
        console.log('predicate', predicate.response);
      }),
      catchError((error, caught) => {
        console.error(error);
        return caught;
      })
    ).subscribe();
  }


}
