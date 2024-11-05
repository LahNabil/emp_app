import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {DepartementListComponent} from "../departement/pages/departement-list/departement-list.component";
import {MainComponent} from "./pages/main/main.component";
import {ChatComponent} from "./pages/chat/chat.component";
import {FileComponent} from "./pages/file/file.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: ChatComponent
      },
      {
        path: 'file',
        component: FileComponent
      },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule { }
