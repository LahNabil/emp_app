import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment.development";
import {Observable} from "rxjs";
import {QuestionResponse} from "../../../models/QuestionResponse";


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  constructor(private _http: HttpClient) { }
  private baseUrl = environment.apiEmp;

  chat(question: string): Observable<QuestionResponse> {
    const params = new HttpParams().set('question', question);
    return this._http.get<QuestionResponse>(`${this.baseUrl}chat/`, { params });
  }


}
