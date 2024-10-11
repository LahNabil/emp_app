import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrl: './accueil.component.scss'
})
export class AccueilComponent implements OnInit{
  username?: string;

  constructor(private userService: UserService) {
  }
  ngOnInit() {
    this.getUsername();
  }
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

}
