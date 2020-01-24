import { Component, OnInit } from '@angular/core';
import {ShippingService} from "../shipping.service";
import {FormBuilder, FormGroup, FormControl, Validators} from "@angular/forms";
@Component({
  selector: 'app-shipping',
  templateUrl: './shipping.component.html',
  styleUrls: ['./shipping.component.css']
})
export class ShippingComponent implements OnInit {

  shippingForm : FormGroup;
  title = 'Shipping Form';
  //this postal code regex matches both USA and canadian postal codes
  //postalPattern = '^(\d{5}(-\d{4})?|[A-Z]\d[A-Z] *\d[A-Z]\d)$';
  constructor(private shippingService : ShippingService , private fb : FormBuilder) { 
   
    this.createForm();
  }

  ngOnInit() {
   

  }
  // Validators.pattern('\d{ 5}(-\d{ 4}) ?| [A - Z]\d[A - Z] *\d[A - Z]\d')
  createForm() {
    this.shippingForm = this.fb.group({
      recipientName: new FormControl('', Validators.required ,),
      streetAddress1: new FormControl('', Validators.required),
      streetAddress2: new FormControl(''),
      city: new FormControl('', Validators.required),
      province: new FormControl('', [Validators.required ] ),
      postal: new FormControl('', [Validators.required, this.postalValidator]),
      deliveryInstructions: new FormControl(''),
    });
  }

  onSubmit(shippingData)
  {
    this.shippingService.addShipping(shippingData);
    console.log("this is the shippign data to be sent to the service " , JSON.stringify(shippingData));
    //console.log(JSON.stringify(this.shippingService.getShipping()));
    this.shippingForm.reset();
  }


  postalValidator(control: FormControl)
  {
    let strPattern = control.value;
    let postalPattern = '^(\d{5}(-\d{4})?|[A-Z]\d[A-Z] *\d[A-Z]\d)$';
    if (!(/^(\d{5}(-\d{4})?|[A-Z]\d[A-Z] *\d[A-Z]\d)$/.test(strPattern))) {
      return {
        myERR: {
          parsedDomain: strPattern
        }
      }
    }
    else {
      return null;
    }
    }
}
