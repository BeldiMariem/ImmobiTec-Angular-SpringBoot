import { Component, OnInit } from '@angular/core';
import { Order } from '../Orders.model';
import { OrderService } from '../order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Product } from '../Product.model';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit{
  product : Product = new Product();
  value : any;
  orderUrl="http://localhost:9000/product/addORdre/";
  order:Order=new Order();

  //update product function

      constructor(private productS : ProductServiceService,private http:HttpClient ,private route: ActivatedRoute,private router: Router){}

      ngOnInit(){

        this.route.params.subscribe(
          param => {
            this.value = param['idproduct']
            });
        this.productS.GetProductsByID(this.value).subscribe(data =>{
          this.product = data
        });
      }
      confirm(){
        this.order.total_price=this.product.price;
let o =this.order;
        this.http.post(
          `${this.orderUrl}${this.product.id_product}/${Number(localStorage.getItem('id'))}`
          ,o).subscribe(
            data=>{ window.location.href="/cardProduct"}
          );
      }

}
