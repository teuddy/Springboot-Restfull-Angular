import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { throwError as observaleThrowError , Observable } from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient) { }


  getAll(): Observable<any> {
    return this.http.get('//localhost:8181/teachers').pipe(
      catchError(this.errorHandler));
  
  }

  errorHandler(error: HttpErrorResponse){
    return observaleThrowError(error.message || "server error");
  }
}
