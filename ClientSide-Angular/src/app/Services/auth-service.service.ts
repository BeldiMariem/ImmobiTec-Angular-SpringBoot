import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { User } from '../models/user.model';
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  public email! :string;
  public password!:  string;
  private authUrl = 'http://localhost:9000/auth/authenticate';
  private baseUrl = 'http://localhost:9000/useer';
constructor(private http: HttpClient) {

}

login(email: string, password: string) {
  return this.http.post(this.authUrl,
    { email,
      password
    })
}

getUserByEmail(email: string,token:any): Observable<User> {
  return this.http.get<User>(`${this.baseUrl}/getByEmail/${email}`);
}


createBasicAuthToken(username: string, password: string) {
  return 'Basic ' + window.btoa(username + ":" + password);
}

registerSuccessfulLogin(username : string, password:string) {
let s: string = username+password;
}

}
