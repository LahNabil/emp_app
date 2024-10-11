import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {AuthenticationRequest} from "../../models/AuthenticationRequest";
import {Observable} from "rxjs";
import {AuthenticationResponse} from "../../models/AuthenticationResponse";
import {RegistrationRequest} from "../../models/RegistrationRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  authenticate(authRequest:AuthenticationRequest): Observable<AuthenticationResponse>{
    return this._http.post(`${this.baseUrl}auth/authenticate`, authRequest)
  }

  register(regRequest: RegistrationRequest):Observable<any>{
    return this._http.post(`${this.baseUrl}auth/register`, regRequest);
  }



}
