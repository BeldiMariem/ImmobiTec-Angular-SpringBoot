import { Component, OnInit } from '@angular/core';
import { Product } from '../Product.model';
import { ProductServiceService } from '../product-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  product : Product = new Product();
  value : any;

  //update product function

  updateProduct(){
    this.productS.UpdateProducts(this.product,this.value).subscribe();
    window.location.href="/ListProduct"
      }
      constructor(private productS : ProductServiceService,private route: ActivatedRoute,private router: Router){}

      ngOnInit(){

        this.route.params.subscribe(
          param => {
            this.value = param['id']
            });
        this.productS.GetProductsByID(this.value).subscribe(data =>{
          this.product = data
        });
      }
}
