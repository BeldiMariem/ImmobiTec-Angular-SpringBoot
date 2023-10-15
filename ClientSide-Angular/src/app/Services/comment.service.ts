import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentP } from '../models/commentP.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private baseUrl = 'http://localhost:9000/comment'

  constructor(private http: HttpClient) { }

  createComment(comment: CommentP,id:number): Observable<Object> {
   
    return this.http.post(`${this.baseUrl}/addCommentToPost/${id}`, comment);
  }


  updateComment(comment:CommentP): Observable<Object>{
   // post.id_post=id;
    return this.http.put(`${this.baseUrl}/updateComment`, comment);
  }

  deleteComment(id: number): Observable<any> {
    //console.log(this.baseUrl);
    return this.http.delete(`${this.baseUrl}/deleteComment/${id}`);
  }
  


}
