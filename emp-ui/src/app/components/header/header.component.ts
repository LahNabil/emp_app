import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit{

  username?: string;
  user?: any;

  constructor(private userService: UserService) {
  }
  ngOnInit() {
    this.getUsername();
    // this.getUser();
  }
  // getUser(){
  //   this.userService.getUser().subscribe({
  //       next:(res)=>{
  //         this.username = res;
  //         console.log(this.user);
  //       },
  //       error:(err)=>{
  //         console.error('failed to get username', err);
  //       }
  //     }
  //   )
  // }

  getUsername(){
    this.userService.getUserName().subscribe({
      next:(res)=>{
        this.username = res;
        console.log(this.username);
      },
      error:(err)=>{
        console.error('failed to get username', err);
      }
      }
    )
  }
  logout() {
    return null;
  }
}
