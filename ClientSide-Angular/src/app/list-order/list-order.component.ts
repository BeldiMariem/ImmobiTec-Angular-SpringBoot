import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrls: ['./list-order.component.css']
})
export class ListOrderComponent implements OnInit {
  orders : any;
  constructor(private OrderService :OrderService ,private router: Router) {
  }
  GetAllOrders(){
    this.OrderService.GetAllOrders().subscribe(data =>{
      this.orders = data
    });
  }
  deleteOrders(id : number){
    this.OrderService.DeleteOrders(id).subscribe();
    this.GetAllOrders;
window.location.href="http://localhost:4200/ListOrders"
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  ngOnInit(){
    this.GetAllOrders();

}

}
