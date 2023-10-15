import { Component, OnInit } from '@angular/core';
import { Cards } from '../Cards.model';
import { CardService } from '../card.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-card',
  templateUrl: './add-card.component.html',
  styleUrls: ['./add-card.component.css']
})
export class AddCardComponent implements OnInit{
  cards : Cards = new Cards();
  saveCards(){
    this.cardS.AddCards(this.cards).subscribe();
    this.router.navigate(['/ListCards']);
  }
  constructor(private cardS : CardService,private router: Router){}
  ngOnInit(){

  }

}
