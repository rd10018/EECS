import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendDetailsService {

  backendURL : string;
  sessionID = "";
  constructor() {
    this.backendURL = "http://192.168.0.16:54000/";
    //this.backendURL = "http://localhost:54000/";
   }

   getURL() : string{
     return this.backendURL;
   }


   setSession(session)
   {
     this.sessionID = session;
   }

   getSession()
   {
     return this.sessionID;
   }
}
