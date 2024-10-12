import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartementRoutingModule } from './departement-routing.module';
import { MainComponent } from './pages/main/main.component';
import { DepartementListComponent } from './pages/departement-list/departement-list.component';
import { DepCardComponent } from './components/dep-card/dep-card.component';
import {SharedModuleModule} from "../shared-module/shared-module.module";
import { ManageDepartementsComponent } from './pages/manage-departements/manage-departements.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    MainComponent,
    DepartementListComponent,
    DepCardComponent,
    ManageDepartementsComponent,

  ],
  imports: [
    CommonModule,
    DepartementRoutingModule,
    SharedModuleModule,
    FormsModule
  ],
    exports:[

    ]
})
export class DepartementModule { }
