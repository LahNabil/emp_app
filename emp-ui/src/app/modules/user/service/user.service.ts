import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";
import {RegistrationRequest} from "../../../models/RegistrationRequest";
import {ChangePasswordRequest} from "../../../models/ChangePasswordRequest";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  public getUserDetails():Observable<RegistrationRequest>{
    return this._http.get<RegistrationRequest>(`${this.baseUrl}user/details`);
  }

  public updateUser(request: RegistrationRequest):Observable<RegistrationRequest>{
    return this._http.put<RegistrationRequest>(`${this.baseUrl}user/`, request);
  }
  public changePassword(request: ChangePasswordRequest):Observable<any>{
    return this._http.patch(`${this.baseUrl}user/`, request);
  }
}
