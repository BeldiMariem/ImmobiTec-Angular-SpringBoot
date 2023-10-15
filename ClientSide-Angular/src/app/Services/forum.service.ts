import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Forum} from "../models/forum.model";
import {Observable} from "rxjs";
import {Post} from "../models/post.model";



@Injectable({
  providedIn: 'root'
})
export class ForumService {

  private baseUrl = 'http://localhost:9000/forum'

  constructor(private http: HttpClient) {
  }

  getAllForums(): Observable<Forum[]> {
    console.log(this.baseUrl);
    return this.http.get<Forum[]>(`${this.baseUrl}/AllForums`);
  }

  createForum(forum: Forum): Observable<Object> {
    return this.http.post(`${this.baseUrl}/AddForum`, forum);
  }


  getForumById(id: any): Observable<Forum> {
    //console.log(this.baseUrl);
    return this.http.get<Forum>(`${this.baseUrl}/ForumById/${id}`);
  }

  updateForum(id:number,forum: Forum): Observable<Object> {
    forum.id_forum=id;
    return this.http.put(`${this.baseUrl}/updateForum`, forum);
  }

  deleteForum(id: number): Observable<Forum> {
    //console.log(this.baseUrl);
    return this.http.delete(`${this.baseUrl}/deleteForum/${id}`);
  }

  getAllPosts(id: number): Observable<Post[]> {
    console.log(this.baseUrl);
    return this.http.get<Post[]>(`${this.baseUrl}/getAllPostsByForum/${id}`);
  }

  getBoostedPosts(id: number): Observable<Post[]> {
    console.log(this.baseUrl);
    return this.http.get<Post[]>(`${this.baseUrl}/boostedPosts/${id}`);
  }

}
