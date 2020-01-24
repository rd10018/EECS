import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BackendDetailsService} from "../backend-details.service";
import {HttpClient} from "@angular/common/http";
import {CartService} from "../cart.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  productName;
  productSpec;
  constructor(private route : ActivatedRoute ,private cart : CartService, private http : HttpClient , private backend : BackendDetailsService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params =>
      {
        this.productName = params.get('productName');
      });
     this.productPopulate(); 
  }

  productPopulate()
  {
    let url = this.backend.getURL();
    url += "Product?cors&name=" + this.productName;
    this.http.get(url).subscribe(data =>
      { 
        this.productSpec = data;
        //console.log("this is the datatype of data " + typeof data);
        console.log("these are the product details obtained from the backend " + JSON.stringify(this.productSpec));
      });
  }

  addToCart(product)
  {
    let p = product;
    p["qty"] = 1;
    p["price"] = p["msrp"];
    p["extended_price"] = p["msrp"];
    delete p["cost"];
    delete p["msrp"];
    this.cart.addToCart(product);
  }

}
