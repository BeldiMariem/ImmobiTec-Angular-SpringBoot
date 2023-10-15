import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../Product.model';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {
  product : Product = new Product();
  value : any;
 promoCode:any


  //update product function
url="http://localhost:9000/productPromoCodeDiscount";


      constructor(private http:HttpClient, private productS : ProductServiceService,private route: ActivatedRoute,private router: Router){}

      ngOnInit(){

        this.route.params.subscribe(
          param => {
            this.value = param['id']
            });

        this.productS.GetProductsByID(this.value).subscribe(data =>{
          this.product = data
        });
      }
      promo(){
        this.http.put(`${this.url}/${this.value}/${this.promoCode}`,{}).subscribe(data=>{
          this.router.navigate(['/cardProduct']);
        })
      }
}
