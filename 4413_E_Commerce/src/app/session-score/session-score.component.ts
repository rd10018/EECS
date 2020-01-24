import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormGroup, FormControl , FormBuilder} from "@angular/forms";
import {BackendDetailsService} from "../backend-details.service";

@Component({
  selector: 'app-session-score',
  templateUrl: './session-score.component.html',
  styleUrls: ['./session-score.component.css']
})
export class SessionScoreComponent implements OnInit {
 totalScore = 0;
  score ;
  sessionScoreList = [];
  websiteTotalScore = 0;
  sessionID = "";
  constructor(private fb : FormBuilder , private backend : BackendDetailsService , private http : HttpClient) { 
    
  }

  ngOnInit() {
    this.score = this.fb.group({
      user_score : new FormControl('')
    });
   
    
  }

urlAdded =  false;
  onSubmit(score)
  { 
   
    let url = this.backend.getURL() + "sessionScore?score=" + score['user_score'] + "&sessionId=" + this.backend.sessionID;
    console.log("this is the url to be sent to the backend" , url);
    this.http.get(url, { responseType: 'text' }).subscribe(data => {
      let s  = JSON.parse(data);
      this.backend.sessionID = s["sessionId"];
      console.log("this is the session id ", data);
      console.log("this i the total score " , s.totalScore);
     this.totalScore = s.totalScore;
      //calculating the total csore for all of the users
      let url1 = this.backend.getURL() + "sessionScoreListPrint";
      this.http.get(url1 , {responseType :"text"} ).subscribe(data =>{
          this.sessionScoreList = JSON.parse(data);
          console.log("this is the total data for all users " , JSON.stringify(this.sessionScoreList));
          this.websiteTotalScore = 0;
          for(let i=0;i<this.sessionScoreList.length; i++)
         {
            this.websiteTotalScore += +this.sessionScoreList[i].totalScore;
         }

      });


    });
  }
        
 
}
