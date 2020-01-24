import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Router} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { CatalogComponent } from './catalog/catalog.component';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { ShippingComponent } from './shipping/shipping.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { StockSoldComponent } from './stock-sold/stock-sold.component';
import { ValidationComponent } from './validation/validation.component';
import { SessionScoreComponent } from './session-score/session-score.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    CatalogComponent,
    CategoryComponent,
    ProductComponent,
    CartComponent,
    ShippingComponent,
    CheckoutComponent,
    StockSoldComponent,
    ValidationComponent,
    SessionScoreComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path :'' , component: CatalogComponent},
      {path : 'catalog' , component : CatalogComponent},
      {path : 'category/:categoryName', component : CategoryComponent},
      {path: 'product/:productName', component: ProductComponent},
      {path: 'cart', component: CartComponent},
      {path: 'shipping', component : ShippingComponent},
      {path: 'checkout', component: CheckoutComponent},
      {path: 'stockSold' , component : StockSoldComponent},
      {path : 'validation', component : ValidationComponent},
      {path : 'sessionScore', component : SessionScoreComponent},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
