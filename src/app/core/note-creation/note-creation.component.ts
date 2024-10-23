import { Component, OnInit } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-note-creation',
  templateUrl: './note-creation.component.html',
  styleUrls: ['./note-creation.component.scss']
})
export class NoteCreationComponent {

  constructor(private modalService: NgbModal) {}

}
