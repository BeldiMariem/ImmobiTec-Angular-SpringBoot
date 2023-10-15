import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { Adress } from 'src/app/models/adress.model';
import { Role } from 'src/app/models/role.model';
import { User } from 'src/app/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit{
  user:User = new User();
  roles:Role[]=[]
  adresse:Adress = new Adress();
  street_name:any
  state:any
  city:any
  getUser="http://localhost:9000/user/add";
  constructor(
    private route:ActivatedRoute ,
    private router: Router,
    private http:HttpClient) {

}
ngOnInit(): void {
  let baseUrl = 'http://localhost:9000/getAllRoles';
  this.http.get<Role[]>(baseUrl).subscribe((d:any)=>{
    this.roles = d;

}, error => {
  console.log(error.error.message);
});
}
onSubmit(){
  this.adresse.state=this.state;
  this.adresse.city=this.city;
  this.adresse.street_name=this.street_name
  this.user.adresse=this.adresse;
this.http.post(this.getUser,this.user).subscribe(data =>{
this.goToUserList();
})
}

cancel(){
  this.router.navigate(['/listUsers']);
}
goToUserList(){
this.router.navigate(['/listUsers']);
}
}
