import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  // public getUserName():Observable<any>{
  //   const token = localStorage.getItem('token');
  //   const headers = new HttpHeaders({
  //     Authorization: `Bearer ${token}`,
  //   })
  //   return this._http.get(`${this.baseUrl}user/username`,{headers})
  // }
  public getUserName(): Observable<string> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    // Expecting a plain text response
    return this._http.get(`${this.baseUrl}user/username`, { headers, responseType: 'text' });
  }

}
