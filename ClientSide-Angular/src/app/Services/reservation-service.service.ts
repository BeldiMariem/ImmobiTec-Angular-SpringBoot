import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../model/Reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {
  private listReservation = 'http://localhost:9000/reservation/getAllReservations';
  private  deleteReservation ='http://localhost:9000/reservation/deleteReservation';
  private addReservation = 'http://localhost:9000/reservation/addReservation';
  private getReservationByID = 'http://localhost:9000/reservation/getReservationByID';
  private updateReservation ='http://localhost:9000/reservation/updateReservation';
  private calculateRevenue = 'http://localhost:9000/reservation/reservations/revenue';
  private pdf = 'http://localhost:9000/pdf/export';
  

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  }

  /*--------------Add Reservations ---------------*/

AddReservation(reservation : Reservation,id:number, idr:number):Observable<Object>{
  return this.http.post(`${this.addReservation}/${id}/${idr}`,reservation);

}


 /*--------------List Reservations--------------*/
  getAllReservations():Observable<any[]>{
    return this.http.get<any[]>(this.listReservation,this.httpOptions);
    }

      /*--------------Delete Reservation--------------*/
  DeleteReservation(id : number):Observable<any>{
    return this.http.delete<any>(`${this.deleteReservation}/${id}`);
  }



  /*--------------Get by Id--------------*/
GetReservationByID(id : number):Observable<any>{
  return this.http.get<any>(`${this.getReservationByID}/${id}`,this.httpOptions);
  }



    /*--------------Update Reservation--------------*/

    UpdateReservation(reservation : Reservation,id : number):Observable<Object>{
      reservation.id_reservation = id;
      return this.http.put<any>(this.updateReservation,reservation);
      }



  /*--------------Calculate Total Revenue--------------*/
  calculateTotalRevenue(): Observable<number> {
    return this.http.get<number>(this.calculateRevenue, this.httpOptions);
  }


  PDF(): Observable<Blob> {
    console.log('PDF function called');
    return this.http.get(this.pdf, {
      responseType: 'blob' // Spécifie le type de réponse attendue
    });
  }

  constructor(private http : HttpClient) { }
}
