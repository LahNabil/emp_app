import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './pages/register/register.component';
import { AccueilComponent } from './pages/accueil/accueil.component';
import {httpTokenInterceptor} from './interceptors/http-token.interceptor';
import {SharedModuleModule} from "./modules/shared-module/shared-module.module";


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        AccueilComponent,

    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        SharedModuleModule
    ],
    providers: [
        HttpClient,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: httpTokenInterceptor,
            multi: true
        }
    ],
    exports: [

    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
