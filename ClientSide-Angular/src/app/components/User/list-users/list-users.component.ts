import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Adress } from 'src/app/models/adress.model';
import { Role } from 'src/app/models/role.model';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit{
  private baseUrl = 'http://localhost:9000/user/getAllUsers?';
  private deleteUrl = 'http://localhost:9000/user/deleteUser/';
  constructor(
 private http : HttpClient,
    private router: Router,
    private route:ActivatedRoute
  ) {      this.numbers = Array.from({length:this.totalElements},(v,k)=>k+1);
}
fn=localStorage.getItem('name')?.substring(0,1).toUpperCase();
ln=localStorage.getItem('lastname')?.substring(0,1).toUpperCase();
  data: User[] = [];
  adresse?:Adress;
  role?:Role;
  totalElements=0;
  totalPages=1;
  numbers:any;
  page=0;
  name="";
  lastname="";
  email="";
  em=""
  size=3;
  address:any;

  ngOnInit( ): void {
this.getAllUsers(this.page,this.size);

  }
   getAllUsers(page:Number,size:Number){
     this.http.get<User[]>(`${this.baseUrl}page=${page}&size=${size}&sort=&direction=desc&name&lastname=&email`,{
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe((d:any)=>{
      this.data = d["content"];

      this.adresse=d["content"].adresse;
      this.role=d["content"].role;
      this.totalElements = d['totalElements'];
      this.totalPages=d['totalPages']

  }, error => {
    this.router.navigate(['/unauth']);
    console.log(error.error.message);
});
  }
  deleteUser(id: any){
    if(confirm("Are you sure to delete this user ")) {
      this.http.delete(`${this.deleteUrl}${id}`,{
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
    }).subscribe(d =>{
        this.getAllUsers(this.page,4);
      });
    }
  }
get(){
  this.http.get<User[]>(`${this.baseUrl}page=0&size=4&sort=&direction=desc&name=${this.name}&lastname=${this.lastname}&email=${this.em}`,{
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
    },
}).subscribe((d:any)=>{
    this.data = d["content"];
    this.totalElements = d['totalElements'];
    this.totalPages=d['totalPages']

}, error => {
  console.log(error.error.message);
});
}

next() {
  if ((this.page<this.totalPages)&&(this.totalPages!=1))
  {  this.page++;
    this.getAllUsers(this.page,this.size)}

}
before() {
  if (this.page>0)
  {this.page--;
  this.getAllUsers(this.page,this.size);}
}
}
