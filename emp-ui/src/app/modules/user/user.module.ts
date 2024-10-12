import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { MainComponent } from './pages/main/main.component';
import {SharedModuleModule} from "../shared-module/shared-module.module";
import { UserDetailsComponent } from './pages/user-details/user-details.component';


@NgModule({
  declarations: [
    MainComponent,
    UserDetailsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModuleModule
  ]
})
export class UserModule { }
