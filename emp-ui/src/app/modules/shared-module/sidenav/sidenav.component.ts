import {Component, EventEmitter, HostListener, Output} from '@angular/core';
import {navbarData} from "./nav-data";

interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrl: './sidenav.component.scss'
})
export class SidenavComponent {
  @Output() onToggleSideNav : EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth =0;
  navData =navbarData;
  @HostListener('window:resize',['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth <= 768) {
      this.collapsed = false;
      this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
    }
  }

  toggleCollapse(): void{
    this.collapsed= !this.collapsed;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }
  closeSidenav():void{
    this.collapsed = false;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }
}
