import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FeedbackService } from 'src/app/services/feedbackservice/feedback.service';
import { Feedback } from 'src/app/model/feedback/feedback';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  constructor(
    private feedbackService: FeedbackService,
    private route: ActivatedRoute
  ) {}
  mb:any;
  feedbacks?: Feedback[];
  feedback?: Feedback;

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const roomID = +params['roomID'];
      this.feedbackService.getFeedbacksByDormRoom(roomID).subscribe((feedbacks: Feedback[]) => {
        this.feedbacks = feedbacks;
        console.log(`Comments for article ${roomID} retrieved:`, this.feedbacks);

        // Sort comments array in descending order by number of boosts
        this.feedbacks.sort((a, b) => b.boosts - a.boosts);

        this.setMostBoostedFeedback(); // Call setMostBoostedFeedback() inside the callback function
      });
    });
  }


  

  addBoost(feedbackId: number): void {
    this.feedbackService.addBoost(feedbackId).subscribe(
      (feedback: Feedback) => {
        console.log(`Boost added for comment ${feedbackId}`);
      },
      (error: any) => {
        console.log(`Error adding boost for comment ${feedbackId}: ${error}`);
      }
    );
  }

setMostBoostedFeedback(): void {
  console.log('All feedbacks:', this.feedbacks);

  if (this.feedbacks && this.feedbacks.length > 0) {
    // Find the most boosts
    let mostBoosts = 0;
    for (let i = 0; i < this.feedbacks.length; i++) {
      if (this.feedbacks[i].boosts > mostBoosts) {
        mostBoosts = this.feedbacks[i].boosts;
      }
      this.mb=mostBoosts;
    }

    // Find all feedbacks with the most boosts
    const mostBoostedFeedbacks: Feedback[] = [];
    for (let i = 0; i < this.feedbacks.length; i++) {
      if (this.feedbacks[i].boosts === mostBoosts) {
        mostBoostedFeedbacks.push(this.feedbacks[i]);
      }
    }

    // Mark all feedbacks with the most boosts as boosted
    for (let i = 0; i < mostBoostedFeedbacks.length; i++) {
      mostBoostedFeedbacks[i].boosted = true;
      console.log('Most boosted feedback:', mostBoostedFeedbacks[i]);
    }

    // If there are no feedbacks with any boosts, log that no feedbacks were found
    if (mostBoostedFeedbacks.length === 0) {
      console.log('No feedbacks found');
    }
  }
}

  
  }
