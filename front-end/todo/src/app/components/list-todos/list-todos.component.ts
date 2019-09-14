import { Component, OnInit } from '@angular/core';


export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: boolean,
    public targetDate: Date
  ) {}
}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})

// tslint:disable-next-line:component-class-suffix

export class ListTodosComponent implements OnInit {

  todos = [
    new Todo(1, 'Todo 1', false, new Date()),
    new Todo(2, 'Todo 2', false, new Date()),
    new Todo(3, 'Todo 3', false, new Date()),
    new Todo(4, 'Todo 4', false, new Date()),
    new Todo(5, 'Todo 5', false, new Date()),
  ];

  constructor() { }

  ngOnInit() {
  }

}
