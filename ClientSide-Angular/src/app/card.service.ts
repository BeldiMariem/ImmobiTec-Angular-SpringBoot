import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cards } from './Cards.model';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private getAllCards = 'http://localhost:9000/getallCards';
  private deleteCards = 'http://localhost:9000/deletCard';
  private addCards = 'http://localhost:9000/addCard';
  private getCardsById = 'http://localhost:9000/GetCardById';
  private updateCard = 'http://localhost:9000/updateCard';




  constructor(private http : HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
    }
    GetAllCards():Observable<any[]>{
      return this.http.get<any[]>(this.getAllCards,this.httpOptions);
    }
    DeleteCards(id : number):Observable<any>{
      return this.http.delete<any>(`${this.deleteCards}/${id}`);
    }
    AddCards(cards : Cards):Observable<Object>{
      return this.http.post(`${this.addCards}`,cards);
    }
    GetCardsByID(id : number):Observable<any>{
      return this.http.get<any>(`${this.getCardsById}/${id}`,this.httpOptions);
    }
    UpdateCards(cards : Cards , id : number):Observable<Object>{
      cards.id_card = id;
       return this.http.put(this.updateCard,cards);
     }


}
