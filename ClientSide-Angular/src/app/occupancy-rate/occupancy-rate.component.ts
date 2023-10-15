import { Component, OnInit } from '@angular/core';
import { DormServiceService } from '../services/dorm-service.service';

@Component({
  selector: 'app-occupancy-rate',
  templateUrl: './occupancy-rate.component.html',
  styleUrls: ['./occupancy-rate.component.css']
})
export class OccupancyRateComponent implements OnInit {

  occupancyRate: number;

  constructor(private dormService: DormServiceService) { 
    this.occupancyRate = 0; // initialize to a default value
  }

  ngOnInit(): void {
    this.dormService.GetOccupancyRate().subscribe((data) => {
      this.occupancyRate = data.occupancyRate;
    });
  }
}


