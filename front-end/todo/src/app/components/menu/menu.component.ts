import { Component, OnInit } from '@angular/core';
import {HardcodedAuthenticationService} from '../../services/authentication/hardcoded-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  // isUserLoggedIn = false;
  constructor(private hardcoadedAuthenticateService: HardcodedAuthenticationService) { }

  ngOnInit() {
    // this.isUserLoggedIn = this.hardcoadedAuthenticateService.isUserLoggedIn();
  }

}
