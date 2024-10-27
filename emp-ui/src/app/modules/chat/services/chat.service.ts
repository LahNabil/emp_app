import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  chat(question: string): Observable<string> {
    const params = new HttpParams().set('question', question);
    console.log(`Sending request to: ${this.baseUrl}chat/?question=${question}`);
    return this._http.get<string>(`${this.baseUrl}chat/`, { params });
  }

}
