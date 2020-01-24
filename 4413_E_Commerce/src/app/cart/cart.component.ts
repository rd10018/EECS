import { Component, OnInit } from '@angular/core';
import {CartService} from "../cart.service";
import {ShippingService} from "../shipping.service";
import {KeyValue} from "@angular/common";
import {HttpClient} from "@angular/common/http";
import {BackendDetailsService} from "../backend-details.service";
import { stringify } from 'querystring';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart;
  shippingDetails;
  s;
  sessionID = "";
  constructor(private backend : BackendDetailsService , private http : HttpClient , private cartService : CartService , private shippingService : ShippingService) {
    this.cart = [];
    this.shippingDetails = [];
   }

  ngOnInit() {
    this.cartPopulate();
  }

  originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

  cartPopulate()
  {
    //console.log("these are the shipping details ", this.shippingDetails);
      this.cart = this.cartService.getCart();
  }


  updateQuantity(product , quantity)
  {
    this.cartService.updateQuantity(product,quantity);

  }

  isShipping() : boolean{
    if(this.shippingService.isShipping())
    {
      this.s = this.shippingService.getShipping();
      for(var key in this.s)
      {
        this.shippingDetails[key] = this.s[key];
      }
      return true;
    }
    else
    {
      return false;
    }
    
  }


  backendUpdateStock()
  {
    let url1 = this.backend.getURL();
    url1 += "sessionCart?cart=";
    let c = this.cartService.getCart();
    url1 += JSON.stringify(c);
    url1 += "&shipping=";
    let s = this.shippingService.getShipping();
    url1 += JSON.stringify(s);
    url1 += "&sessionId=" + this.backend.sessionID;
    //console.log("---------------");
    this.http.get(url1 , {responseType :'text'}).subscribe(data =>
      {
        console.log("this s the sesison ID to be used for next call " , data);
      this.backend.sessionID = data;
      });  
  }
}
