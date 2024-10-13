import {Component, OnInit} from '@angular/core';
import {RegistrationRequest} from "../../../../models/RegistrationRequest";
import { Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.scss'
})
export class UpdateUserComponent implements OnInit{

  errorMsg: Array<string> = [];
  userRep: RegistrationRequest = {name: '', username: '', email: '',phone: ''};
  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.getUserDetails();
  }
  getUserDetails(){
    this.userService.getUserDetails().subscribe({
      next:(res)=> {
        this.userRep = res;
      },
      error: (err)=>{
        console.log('Error fetching user', err);
      }
    })
  }


  updateUser() {
    this.userService.updateUser(this.userRep).subscribe({
      next:(res)=>{
        this.router.navigate(['/user']);
      },
      error:(err)=>{
        console.log('error updating user', err);
        this.errorMsg.push('Failed to update user details');
      }
    })

  }
}
