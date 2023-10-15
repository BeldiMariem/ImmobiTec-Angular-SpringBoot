import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Adress } from '../models/adress.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  email! :string;
  password!:  string;
  gender!:string;
  name!:string;
  lastname!:string;
  birthday!:Date;
  phoneNumber!:string;
  private registerUrl = 'http://localhost:9000/auth/register';
constructor(private http: HttpClient) {
}
  register(email: string, password: string,gender:string,name:string,lastname:string,birthday:Date,phoneNumber:string,adresse:Adress) {

    return this.http.post(this.registerUrl,
      { "name":name,
        "lastname":lastname,
        "password":password,
        "email":email,
        "birthday":birthday,
        "phoneNumber":Number(phoneNumber),
        "wallet":0.0,
        "role_id":2,
        "picture":"picture","adresse":adresse

      })
  }
}
