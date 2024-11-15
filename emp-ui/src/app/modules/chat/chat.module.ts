import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import {SharedModuleModule} from "../shared-module/shared-module.module";
import { MainComponent } from './pages/main/main.component';
import { ChatComponent } from './pages/chat/chat.component';
import {FormsModule} from "@angular/forms";
import { FileComponent } from './pages/file/file.component';


@NgModule({
  declarations: [
    MainComponent,
    ChatComponent,
    FileComponent
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    SharedModuleModule,
    FormsModule
  ]
})
export class ChatModule { }
