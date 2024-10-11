import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartementRoutingModule } from './departement-routing.module';
import { MainComponent } from './pages/main/main.component';
import { DepartementListComponent } from './pages/departement-list/departement-list.component';
import { DepCardComponent } from './components/dep-card/dep-card.component';



@NgModule({
  declarations: [
    MainComponent,
    DepartementListComponent,
    DepCardComponent
  ],
    imports: [
        CommonModule,
        DepartementRoutingModule,

    ]
})
export class DepartementModule { }
