import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {ChangePasswordRequest} from "../../../../models/ChangePasswordRequest";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.scss'
})
export class ChangePasswordComponent {

  request: ChangePasswordRequest = {currentPassword: '', newPassword: '' , confirmationPassword: ''};

  constructor(private userService: UserService, private router: Router) {
  }
  changePassword(){
    this.userService.changePassword(this.request).subscribe({
      next:(res)=>{
        alert('Password changed successfully!');
        this.router.navigate(['/user']);
      },
      error: (error) => {
        // Handle error response
        console.error('Error changing password', error);
        // You might want to display an error message to the user
      }
    })

  }

}
