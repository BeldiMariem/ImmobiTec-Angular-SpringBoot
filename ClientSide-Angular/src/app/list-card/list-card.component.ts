import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardService } from '../card.service';

@Component({
  selector: 'app-list-card',
  templateUrl: './list-card.component.html',
  styleUrls: ['./list-card.component.css']
})
export class ListCardComponent implements OnInit {
  cards : any;

  constructor(private CardService :CardService ,private router: Router) {
  }
  GetAllCards(){
    this.CardService.GetAllCards().subscribe(data =>{
      this.cards = data
    });
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }
  deleteCards(id : number){
    this.CardService.DeleteCards(id).subscribe();
    this.GetAllCards;
   this.reloadComponent();
  }
  ngOnInit(){
    this.GetAllCards();

}

}
