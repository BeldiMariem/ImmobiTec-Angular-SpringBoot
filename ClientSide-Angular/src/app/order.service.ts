import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './Orders.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private getAllOrders = 'http://localhost:9000/order/getallOrdres';
  private deleteOrders = 'http://localhost:9000/order/deletOrdre';
  private addOrders = 'http://localhost:9000/order/addOrdre';
  private getOrdersById = 'http://localhost:9000/order/GetOrdreById';
  private updateOrders = 'http://localhost:9000/order/updateOrdre';





  constructor(private http : HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
    }
    GetAllOrders():Observable<any[]>{
      return this.http.get<any[]>(this.getAllOrders,this.httpOptions);
    }
    DeleteOrders(id : number):Observable<any>{
      return this.http.delete<any>(`${this.deleteOrders}/${id}`);
    }
    AddOrders(order : Order ):Observable<Object>{
      return this.http.post(`${this.addOrders}`,order);
    }
    GetOrdersById(id : number):Observable<any>{
      return this.http.get<any>(`${this.getOrdersById}/${id}`,this.httpOptions);
    }
       UpdateOrders(order : Order , id : number):Observable<Object>{
      order.id_order = id;
       return this.http.put(this.updateOrders,order);
     }


}
