import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  error = {
    code: 404,
    message: 'Not Found!'
  };

  isError = true;
  constructor() { }

  ngOnInit() {
  }

}
