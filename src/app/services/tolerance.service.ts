import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {standardAllowance} from "../model/standardAllowance.model";
import {gradeOfTolerance} from "../model/gradeOfTolerance.model";
import {Borehole} from "../model/borehole.model";

@Injectable({
  providedIn: 'root'
})


export class ToleranceService {

  constructor(private http: HttpClient) {
  }

  getStandardAllowancePost(gradeOfTolerance?: number): Observable<standardAllowance[]> {
    let params = new HttpParams();
    if (gradeOfTolerance) {
      params = params.append('gradeOfTolerance', gradeOfTolerance.toString());
    }
    return this.http.get<standardAllowance[]>('http://localhost:8080/api/v1/standardAllowance', {params});
  }

  getGradeOfTolerancePost(standardAllowanceId?: number): Observable<gradeOfTolerance[]> {
    let params = new HttpParams();
    if (standardAllowanceId) {
      params = params.append('standardAllowanceId', standardAllowanceId.toString());
    }
    return this.http.get<standardAllowance[]>('http://localhost:8080/api/v1/gradeOfTolerance', {params});
  }

  getTolerance(nominalDimension: number, standardAllowance: number, gradeOfTolerance: number): Observable<Borehole> {
    let params = new HttpParams();
    params = params.append('nominalDimension', nominalDimension.toString());
    params = params.append('standardAllowance', standardAllowance.toString());
    params = params.append('gradeOfTolerance', gradeOfTolerance.toString());
    return this.http.get<Borehole>('http://localhost:8080/api/v1/tolerance', {params});
  }
}
