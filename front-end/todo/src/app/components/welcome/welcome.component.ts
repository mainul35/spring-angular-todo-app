import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  username = '';

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    // console.log();
    this.username = this.route.snapshot.params['name'];
  }

}
