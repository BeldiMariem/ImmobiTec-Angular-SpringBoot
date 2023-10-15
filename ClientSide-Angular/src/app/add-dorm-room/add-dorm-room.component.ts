import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule, FormControl } from '@angular/forms';
import { DormServiceService } from '../services/dorm-service.service';
import { Router } from '@angular/router';
import { Dorm } from '../model/Dorm.model';

@Component({
  selector: 'app-add-dorm-room',
  templateUrl: './add-dorm-room.component.html',
  styleUrls: ['./add-dorm-room.component.css']
})
export class AddDormRoomComponent implements OnInit {
 
  dormRoom : Dorm = new Dorm();


  constructor(private dormservice : DormServiceService ,private fb:FormBuilder,private route : Router) {
  }
      ngOnInit(): void {
      }
  AddDormRoom(){

   this.dormservice.AddDormRoom(this.dormRoom).subscribe({
      next:(Response)=> {console.log(Response)},
      error:(error)=> {console.log(error)}})
      alert("Your room was added succesfuly");
      this.route.navigate(['/dormsback']);
  }
  }
