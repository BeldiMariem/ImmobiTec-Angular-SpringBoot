import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Auction } from '../model/Auction.model';
import { NewAuction } from '../model/NewAuction.model';
import { Mise } from '../model/Mise.model';

@Injectable({
  providedIn: 'root'
})
export class AuctionsService {
  private ListAuctions='http://localhost:9000/Auction/current';
private AdminAuctions = 'http://localhost:9000/Auction/getAllAuction';
private AddAuction="http://localhost:9000/Auction";
private AuctionById = 'http://localhost:9000/Auction/getAuctionByid';
private ListProp ="http://localhost:9000/getAllProperties";
private Deleteauction ="http://localhost:9000/Auction/deleteAuction";
private pdf ="http://localhost:9000/Auction/auctions/pdf";
private excel="http://localhost:9000/Auction/winners/export?fileName=winners.xlsx"
private mise="http://localhost:9000/Auction";
private percentage ="http://localhost:9000/Auction/userPercentage"

httpOptions = {
  headers: new HttpHeaders({
  'Content-Type': 'application/json'
  })
  }
  constructor(private http: HttpClient) { }

  GetAllAuctions():Observable<any[]>{
    return this.http.get<any[]>(this.ListAuctions,this.httpOptions);
  }
  
  GetAllADAuctions():Observable<any[]>{
    return this.http.get<any[]>(this.AdminAuctions,this.httpOptions);
  }


  AddAuctions(auction : NewAuction,id :number ):Observable<any[]>{
    return this.http.post<any[]>(`${this.AddAuction}/${parseInt(localStorage.getItem('id')!)}/${id}/auction`,auction);
  
}

AddMise(id:number,mise : Mise ):Observable<any[]>{
  
    return this.http.post<any[]>(`${this.mise}/${id}/${parseInt(localStorage.getItem('id')!)}/mise`,mise);
  
}


DeleteAuction(id:number):Observable<any>{
  return this.http.delete<any>(`${this.Deleteauction}/${id}`);
}

PDF(): Observable<Blob> {
  console.log('PDF function called');
  return this.http.get(this.pdf, {
    responseType: 'blob' // Spécifie le type de réponse attendue
  });
}


Execl():Observable<any[]>{
  return this.http.get<any[]>(this.excel,this.httpOptions);
}

  GetAuctionById(id : number):Observable<any>{
    return this.http.get<any>(`${this.AuctionById}/${id}`,this.httpOptions);

  }

  GetProprety():Observable<any[]>{
    return this.http.get<any[]>(this.ListProp,this.httpOptions);
  }

  getPercentage():Observable<any> {
   return this.http.get<any[]>(this.percentage);
   
  }

}
