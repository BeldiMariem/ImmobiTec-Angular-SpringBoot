import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products : any;

  constructor(private ProductService: ProductServiceService,private router: Router) {
  }
  GetAllProducts(){
    this.ProductService.GetAllProducts().subscribe(data =>{
      this.products = data
    });
  }
  DeleteProducts(id : number){
    this.ProductService.DeleteProducts(id).subscribe();
    this.GetAllProducts
    window.location.href="/ListProduct"
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }
  ngOnInit(){
    this.GetAllProducts();
      }

}
