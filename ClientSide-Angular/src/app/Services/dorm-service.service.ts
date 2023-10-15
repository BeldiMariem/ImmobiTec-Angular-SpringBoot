import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dorm } from '../model/Dorm.model';

@Injectable({
  providedIn: 'root'
})
export class DormServiceService {

  private listDormRoom = 'http://localhost:9000/dorm/getAllRooms';
  private  deleteDormRoom ='http://localhost:9000/dorm/deleteDormRoom';
  private addDormRoom = 'http://localhost:9000/dorm/addDormRoom';
  private getDormRoomByID = 'http://localhost:9000/dorm/getRoomById';
  private updateDormRoom ='http://localhost:9000/dorm/updateDormRoom';
  private addImage = 'http://localhost:9000/dorm/addImage';
  private bestRooms='http://localhost:9000/dorm/bestRooms';
  private occupancyRate='http://localhost:9000/dorm/occupancy-rate';
  private Likes ='http://localhost:9000/dorm/likeDormRoom';
  private Dislikes ='http://localhost:9000/dorm/dislikeDormRoom';


httpOptions = {
  headers: new HttpHeaders({
    'Content-Type' : 'application/json'
  })
}

/*--------------Occupency rate ---------------*/



GetOccupancyRate(): Observable<any> {
  return this.http.get<any>(this.occupancyRate, this.httpOptions);
}


/*--------------Get best rooms ---------------*/

BestRooms(): Observable<any> {
  return this.http.get(this.bestRooms, this.httpOptions);
}

/*--------------Add Dorm Room ---------------*/

AddDormRoom(dorm : Dorm):Observable<any[]>{
  return this.http.post<any[]>(this.addDormRoom,dorm);

}

/*--------------Upload Image ---------------*/


uploadImage(id : number , img : any):Observable<any>{
  return this.http.put<any>(`${this.addImage}/${id}`,img);
}

/*--------------List Dorm Rooms --------------*/
getAllDormRooms():Observable<any[]>{
return this.http.get<any[]>(this.listDormRoom,this.httpOptions);
}


/*--------------Get by Id--------------*/
getDormRoombyID(id : number):Observable<any>{
  return this.http.get<any>(`${this.getDormRoomByID}/${id}`,this.httpOptions);
  }

  /*--------------Update Dorm Room--------------*/

  updateDormroom(dorm : Dorm,id : number):Observable<Object>{
    dorm.id_Room = id;
    return this.http.put<any>(this.updateDormRoom,dorm);
    }

  /*--------------Delete Dorm Room--------------*/
  DeleteDormRoom(id : number):Observable<any>{
    return this.http.delete<any>(`${this.deleteDormRoom}/${id}`);
  }


  LikeDorm(idroom: number, userId: number): Observable<Dorm> {
    const url = `${this.Likes}/${idroom}/${userId}`;
    return this.http.post<Dorm>(url,this.httpOptions);
  }

  DislikeDorm(idroom: number, userId: number): Observable<Dorm> {
    const url = `${this.Dislikes}/${idroom}/${userId}`;
    return this.http.post<Dorm>(url,this.httpOptions);
  }




  constructor(private http: HttpClient) { }
}
