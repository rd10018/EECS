import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CartService} from "../cart.service";
import { KeyValue } from "@angular/common";
import {BackendDetailsService} from "../backend-details.service";

@Component({
  selector: 'app-stock-sold',
  templateUrl: './stock-sold.component.html',
  styleUrls: ['./stock-sold.component.css']
})
export class StockSoldComponent implements OnInit {

  sessionID = "";
  sessionCartDb = [];
  printDetails : boolean;
  constructor(private http : HttpClient , private backend : BackendDetailsService) { 
    this.printDetails = false;
  }
  
  ngOnInit() {
    this.printSessionCart();
  }

  originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

  printSessionCart() {
 
    let url = this.backend.getURL() + "sessionCartPrint?sessionId=" + this.backend.sessionID;
    this.http.get(url, { responseType: 'text' }).subscribe(data => {
    console.log("this is the data that came from the backend " , data);
    let s = JSON.parse(data);
    console.log("this is the backend data " , s);

    this.sessionCartDb = s;
    console.log("this is the value of sessionCartDb" , this.sessionCartDb);
    //console.log("this is the cart of the sessionCartDb", this.sessionCartDb[0]["cart"]);
    this.printDetails = true;  
    });
  }


  isPrintDetails() : boolean
  {
    return this.printDetails;
  }

  getCart(cart)
  {
    console.log("this is the cart " + cart);
  }
}
