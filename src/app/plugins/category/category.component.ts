import { Component, OnInit } from '@angular/core';
import {registerPlugin} from "@capacitor/core";
import {CategoryPlugin} from "./definitions";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const plug = registerPlugin<CategoryPlugin>("categoryPlugin");
    void plug.echo( { value: "test"});
    console.log('plug', plug);
  }

}
