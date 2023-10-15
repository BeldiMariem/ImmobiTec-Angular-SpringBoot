import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Forum} from "../models/forum.model";
import {Post} from "../models/post.model";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = 'http://localhost:9000/post'
  constructor(private http: HttpClient) { }

  createPost(post: any,id:number): Observable<Object> {
    //post.user=1;
    return this.http.post(`${this.baseUrl}/addPost/${id}`, post);
  }

  getPostById(id: number): Observable<Post> {
    //console.log(this.baseUrl);
    return this.http.get<Post>(`${this.baseUrl}/getPostById/${id}`);
  }

  updatePost(id: any ,post:Post): Observable<Object>{
   // post.id_post=id;
    return this.http.put(`${this.baseUrl}/updatePost/${id}`, post);
  }

  deletePost(id: any): Observable<Object> {
    //console.log(this.baseUrl);
    return this.http.delete(`${this.baseUrl}/deletePost/${id}`);
  }

  boostPost(id:number): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/boostPost/${id}`);
  }

  getCommentsByPost(id:number): Observable<any>{
    return this.http.get<Comment>(`${this.baseUrl}/getAllCommentsByPost/${id}`);
  }
}
