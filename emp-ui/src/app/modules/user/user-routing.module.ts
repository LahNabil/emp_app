import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {UserDetailsComponent} from "./pages/user-details/user-details.component";
import {UpdateUserComponent} from "./pages/update-user/update-user.component";

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
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
