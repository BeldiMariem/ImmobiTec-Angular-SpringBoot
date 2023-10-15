import { Component, OnInit } from '@angular/core';
import { AuctionsService } from '../services/auctions.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AbstractControl , ValidationErrors } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent  implements OnInit{
  propretys : any ;
  auctionForm! : FormGroup ;
  id : any;
  constructor(private auctionService: AuctionsService ,private fb:FormBuilder,private route: ActivatedRoute) {

    this.auctionForm = this.fb.group({
      description: [null, [Validators.required]],
      entryFee: [null, [Validators.required]],
      startDate: [null, [Validators.required, this.validateDate.bind(this, 'startDate')]], // Ajouter le validateur ici
  endDate: [null, [Validators.required, this.validateDate.bind(this, 'endDate')]],
   proprety: this.propretys,
    }); }
    
    validateDate(dateField: string, control: AbstractControl): ValidationErrors | null {
      const currentDate = new Date();
      const selectedDate = new Date(control.value);
      
      // Valider si la date sélectionnée est antérieure à la date actuelle, à l'exception de l'heure
      if (selectedDate.setHours(0, 0, 0, 0) < currentDate.setHours(0, 0, 0, 0)) {
        return { [`${dateField}PastDate`]: true };
      }
      
      const startDate = new Date(this.auctionForm.controls['startDate'].value);
      const endDate = new Date(this.auctionForm.controls['endDate'].value);
      
      if (dateField === 'endDate' && selectedDate <= startDate) {
        return { [`${dateField}BeforeStartDate`]: true };
      }
      
      return null;
    }
    

getAllProretys(){
  this.auctionService.GetProprety().subscribe(data =>{
this.propretys = data
  });
}


   
   
    AddAuction() {
      if (this.auctionForm.valid) {
        this.auctionService.AddAuctions(this.auctionForm.value,this.id).subscribe({
          next: (response) => {
            console.log(response);
            window.alert('Auction added successfully!');
          },
          error: (error) => {
            console.log(error);
            window.alert('Failed to add auction.');
          }
        });
      } else {
        window.alert('Please fill in all required fields and make sure the dates are valid.');
      }
    }
    


    ngOnInit(){
  
      this.getAllProretys(); 
      this.route.params.subscribe(
        param => {
          this.id = param['idprop']
          });
        }
  }
