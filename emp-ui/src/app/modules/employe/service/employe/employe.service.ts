import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../../environments/environment.development";
import {Observable} from "rxjs";
import {PageResponseEmploye} from "../../../../models/PageResponseEmploye";
import {EmployeResponse} from "../../../../models/EmployeResponse";

@Injectable({
  providedIn: 'root'
})
export class EmployeService {
  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  getEmployeById(idEmp: number):Observable<EmployeResponse>{
    return this._http.get<EmployeResponse>(`${this.baseUrl}employe/${idEmp}`);
  }

  getAllEmployes(page: number, size: number):Observable<PageResponseEmploye>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this._http.get<PageResponseEmploye>(`${this.baseUrl}employe/`, {params});
  }

  archiveEmploye(idEmp: number | undefined):Observable<any>{
    return this._http.patch(`${this.baseUrl}employe/${idEmp}`, {})
  }

  getEmpAll():Observable<any>{
    return this._http.get(`${this.baseUrl}employe/all`);
  }

  saveEmploye(emp: EmployeResponse):Observable<Object>{
    return this._http.post(`${this.baseUrl}employe/`, emp);
  }


}
