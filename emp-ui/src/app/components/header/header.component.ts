import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  username?: string;
  user?: any;

  constructor(private userService: UserService, private router: Router) {
  }



  logout() {
    window.localStorage.removeItem('token');
    this.router.navigate(['/login'])
  }
}
