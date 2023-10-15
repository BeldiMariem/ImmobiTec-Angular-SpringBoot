import { Component, OnInit } from '@angular/core';
import { DormServiceService } from 'src/app/services/dorm-service.service';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';


@Component({
  selector: 'app-dormsback',
  templateUrl: './dormsback.component.html',
  styleUrls: ['./dormsback.component.css']
})
export class DormsbackComponent implements OnInit {

  DormRooms : any ;

 

  getAllDormRoom(){
    this.dromservice.getAllDormRooms().subscribe(data =>{
      this.DormRooms = data
    })
  }


  deleteDormRoom(id : number){
    console.log(id); // add this line
    this.dromservice.DeleteDormRoom(id).subscribe();
    this.getAllDormRoom();
    alert("Dorm Deleted Successfuly !!");
    this.reloadComponent();
  }


  

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  constructor(private dromservice : DormServiceService,private router: Router) { }

  ngOnInit(){
    this.getAllDormRoom();
   
  
  }




}
