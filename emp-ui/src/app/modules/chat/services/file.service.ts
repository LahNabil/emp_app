import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";
import {FileDto} from "../../../models/FileDto";


@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private _http: HttpClient) {
  }

  private baseUrl = environment.apiEmp;

  loadFiles(): Observable<FileDto> {
    return this._http.get<FileDto>(`${this.baseUrl}file/`);
  }

  deleteFile(fileName: string): Observable<string> {
    return this._http.delete<string>(`${this.baseUrl}file/${fileName}`);
  }

  saveFiles(formData: FormData): Observable<HttpEvent<string[]>> {
    return this._http.post<string[]>(`${this.baseUrl}file/`, formData, {
      reportProgress: true,
      observe: 'events'
    });


  }
}

