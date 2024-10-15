import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { MainComponent } from './pages/main/main.component';
import {SharedModuleModule} from "../shared-module/shared-module.module";
import { UserDetailsComponent } from './pages/user-details/user-details.component';
import { UpdateUserComponent } from './pages/update-user/update-user.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ChangePasswordComponent } from './pages/change-password/change-password.component';


@NgModule({
  declarations: [
    MainComponent,
    UserDetailsComponent,
    UpdateUserComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModuleModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
