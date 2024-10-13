import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeRoutingModule } from './employe-routing.module';
import { MainComponent } from './pages/main/main.component';
import { EmployeListComponent } from './pages/employe-list/employe-list.component';
import {SharedModuleModule} from "../shared-module/shared-module.module";
import { ManageEmployeComponent } from './pages/manage-employe/manage-employe.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    MainComponent,
    EmployeListComponent,
    ManageEmployeComponent
  ],
  imports: [
    CommonModule,
    EmployeRoutingModule,
    SharedModuleModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EmployeModule { }
