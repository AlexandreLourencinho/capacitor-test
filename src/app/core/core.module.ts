import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NoteCardComponent} from "./note-card/note-card.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [NoteCardComponent],
  imports: [
    CommonModule,
    NgbModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule
  ],
  exports: [
    NoteCardComponent
  ]
})
export class CoreModule { }
