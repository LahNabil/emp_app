import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {RegistrationRequest} from "../../../../models/RegistrationRequest";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.scss'
})
export class UserDetailsComponent implements OnInit{

  user: RegistrationRequest = {name: '',email: '',phone: '',username: ''};

  constructor(private userService: UserService) {
  }
  ngOnInit() {
    this.getUserDetails();
  }

  getUserDetails(){
    this.userService.getUserDetails().subscribe({
      next:(res)=> {
        this.user = res;
      },
      error: (err)=>{
        console.log('Error fetching user', err);
      }
    })
  }

}