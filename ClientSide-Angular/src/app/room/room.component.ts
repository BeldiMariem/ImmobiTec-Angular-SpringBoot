import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DormServiceService } from '../services/dorm-service.service';
import { Dorm } from '../model/Dorm.model';
import { FeedbackService } from '../services/feedbackservice/feedback.service';
import { Feedback } from '../model/feedback/feedback';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  dorm!: Dorm; // use the non-null assertion operator to indicate that dorm will have a value
feedback:Feedback = new Feedback();


  constructor(
    private route: ActivatedRoute,
    private dormService: DormServiceService,
    private feedbackService: FeedbackService

  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('roomID');
    if (id !== null) {
      const roomID = +id;
      this.dormService.getDormRoombyID(roomID).subscribe((dorm) => {
        this.dorm=dorm;
        console.log('Room data:', dorm);
      
      });
    }
  }

  AddFeedback(){
    var id = Number(localStorage.getItem('id'));

    console.log('feedback:', this.feedback);
    console.log('dorm id:', this.dorm.id_Room);
    this.feedbackService.addFeedback(this.feedback,id,this.dorm.id_Room).subscribe(
      (data) => {
        // clear comment form and show success message
        this.feedback = new Feedback();
      },
      (error) => {
        console.error(error);
      }
    );
  }
  

}
