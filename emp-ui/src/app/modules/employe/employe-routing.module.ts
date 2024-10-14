import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {EmployeListComponent} from "./pages/employe-list/employe-list.component";
import {ManageEmployeComponent} from "./pages/manage-employe/manage-employe.component";
import {ManageDepartementsComponent} from "../departement/pages/manage-departements/manage-departements.component";
import {PointageComponent} from "./pages/pointage/pointage.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: EmployeListComponent
      },
      {
        path: 'pointage',
        component: PointageComponent
      },
      {
        path: 'manage',
        component: ManageEmployeComponent
      },
      {
        path:'manage/:idEmp',
        component: ManageEmployeComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeRoutingModule { }
