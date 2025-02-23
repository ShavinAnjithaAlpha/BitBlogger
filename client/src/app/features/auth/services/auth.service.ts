import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Credentials from '../models/credentials';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials: Credentials): Observable<any> {
    return this.http.post('http://localhost:3000/login', credentials);
  }
}
