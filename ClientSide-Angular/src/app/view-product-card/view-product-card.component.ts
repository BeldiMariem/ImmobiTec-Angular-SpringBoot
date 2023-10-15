import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-view-product-card',
  templateUrl: './view-product-card.component.html',
  styleUrls: ['./view-product-card.component.css']
})
export class ViewProductCardComponent implements OnInit {
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
   this.reloadComponent();
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
