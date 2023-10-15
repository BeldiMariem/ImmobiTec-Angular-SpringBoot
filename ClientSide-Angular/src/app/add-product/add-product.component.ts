import { Component, OnInit } from '@angular/core';
import { Product } from '../Product.model';
import { ProductServiceService } from '../product-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  product : Product = new Product();

  saveProduct(){
    this.productS.AddProducts(this.product).subscribe();
    window.location.href="/ListProduct"
  }
  constructor(private productS : ProductServiceService,private router: Router){}
  ngOnInit(){

  }


}
