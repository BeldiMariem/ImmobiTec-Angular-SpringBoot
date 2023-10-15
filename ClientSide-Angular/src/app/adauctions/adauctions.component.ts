import { Component, OnInit } from '@angular/core';
import { AuctionsService } from '../services/auctions.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adauctions',
  templateUrl: './adauctions.component.html',
  styleUrls: ['./adauctions.component.css']
})
export class ADauctionsComponent implements OnInit{
  auctions : any;
  currentPage: number = 1;
  pageSize: number = 3;
  searchString: string = '';

  constructor(private auctionService: AuctionsService , private router:Router) {

  }

getAllAdminAuctions(){
  this.auctionService.GetAllADAuctions().subscribe(data =>{
this.auctions = data
  });
}

pdf(){
  console.log('PDF function called');
  this.auctionService.PDF().subscribe(response => {
    const blob = new Blob([response], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'auctions.pdf';
    link.click();
    URL.revokeObjectURL(url);
  }, error => {
    console.log(error);
  });
}





excel(){
  this.auctionService.Execl().subscribe();

}

deleteAuction(id: number) {
  if (confirm("Are you sure you want to delete this auction?")) {
    this.auctionService.DeleteAuction(id).subscribe(
      () => {
        this.reloadComponent();
      },
      (error) => {
        console.log(error);
        alert('Failed to delete the auction. Please try again later.');
      }
    );
  } else {
    alert("Auction deletion canceled.");
    const alertElement = document.querySelector('.alert');
    if (alertElement) {
      alertElement.classList.add('custom-alert');
    }
  }
}



reloadComponent(): void {
  const currentRoute = this.router.url.split('?')[0];
  this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([currentRoute]);
  });
}

filterAuctions(searchString: string) {
  if (!searchString) {
    this.getAllAdminAuctions();
  } else {
    this.auctions = this.auctions.filter((auction: any) =>
      auction.description.toLowerCase().includes(searchString.toLowerCase())
    );
  }
}


  ngOnInit(){
this.getAllAdminAuctions(); 
  }
}



