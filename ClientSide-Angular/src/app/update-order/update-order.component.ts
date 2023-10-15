import { Component, OnInit } from '@angular/core';
import { Order } from '../Orders.model';
import { OrderService } from '../order.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {
  orders : Order = new Order();
  value : any;
  updateOrder(){
    this.orderS.UpdateOrders(this.orders,this.value).subscribe();
    this.router.navigate(['/ListOrders']);
      }

      constructor(private orderS : OrderService,private route: ActivatedRoute,private router: Router){}

      ngOnInit(){

        this.route.params.subscribe(
          param => {
            this.value = param['id']
            });
        this.orderS.GetOrdersById(this.value).subscribe(data =>{
          this.orders = data
        });
      }


}
