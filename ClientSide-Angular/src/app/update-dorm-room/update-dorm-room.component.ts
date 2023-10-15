import { Component, OnInit } from '@angular/core';
import { Dorm } from '../model/Dorm.model';
import { ActivatedRoute, Router } from '@angular/router';
import { DormServiceService } from '../services/dorm-service.service';

@Component({
  selector: 'app-update-dorm-room',
  templateUrl: './update-dorm-room.component.html',
  styleUrls: ['./update-dorm-room.component.css']
})
export class UpdateDormRoomComponent implements OnInit{
  dormRoom : Dorm = new Dorm();
  id?:any;

  constructor( private route :ActivatedRoute,private dormRoomSer: DormServiceService,private router: Router){}

updateRoom(){
  this.dormRoomSer.updateDormroom(this.dormRoom , this.id).subscribe();
  alert("Dorm Room Updated Successfuly !!");
  this.router.navigate(['/dormsback']);
}

  ngOnInit(){
    this.id = this.route.snapshot.params['roomID'];
  this.dormRoomSer.getDormRoombyID(this.id).subscribe(data =>{
    this.dormRoom = data
  })


  }
}
  
