import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartementRoutingModule } from './departement-routing.module';
import { MainComponent } from './pages/main/main.component';
import { DepartementListComponent } from './pages/departement-list/departement-list.component';


@NgModule({
  declarations: [
    MainComponent,
    DepartementListComponent
  ],
  imports: [
    CommonModule,
    DepartementRoutingModule
  ]
})
export class DepartementModule { }
