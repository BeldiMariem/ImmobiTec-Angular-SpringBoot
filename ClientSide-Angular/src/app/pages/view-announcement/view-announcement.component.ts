import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Stripe } from '@stripe/stripe-js';

@Component({
  selector: 'app-view-announcement',
  templateUrl: './view-announcement.component.html',
  styleUrls: ['./view-announcement.component.css']
})
export class ViewAnnouncementComponent implements OnInit{

  announcement : any;
  announcements : any;
  id : any;
  idAnnouncement : number = 0;
  paymentHandler : any = null;

  constructor(private announcementService: AnnouncementService,private router: Router,
    private route :ActivatedRoute) {
  }

  updateAnnouncement(){
    this.announcementService.UpdateAnnouncement(this.announcement , this.idAnnouncement).subscribe();
    alert("Announcement Updated Successfully !!!");

    this.router.navigate(['/ListProperties']);
  }

   makePayment(amount: number) {
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key :'pk_test_51Ks89UCXTqtJcSxPg1H0s9Ej8DFEWtwnnSg9qHASHnck4zA4YvlNrhuW8HG9BvMeElBjQvgh95kuXRzL0C59jh8e00bgJYBPq0',
      locale :'auto',
      token : function (stripeToken: any){
        console.log(stripeToken);
        paymentStripe(stripeToken.id);
      }
    });

    const paymentStripe = (stripeToken : any)=>{
      this.announcementService.Payment(stripeToken,this.idAnnouncement).subscribe((data : any)=>{
        console.log(data);
      });
      this.router.navigate(['/ListProperties']);
    }

    paymentHandler.open({
      name:'Stripe Payment',
      description : 'Boost your announcement',
      amount : amount * 100
    })
  }

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  invokeStripe() {
    if(!window.document.getElementById('stripe-script')){
      const script = window.document.createElement('script');
      script.id = 'stripe-script'
      script.src = 'https://checkout.stripe.com/checkout.js';
      script.type = 'text/javascript';
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key :'pk_test_51Ks89UCXTqtJcSxPg1H0s9Ej8DFEWtwnnSg9qHASHnck4zA4YvlNrhuW8HG9BvMeElBjQvgh95kuXRzL0C59jh8e00bgJYBPq0',
          locale :'auto',
          token : function (stripeToken: any){
            console.log(stripeToken);
          }
        })
      };
      window.document.body.appendChild(script); // add script to the document

  }
}

  ngOnInit(){
    
    this.route.params.subscribe(
      param => {
        this.id = param['propertyID']
        });
    this.announcementService.GetAllAnnouncementsTWO().subscribe(data =>{
      for(let item of data){
        if(item.property.id_property == this.id){
          this.announcement = item;
          this.announcement.property = null;
          this.idAnnouncement = item.id_announcement;
      }
    }
    });
    this.invokeStripe();
 
}
}
