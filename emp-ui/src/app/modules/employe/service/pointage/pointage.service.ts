import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../../environments/environment.development";
import {Observable} from "rxjs";
import {PageResponseEmploye} from "../../../../models/PageResponseEmploye";
import {PageResponsePointage} from "../../../../models/PageResponsePointage";

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

}
