import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  userCart = [];
  productRepeat : boolean;
  totalSum : number ;
  constructor() {
    this.productRepeat = false;
    this.totalSum = 0;
    
   }


   addToCart(product)
   {

    if(this.userCart.length > 0)
    {
      for (let i = 0; i < this.userCart.length; i++) {
        if (this.userCart[i]["id"] === product["id"]) {
          this.productRepeat = true;
          this.userCart[i]["qty"] = +this.userCart[i]["qty"] + 1;
          this.userCart[i]["extended_price"] = +this.userCart[i]["extended_price"] + +this.userCart[i]["price"];
        }
        else {
          this.productRepeat = false;
        }
      }

    }
    

    if(this.productRepeat == false) 
    {
      this.userCart.push(product);
    }
   // this.productRepeat =  false;
   }

   getCart()
   {
    // console.log("these are the total entries of the cart at present" , this.userCart);
     return this.userCart;
   }

   updateQuantity(product, quantity)
   {
     for( let i =0;i< this.userCart.length ; i++)
     {
       if(this.userCart[i]["id"] === product["id"])
       {  
         if(quantity != 0)
         {
           this.userCart[i]["qty"] = +quantity;
           this.userCart[i]["extended_price"] = +quantity * +this.userCart[i]["price"];
           console.log("this is the updated cart ", JSON.stringify(this.userCart));
         }
         else
         {
           this.userCart.splice(i,1);
         }
       }
     }
   }

   getTotal()
   {
     for(var i=0;i< this.userCart.length; i++)
     {
      this.totalSum = +this.totalSum + +this.userCart[i]["extended_price"];
     }
     return this.totalSum;
   }
}
