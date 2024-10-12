import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {authGuard} from "./guard/auth.guard";
import {AccueilComponent} from "./pages/accueil/accueil.component";

const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', component: AccueilComponent, canActivate:[authGuard]},
  {path: 'departement', canActivate:[authGuard],
  loadChildren:()=> import('./modules/departement/departement.module').then(m => m.DepartementModule)
  },
  {path: 'user', canActivate:[authGuard],
  loadChildren:()=> import('./modules/user/user.module').then(m=> m.UserModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
