import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


// Service to pass employee data between various components in the application
@Injectable({
  providedIn: 'root'
})
export class SellerDataService {

  constructor() { }


  private dataSource = new BehaviorSubject(new Object());
  currentData = this.dataSource.asObservable(); // Create obesrvable to watch over the state of the data over time


  changeData(data: object) {  // Watch over the data as observer
    this.dataSource.next(data);
  }
}
