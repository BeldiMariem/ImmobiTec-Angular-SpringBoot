import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';


@Component({
  selector: 'app-list-roles',
  templateUrl: './list-roles.component.html',
  styleUrls: ['./list-roles.component.css']
})
export class ListRolesComponent implements OnInit{
  private baseUrl = 'http://localhost:9000/role/getAllRoles';
  private deleteUrl = 'http://localhost:9000/role/deleteRole/';
  constructor(
 private http : HttpClient,
    private router: Router,
  ) {
}

  data: Role[] = [];
  name!:string
  headers :any
  httpOptions:any
  ngOnInit( ): void {
this.getAllUsers();

  }
  getAllUsers(){

  let httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization : `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlzQWRtaW4iOnRydWUsImV4cCI6MTY4MzM4MDI2NSwiaWF0IjoxNjgzMzYyMjY1fQ.OL4b2adli-IFb5loXHk8c3Yijqz-IuZnsEalJfAktmhOgIpNWp1OxI-UZ4ADZHwcQIgWvjb5vNKkZOlXz25dAg`
    })}

    this.http.get<Role[]>(this.baseUrl,  {
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe((d:any)=>{
     this.data = d;

 }, error => {
  this.router.navigate(['/unauth']);
});
 }
 deleteRole(id: any){
  if(confirm("Are you sure to delete this user ")) {
    this.http.delete(`${this.deleteUrl}${id}`,{
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe(d =>{
      this.getAllUsers();
    });
  }
}

}
