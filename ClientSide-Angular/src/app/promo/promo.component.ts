import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductServiceService } from '../product-service.service';
import { Product } from '../Product.model';
import { HttpBackend, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-promo',
  templateUrl: './promo.component.html',
  styleUrls: ['./promo.component.css']
})
export class PromoComponent implements OnInit {
  product : Product = new Product();
  value : any;
  duration:any
  newValue:any
  unit:any


  //update product function
url="http://localhost:9000/productSale";

  updateProduct(){
    this.productS.UpdateProducts(this.product,this.value).subscribe();
    this.router.navigate(['/ListProduct']);
      }
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
        this.http.put(`${this.url}/${this.value}/${this.newValue}/${this.duration}/${this.unit}`,{}).subscribe(data=>{
          this.router.navigate(['/ListProduct']);
        })
      }

}
