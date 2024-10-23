import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NoteCardComponent} from "./note-card/note-card.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";
import {NoteCreationComponent} from './note-creation/note-creation.component';

@NgModule({
  declarations: [NoteCardComponent, NoteCreationComponent],
  imports: [
    CommonModule,
    NgbModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule
  ],
  exports: [
    NoteCardComponent,
    NoteCreationComponent
  ]
})
export class CoreModule {
}
