import { Component } from '@angular/core';
import {RegistrationRequest} from "../../models/RegistrationRequest";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  regRequest: RegistrationRequest = {email: '', password: '', name: '',username: '', phone:'' };
  errorMsg: Array<string> = [];

  constructor(private router: Router, private authService: AuthenticationService) {
  }

  register() {
    this.errorMsg= [];
    this.authService.register(this.regRequest)
      .subscribe({
        next:()=>{
          this.router.navigate(['login']);
        },
        error: (err)=> {
          this.errorMsg = err.error.validationErrors;
        }
      })

  }

  login() {
    this.router.navigate(['login'])
  }
}
