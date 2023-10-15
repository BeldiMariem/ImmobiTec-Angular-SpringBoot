import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from 'src/app/model/feedback/feedback';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private getAllFeedbacksUrl = 'http://localhost:9000/feedBack/feedbacks';
  private getFeedbackByIdUrl = 'http://localhost:9000/feedBack/feedback/';
  private getFeedbacksByDormRoomUrl = 'http://localhost:9000/feedBack/feedbacks/dormRoom/';
  private getFeedbacksByAppUserUrl = 'http://localhost:9000/feedBack/feedbacks/appUser/';
  private addFeedbackUrl = 'http://localhost:9000/feedBack/feedback/';
  private updateFeedbackUrl = 'http://localhost:9000/feedBack/updatefeedback';
  private deleteFeedbackUrl = 'http://localhost:9000/feedBack/feedback/';
  private addBoostUrl = 'http://localhost:9000/feedBack/feedback/boost/';

  constructor(private http: HttpClient) { }

  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.getAllFeedbacksUrl);
  }

  getFeedbackById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(this.getFeedbackByIdUrl + id);
  }

  getFeedbacksByDormRoom(dormRoomId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.getFeedbacksByDormRoomUrl + dormRoomId);
  }

  getFeedbacksByAppUser(appUserId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.getFeedbacksByAppUserUrl + appUserId);
  }

  addFeedback(feedback: Feedback, userId: number, roomId: number): Observable<any> {
    return this.http.post<any>(this.addFeedbackUrl + userId + '/' + roomId, feedback);
    
  }

  updateFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.put<Feedback>(this.updateFeedbackUrl, feedback);
  }

  deleteFeedback(id: number): Observable<any> {
    return this.http.delete<any>(this.deleteFeedbackUrl + id);
  }

  addBoost(id: number): Observable<Feedback> {
    return this.http.put<Feedback>(this.addBoostUrl + id, {});
  }

}
