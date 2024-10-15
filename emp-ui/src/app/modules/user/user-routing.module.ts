import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {UserDetailsComponent} from "./pages/user-details/user-details.component";
import {UpdateUserComponent} from "./pages/update-user/update-user.component";
import {ChangePasswordComponent} from "./pages/change-password/change-password.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children:[
      {
        path: '',
        component: UserDetailsComponent
      },
      {
        path: 'update',
        component: UpdateUserComponent
      },
      {
        path: 'changepassword',
        component: ChangePasswordComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
