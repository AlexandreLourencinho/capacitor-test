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
    plug.echo( { value: "tamere"});
    // https://capacitorjs.com/docs/android/custom-code
    // https://github.com/ionic-enterprise/capacitor-plugin-tutorial/blob/main/src/plugins/screen-orientation/index.ts
    // https://github.com/robingenz/capacitor-plugin-demo/blob/main/src/app/modules/badge/badge.page.ts
    // https://www.youtube.com/watch?v=MgjzFJHMNAM&ab_channel=TechBinomial
    // https://capgo.app/blog/angular-mobile-app-capacitor/#using-capacitor-plugins
    // https://capacitorjs.com/docs/plugins/tutorial/android-implementation
    // https://capacitorjs.com/docs/plugins/tutorial/using-the-plugin-api
  }

}
