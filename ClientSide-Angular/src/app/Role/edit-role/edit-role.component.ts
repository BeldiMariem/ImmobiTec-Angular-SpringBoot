import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';

@Component({
  selector: 'app-edit-role',
  templateUrl: './edit-role.component.html',
  styleUrls: ['./edit-role.component.css']
})
export class EditRoleComponent  implements OnInit{
  getUser="http://localhost:9000/role/";
  updateUser="http://localhost:9000/role/updateRole";
  id?:any;
 name?:any
 role:Role=new Role();
  constructor(
              private route :ActivatedRoute,
              private router: Router,
              private http:HttpClient) {

  }
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    //console.log(this.email);
    this.http.get<Role>(`${this.getUser}getRoleById/${this.id}`,{
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
  }).subscribe(data=>{
      this.role=data;
      this.name=this.role.name;
    })

  }
onSubmit(){
  this.http.put(this.updateUser,{"id":this.id,"name":this.name}).subscribe(data =>{
    this.goToUserList();
  })
}
goToUserList(){
  this.router.navigate(['/listRoles']);
}

}
