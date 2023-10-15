import { HttpClient, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit{


  user:User = new User();
  roles:Role[]=[]
  getUser="http://localhost:9000/useer/";
  updateUser="http://localhost:9000/updateUser";
  gender!:string;
        firstName!:string;
        lastName!:string;

        d= new Date(Number(localStorage.getItem('birthday'))!)
        date =this.d.getFullYear()+'-'+this.d.getMonth()+'-'+this.d.getDate()
        birthday=this.date;
        phoneNumber!:string;

  constructor(
              private route :ActivatedRoute,
              private router: Router,
              private http:HttpClient) {

  }
  ngOnInit(): void {
    let e = this.route.snapshot.params['email'];
    //console.log(this.email);
    this.http.get<User>(`${this.getUser}getByEmail/${e}`).subscribe(data=>{
      this.user=data;
    },(error)=>{   this.router.navigate(['/unauth']);})
    let baseUrl = 'http://localhost:9000/getAllRoles';
    this.http.get<Role[]>(baseUrl).subscribe((d:any)=>{
      this.roles = d;

  }, error => {
    console.log(error.error.message);
  });

  }
onSubmit(){
  this.http.put(this.updateUser,this.user,{
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
    this.router.navigate(['/listUsers']);
  }
cancel(){
  this.router.navigate(['/listUsers']);
}
}
