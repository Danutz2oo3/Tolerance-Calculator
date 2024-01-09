
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import { catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private apiURL = 'http://localhost:8080/api/v1/users';


  constructor(private http: HttpClient) {}

  register(username: string, email: string, password: string): Observable<any> {
    const body = { username, email, password };
    const options = { params: { username, email, password } };

    return this.http.post<any>(`${this.apiURL}/register`, body, options).pipe(
      catchError((error) => {
        console.error('Error:', error);
        throw error;
      })
    );
  }
  loginEmail(email: string, password: string): Observable<any>{
    const body = { email, password };
    const options = { params: { email, password } };
    return this.http.get<any>(`${this.apiURL}/login-by-email`, options).pipe(
      catchError((error) => {
        console.error('Error:', error);
        throw error;
      })
    );
  }
  loginUsername(username: string, password: string): Observable<any>{
    const body = { username, password };
    const options = { params: { username, password } };

    return this.http.get<any>(`${this.apiURL}/login-by-username`, options).pipe(
      catchError((error) => {
        console.error('Error:', error);
        throw error;
      })
    );
  }
}
