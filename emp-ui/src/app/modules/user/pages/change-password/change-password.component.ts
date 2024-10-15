import { Component } from '@angular/core';
import { UserService } from "../../service/user.service";
import { Router } from "@angular/router";
import { ChangePasswordRequest } from "../../../../models/ChangePasswordRequest";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {

  errorMsg: Array<string> = [];
  request: ChangePasswordRequest = {currentPassword: '', newPassword: '', confirmationPassword: ''};
  visible: boolean = true;
  changeType: boolean = true;

  constructor(private userService: UserService, private router: Router) {}
  viewpass(){
    this.visible = !this.visible;
    this.changeType= !this.changeType;
  }


  changePassword() {
    this.userService.changePassword(this.request).subscribe({
      next: (res) => {
        alert('Password changed successfully!');
        this.router.navigate(['/user']);
      },
      error: (err) => {
        if (err.error && err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg = ['Failed to change password. Please try again later.'];
        }
      }
    });
  }
}
