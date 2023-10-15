import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostVoteService {

  private baseUrl = 'http://localhost:9000/postVote';

  constructor(private http: HttpClient) { }

  like(id:any): Observable<any> {
    const url = `${this.baseUrl}/like/${id}`;
    //console.log(url);
    return this.http.get<any>(url);
  }


  dislike(id:any): Observable<any> {
    const url = `${this.baseUrl}/dislike/${id}`;
    //console.log(url);
    return this.http.get<any>(url);
  }
}
