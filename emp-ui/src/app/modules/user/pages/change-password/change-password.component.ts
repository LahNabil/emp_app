import { Component } from '@angular/core';
import { UserService } from "../../service/user.service";
import { Router } from "@angular/router";
import { ChangePasswordRequest } from "../../../../models/ChangePasswordRequest";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'] // Fix: styleUrls should be plural
})
export class ChangePasswordComponent {

  errorMsg: Array<string> = [];
  request: ChangePasswordRequest = {currentPassword: '', newPassword: '', confirmationPassword: ''};

  constructor(private userService: UserService, private router: Router) {}

  changePassword() {
    // Validation: Check if newPassword and confirmationPassword match
    if (this.request.newPassword !== this.request.confirmationPassword) {
      this.errorMsg = ['Passwords do not match'];
      return;
    }

    this.userService.changePassword(this.request).subscribe({
      next: (res) => {
        alert('Password changed successfully!');
        this.router.navigate(['/user']);
      },
      error: (err) => {
        // Backend should send validation errors. If it's structured differently, adjust here.
        if (err.error && err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg = ['Failed to change password. Please try again later.'];
        }
      }
    });
  }
}
