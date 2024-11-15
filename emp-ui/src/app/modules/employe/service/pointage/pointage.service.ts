import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../../environments/environment.development";
import {Observable} from "rxjs";
import {PageResponsePointage} from "../../../../models/PageResponsePointage";
import {PointageResponse} from "../../../../models/PointageResponse";

@Injectable({
  providedIn: 'root'
})
export class PointageService {
  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  getAllPointages(page: number, size: number):Observable<PageResponsePointage>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this._http.get<PageResponsePointage>(`${this.baseUrl}pointage/`, {params});
  }
  getPointages():Observable<any>{
    return this._http.get(`${this.baseUrl}pointage/all`);
  }
  savePointage(poi: PointageResponse):Observable<PointageResponse>{
    return this._http.post<PointageResponse>(`${this.baseUrl}pointage/`, poi);
  }
  archivePointage(idPoi: number | undefined):Observable<any>{
    return this._http.patch(`${this.baseUrl}pointage/${idPoi}`, {})
  }
  deletePointage(idPoi: number):Observable<any>{
    return this._http.delete(`${this.baseUrl}pointage/${idPoi}`);
  }

}
