import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Answer, Question } from './back-end-types';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {

  protected readonly baseUrl: string = "http://localhost:8080/api";

  constructor(protected http: HttpClient) { }

  getUser(username: string): any
  {
    const url = `${this.baseUrl}/user/${username}`;
    return this.http.post<any>(url, null);
  }

  getQuestions(): Observable<Array<Question>>
  {
    const url = `${this.baseUrl}/questions`;
    return this.http.get<Array<Question>>(url);
  }

  submitAnswers(answers: Array<Answer>, userId: number): any
  {
    const url = `${this.baseUrl}/submitAnswers/${userId}`;
    return this.http.post<any>(url, answers);
  }
}
