import { Component, OnInit } from '@angular/core';
import { DormServiceService } from '../services/dorm-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-dorm-rooms',
  templateUrl: './list-dorm-rooms.component.html',
  styleUrls: ['./list-dorm-rooms.component.css']
})
export class ListDormRoomsComponent implements OnInit {
  DormRooms : any ;
   searchtext:any;
   bestRooms: any;


   likeDorm(idRoom : number) {
    var id = Number(localStorage.getItem('id'));

    this.dromservice.LikeDorm(idRoom,id).subscribe((dorm) => {
      // Replace userId with the ID of the current user
      // Handle the updated service data or UI as needed
    });

  }




  DislikeDorm(idRoom:number){
    var id = Number(localStorage.getItem('id'));

    this.dromservice.DislikeDorm(idRoom,id).subscribe((dorm)=>{

    });
  }






  getAllDormRoom(){
    this.dromservice.getAllDormRooms().subscribe(data =>{
      this.DormRooms = data
    })
  }



    getBestRooms() {
      this.dromservice.BestRooms().subscribe(data => {
        this.bestRooms = data;
      });
    }


  deleteDormRoom(id : number){
    this.dromservice.DeleteDormRoom(id).subscribe();
    this.getAllDormRoom();
    alert("Dorm Room Deleted Successfuly !!");
   this.reloadComponent();
  }

  

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  constructor(private dromservice : DormServiceService,private router: Router,private route: ActivatedRoute) { }

  ngOnInit(){
    this.getAllDormRoom();
    this.getBestRooms();

  }




}
