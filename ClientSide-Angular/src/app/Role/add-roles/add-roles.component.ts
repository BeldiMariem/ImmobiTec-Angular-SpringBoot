import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';

@Component({
  selector: 'app-add-roles',
  templateUrl: './add-roles.component.html',
  styleUrls: ['./add-roles.component.css']
})
export class AddRolesComponent implements OnInit{
  role:Role = new Role();
  name?:string;
  getRole="http://localhost:9000/role/addRole";
  constructor(
    private route :ActivatedRoute,
    private router: Router,

    private http:HttpClient) {

}
ngOnInit(): void {

}
onSubmit(){
this.http.post(this.getRole,{"name":this.name},{
  headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
  },
}).subscribe(data =>{
this.goToUserList();
})
}


goToUserList(){
this.router.navigate(['/listRoles']);
}
}
