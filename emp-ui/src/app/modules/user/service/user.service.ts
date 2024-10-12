import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";
import {RegistrationRequest} from "../../../models/RegistrationRequest";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  public getUserDetails():Observable<RegistrationRequest>{
    return this._http.get<RegistrationRequest>(`${this.baseUrl}user/details`);
  }
}
