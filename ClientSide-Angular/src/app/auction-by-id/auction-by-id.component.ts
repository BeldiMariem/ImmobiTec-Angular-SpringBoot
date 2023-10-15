import { Component, OnInit } from '@angular/core';
import { AuctionsService } from '../services/auctions.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Mise } from '../model/Mise.model';

@Component({
  selector: 'app-auction-by-id',
  templateUrl: './auction-by-id.component.html',
  styleUrls: ['./auction-by-id.component.css']
})
export class AuctionByIdComponent implements OnInit {

  value : any;
  auction : any ;
  mises :number [] = [];
  mise : Mise = new Mise();
  max : any;

miseForm! : FormGroup;
  constructor(private auctionService: AuctionsService , private route: ActivatedRoute, private fb:FormBuilder) {

    this.miseForm = this.fb.group({
      montant:[null]
    })
  }



  ngOnInit(){

    this.route.params.subscribe(
      param => {
        this.value = param['idAuction']
        });

        this.auctionService.GetAuctionById(this.value).subscribe(data => {
          this.auction = data
          this.max = this.auction.mises[0].montant;
          for(let item of this.auction.mises){
            if(this.max < item.montant){
                this.max = item.montant;
            }
      }
        });
       
      

  }



  AddMise(id : number){
    this.mise.montant = document.querySelector<HTMLInputElement>("#nom")!.value;
    console.log(this.mise);
    this.auctionService.AddMise(id,this.mise).subscribe({
      next:(Response)=> {console.log(this.mise)},
      error:(error)=> {console.log(error)}})
        }

}
