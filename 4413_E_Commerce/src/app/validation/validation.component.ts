import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators , FormControl } from '@angular/forms';
@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.css']
})
export class ValidationComponent implements OnInit {
 
  // title = 'Shipping Form';
  // shippingForm: FormGroup;
  // constructor(private fb: FormBuilder) {
  //   this.createForm();
  // }
  // createForm() {
  //   this.shippingForm = this.fb.group({
  //     recipientName: ['', Validators.required],
  //     streetAddress1: ['', Validators.required],
  //     streetAddress2: ['',],
  //     city: ['', Validators.required],
  //     state: ['', Validators.required],
  //     province: ['', Validators.required],
  //     postal: ['', Validators.required],
  //     deliveryInstructions: ['',],
  //   });
  // }


  // ngOnInit() {

  // }

  name;
  addr1;
  addr2;
  city;

  prov;
  postal;

  inst;


  // adding Validators.required (as the second element) will make this require
  myFormGroop = this.myFB.group({
    name: new FormControl('',[Validators.required, this.myCustomValidator2]),
    addr1: new FormControl('', Validators.required),
    addr2: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    prov: new FormControl('', Validators.required),
    post: new FormControl('', Validators.required),
    instruct: new FormControl('', Validators.required),
  });



  //this is for test
  myCustomValidator(control: FormControl) {
    let email = control.value;
    if (email && email.indexOf("@") != -1) {
      let [_, domain] = email.split("@");
      if (domain !== "lol.com") {
        return {
          emailDomain: {
            parsedDomain: domain
          }
        }
      }
    }
    return null;
  }

  //this is for test pt2
  myCustomValidator2(control: FormControl) {
    let temp = control.value;

    if (temp.length < 3) {
      return {
        myERR: {
          parsedDomain: temp
        }
      }
    }
    else {
      return null;
    }

  }


  formCtrl1 = new FormControl('');

  getTot() {

  }

  mySubmit() {
    console.warn(this.myFormGroop.value);
  }



  constructor(private myFB: FormBuilder) { }

  ngOnInit() {
  }
}
