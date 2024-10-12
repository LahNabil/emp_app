import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {DepartementListComponent} from "./pages/departement-list/departement-list.component";
import {ManageDepartementsComponent} from "./pages/manage-departements/manage-departements.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: DepartementListComponent
      },
      {
        path:'manage',
        component: ManageDepartementsComponent
      },
      {
        path:'manage/:idDep',
        component: ManageDepartementsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartementRoutingModule { }
