import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { switchMap, timer } from 'rxjs';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email!: string;
  tokenString!:string;

  constructor(private http: HttpClient) {}
  private authUrl = 'http://localhost:9000/auth/forgot_password';
  tokenUrl='http://localhost:9000/auth/reset_password?token=Dssa7Yiqrnu3T71wxX4Yqd51SW5eEm';
  ngOnInit(): void {

  }
  validate(email:string){

      return this.http.post(this.authUrl, email ).subscribe(data=>{
        alert("We have sent a reset password link to your email. Please check")
      },error=>{alert("We have sent a reset password link to your email. Please check")})

  }




}
