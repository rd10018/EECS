import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

  shippingDetails ;
  constructor() { 

  }


  addShipping(shipping)
  {
    this.shippingDetails = shipping;
  }

  getShipping()
  {
    return this.shippingDetails;
  }


  isShipping() : boolean{
    if(this.shippingDetails)
    return true;
    else
    return false;
  }



}
