import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HardcodedAuthenticationService} from '../../services/authentication/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  errorMessage = 'Invalid Credentials';
  invalidLogin = false;
  aVal = true;

  constructor(private router: Router, private hardcoadedAuthenticationService: HardcodedAuthenticationService) { }

  ngOnInit() {
  }

  handleLogin() {
    if (this.hardcoadedAuthenticationService.authenticate(this.username, this.password)) {
      this.invalidLogin = false;
      this.errorMessage = 'Login successful!';
      this.router.navigate(['welcome', this.username]);
      sessionStorage.setItem('username', this.username);
    } else {
      this.invalidLogin = true;
    }
  }
}
