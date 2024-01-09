// calculation.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {historyElem} from "../model/historyElem.model";
import {Borehole} from "../model/borehole.model";

@Injectable({
  providedIn: 'root',
})
export class HistoryService {
  private apiUrl = 'http://localhost:8080/api/v1/history';

  constructor(private http: HttpClient) {}

  private historyDataSubject = new BehaviorSubject<any>(null);

  sendHistoryData(data: any): void {
    this.historyDataSubject.next(data);
  }

  getHistoryData(): BehaviorSubject<any> {
    return this.historyDataSubject;
  }
  getCalculationHistory(userId: number): Observable<Borehole[]> {
    // Adjust the endpoint or parameters based on your backend API
    return this.http.get<Borehole[]>(`${this.apiUrl}/get?id=${userId}`);
  }
}
