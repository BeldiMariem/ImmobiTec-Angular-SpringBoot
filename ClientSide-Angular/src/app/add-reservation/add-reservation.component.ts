import { Component, OnInit } from '@angular/core';
import { Reservation } from '../model/Reservation.model';
import { ReservationServiceService } from '../services/reservation-service.service';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css']
})
export class AddReservationComponent implements OnInit {

value : any;
  reservation : Reservation = new Reservation();

  constructor(private reservationservice : ReservationServiceService ,private fb:FormBuilder,private router : Router,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      param => {
        this.value = param['roomID']
        });
  }
AddReservation(){
var id = Number(localStorage.getItem('id'));
this.reservationservice.AddReservation(this.reservation,id,this.value).subscribe();
alert("Your reservation was added succesfuly");
  this.router.navigate(['/ListDormRooms']);
}


reloadComponent(): void {
  const currentRoute = this.router.url.split('?')[0];
  this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([currentRoute]);
  });
}

}
