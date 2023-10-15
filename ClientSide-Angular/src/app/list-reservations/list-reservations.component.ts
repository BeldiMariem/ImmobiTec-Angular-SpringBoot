import { Component, OnInit } from '@angular/core';
import { ReservationServiceService } from '../services/reservation-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-reservations',
  templateUrl: './list-reservations.component.html',
  styleUrls: ['./list-reservations.component.css']
})
export class ListReservationsComponent implements OnInit {

  reservation:any;
  totalRevenue = 0;

  CalculateTotalRevenue() {
    this.reservationservice.calculateTotalRevenue().subscribe(data => {
      this.totalRevenue = data;
    });
  }
  
  GetAllReservations(){
    this.reservationservice.getAllReservations().subscribe(data =>{
      this.reservation = data
    })
  }



  pdf(){
    console.log('PDF function called');
    this.reservationservice.PDF().subscribe(response => {
      const blob = new Blob([response], { type: 'application/pdf' });
      const url = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'reservations.pdf';
      link.click();
      URL.revokeObjectURL(url);
    }, error => {
      console.log(error);
    });
  }





  constructor(private reservationservice : ReservationServiceService, private router: Router) {
    this.reservationservice.calculateTotalRevenue().subscribe(revenue => {
      this.totalRevenue = revenue;
    });
   }

  
  ngOnInit(){
this.GetAllReservations();
this.CalculateTotalRevenue();
  }


  deleteReservation(id: number){
    this.reservationservice.DeleteReservation(id).subscribe();
    this.GetAllReservations();
    alert("Reservation Deleted Successfuly !!");
    this.reloadComponent();
  }



  

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }
}
