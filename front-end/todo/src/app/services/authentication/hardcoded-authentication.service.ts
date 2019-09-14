import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(username, password) {
    if (username === 'mainul35' && password === 'secret') {
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    const loggedInUsername = sessionStorage.getItem('username');
    return !(loggedInUsername === null);
  }

  logout() {
    sessionStorage.removeItem('username');
  }
}
