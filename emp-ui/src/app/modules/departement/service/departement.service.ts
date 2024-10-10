import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";
import {PageResponseDepartement} from "../../../models/PageResponseDepartement";
import {DepartementResponse} from "../../../models/DepartementResponse";

@Injectable({
  providedIn: 'root'
})
export class DepartementService {
  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  getAllDepartements(page: number, size: number):Observable<PageResponseDepartement>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this._http.get<PageResponseDepartement>(`${this.baseUrl}departement/`, {params});
  }



}