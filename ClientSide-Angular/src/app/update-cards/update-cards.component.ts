import { Component, OnInit } from '@angular/core';
import { Cards } from '../Cards.model';
import { CardService } from '../card.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-cards',
  templateUrl: './update-cards.component.html',
  styleUrls: ['./update-cards.component.css']
})
export class UpdateCardsComponent implements OnInit {
  cards : Cards = new Cards();
  value : any;
  
  constructor(private cardS : CardService,private route: ActivatedRoute,private router: Router){}
  updateCards(){
    this.cardS.UpdateCards(this.cards,this.value).subscribe();
    this.router.navigate(['/ListCards']);
      }
      ngOnInit(){

        this.route.params.subscribe(
          param => {
            this.value = param['id']
            });
        this.cardS.GetCardsByID(this.value).subscribe(data =>{
          this.cards = data
        });
      }
}
