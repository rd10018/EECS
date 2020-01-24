import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {BackendDetailsService} from "../backend-details.service";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryList;
  categoryName;
  constructor(private route : ActivatedRoute, private http : HttpClient , private backend : BackendDetailsService) { }

  ngOnInit() {
    this.getCategoryName();
    this.categoryPopulate();
  }

  getCategoryName()
  {
    this.route.paramMap.subscribe(params => {
    this.categoryName = params.get('categoryName');
    });
  }

  categoryPopulate()
  {
    let url = this.backend.getURL();
    url+= "Category?name=" + this.categoryName;
    this.http.get(url).subscribe(data => {
        this.categoryList = data;
        console.log("this is the category list " + JSON.stringify(this.categoryList));
    });
  }
}
