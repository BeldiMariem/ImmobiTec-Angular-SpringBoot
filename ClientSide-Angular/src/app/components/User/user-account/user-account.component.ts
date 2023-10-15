import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  name=localStorage.getItem('name');
  lastname=localStorage.getItem('lastname')
  fn=localStorage.getItem('name')?.substring(0,1).toUpperCase();
  ln=localStorage.getItem('lastname')?.substring(0,1).toUpperCase();

  email=localStorage.getItem('email')
  city=localStorage.getItem('city')
  state=localStorage.getItem('state')
  street=localStorage.getItem('street')
password=localStorage.getItem('password')

  d= new Date(Number(localStorage.getItem('birthday'))!)
  date =this.d.getFullYear()+'-'+this.d.getMonth()+'-'+this.d.getDate()
  birthday=this.date;

  phoneNumber=localStorage.getItem('phoneNumber')
  updateUser="http://localhost:9000/updatePass";


  constructor( private router: Router,private route:ActivatedRoute ,private http: HttpClient,private authServic:AuthServiceService) { }

  refreshSubscription!: Subscription;
i=true;
  ngOnInit() {
  var  isReloaded: boolean = false;


  this.city=localStorage.getItem('city')
  this.state=localStorage.getItem('state')
  this. street=localStorage.getItem('street')


  }


update(){
  this.authServic.getUserByEmail(
    localStorage.getItem("email")!,localStorage.getItem("token")).subscribe((data) => {
      var u =new User();
  u=data;
  u.email=this.email!,
  u.name=this.name!,
  u.lastname=this.lastname!,
  u.phoneNumber=Number(this.phoneNumber)!
    this.http.put(this.updateUser,u,{
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe((data:User) =>{
    localStorage.setItem('email', data.email!);
    localStorage.setItem('role', data.role_name!);
    localStorage.setItem('name', data.name!);
    localStorage.setItem('lastname',data.lastname!);
    localStorage.setItem('password',data.password!);
    localStorage.setItem('phoneNumber',data.phoneNumber!.toString()!)
    localStorage.setItem('birthday', data.birthday!.toString()!)
    localStorage.setItem('city', data.adresse!.city!)
    this.router.navigate(['/userAccount']);
    })
  });
}

updatePass(){
  this.authServic.getUserByEmail(
    this.email!,localStorage.getItem("token")).subscribe((data) => {
  var u =new User();
  u=data;
  u.password=this.password!
    this.http.put(this.updateUser,u,{
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe((data:User) =>{
    localStorage.setItem('email', data.email!);
    localStorage.setItem('role', data.role_name!);
    localStorage.setItem('name', data.name!);
    localStorage.setItem('lastname',data.lastname!);
    localStorage.setItem('password',data.password!);
    localStorage.setItem('phoneNumber',data.phoneNumber!.toString()!)
    localStorage.setItem('birthday', data.birthday!.toString()!)
    localStorage.setItem('city', data.adresse!.city!)
    this.router.navigate(['/userAccount']);



    })
  });
}
}


