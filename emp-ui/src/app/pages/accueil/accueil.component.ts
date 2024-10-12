import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";

interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls:['./accueil.component.scss']
})

export class AccueilComponent implements OnInit{
  username?: string;
  isSideNavCollapsed = false;
  screenWidth =0;

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

  onToggleSideNav(data: SideNavToggle): void {
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;
  }
}
