import { Component, OnInit } from '@angular/core';
import {ShippingService} from "../shipping.service";
import {CartService} from "../cart.service";
import {KeyValue} from "@angular/common";
import {BackendDetailsService} from "../backend-details.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cart;
  shippingDetails;
  s;
  totalSum : number;
  distance;
  discountArray = [];
  discountApplied = 0;
  afterDiscountAmount = 0;
  finalAmountWithTax = 0;
  tax = 0;
  constructor(private cartService: CartService, private shippingService: ShippingService , private backend : BackendDetailsService , private http: HttpClient) {
    this.cart = [];
    this.shippingDetails = [];
    this.discountArray = [10,20,30,40];
  }

  ngOnInit() {
    this.cartPopulate();
    this.s = this.shippingService.getShipping();
    //console.log(this.s);
    for (var key in this.s) {
      this.shippingDetails[key] = this.s[key];
      
    }
    this.getTotal();
    this.applyDiscount(this.totalSum);
    this.afterDiscount(this.totalSum, this.discountApplied);
    this.applyTax(this.totalSum);
    this.finalAmount(this.afterDiscountAmount, this.tax);
    //this.getDistance();
  }

  originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

  applyDiscount(totalSum)
  {
    var t = parseFloat(totalSum);
    console.log("this is the sum on which discount will be applied" , t);
    if(t > 0 && t <= 2000)
      this.discountApplied = t * 0.10;
    if (t > 2000 && t <= 5000)
      this.discountApplied = t * 0.20;
    if (t > 5000 && t <= 10000)
      this.discountApplied = t * 0.30;  
    if (t > 10000 && t <= 20000)
      this.discountApplied = t * 0.40;
    if ( t > 20000)
      this.discountApplied = t * 0.50;

      this.discountApplied = parseFloat(this.discountApplied.toFixed(2));
  }


  afterDiscount(totalSum, discountApplied)
  {
    this.afterDiscountAmount = +totalSum - +discountApplied;
    this.afterDiscountAmount = parseFloat(this.afterDiscountAmount.toFixed(2));
  }



  applyTax(totalSum)
  {
    this.tax = +totalSum * 0.13;
    this.tax = parseFloat(this.tax.toFixed(2));
  }

  finalAmount(afterDiscountAmount , tax)
  {
    this.finalAmountWithTax = +afterDiscountAmount + +tax;
    this.finalAmountWithTax = parseFloat(this.finalAmountWithTax.toFixed(2));
  }
  cartPopulate() {
    this.cart = this.cartService.getCart();
  }

  getTotal()
  {
    
    this.totalSum =  parseFloat((this.cartService.getTotal()).toFixed(2));
  }
  

  getDistance()
  {
    console.log(this.shippingDetails);
    let start = this.shippingDetails["streetAddress1"] + " " + this.shippingDetails["city"] + " " + this.shippingDetails["province"];
    //   {responseType : Text }
    let end = "York University, ON";
    let url = this.backend.getURL();
    url+= "Trip?start=" + start + "&end=" + end;
    this.http.get(url , {responseType : 'text'}).subscribe(data =>
      {
        this.distance = data;
        //console.log( typeof data);
      });
  }


//    round(value : string , decimals : string) {
     
//   return Number(Math.round(value + 'e' + decimals) + 'e-' + decimals);
// }
}
