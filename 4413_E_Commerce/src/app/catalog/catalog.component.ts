import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { BackendDetailsService} from "../backend-details.service";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  catalogList;
  constructor(private http : HttpClient , private backend : BackendDetailsService) { 
    
  }

  ngOnInit() {
    this.catalogPopulate();
  }

  catalogPopulate()
  {
    let url = this.backend.getURL();
    
    url += "Catalog?";
    this.http.get(url).subscribe(data => {
        this.catalogList = data;
       // console.log("this is the datatype of the response " , typeof data);
        console.log("catalog list " + JSON.stringify(this.catalogList));
    });
  }



}
