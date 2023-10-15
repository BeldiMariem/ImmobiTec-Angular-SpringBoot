import { Component, OnInit } from '@angular/core';
import { Reservation } from '../model/Reservation.model';
import { ReservationServiceService } from '../services/reservation-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrls: ['./update-reservation.component.css']
})
export class UpdateReservationComponent implements OnInit {


  reservation : Reservation = new Reservation();
  id?:any;
  roomid?:any;

  constructor( private route :ActivatedRoute,private reservationser: ReservationServiceService,private router: Router){}


  updateReservation(){
    this.reservationser.UpdateReservation(this.reservation , this.id).subscribe();
    alert("Reservation Updated Successfuly !!");
    this.router.navigate(['/ListReservations']);
  }



  ngOnInit(){
    this.id = this.route.snapshot.params['reservationID'];
    this.reservationser.GetReservationByID(this.id).subscribe(data => {
      this.reservation = data;
      this.roomid = data.roomID; // set the roomID property of the reservation object
    })
  }



    formatDate(timestamp: number): string {
      const date = new Date(timestamp);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    }

    
  }