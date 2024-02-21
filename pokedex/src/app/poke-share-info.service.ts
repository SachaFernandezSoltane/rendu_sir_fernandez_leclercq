import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PokeShareInfoService {

  public stringVar = new Subject<number>();
  constructor() { }

  getObservable(): Subject<number> {
    return this.stringVar;
  }

  setObservable(newStringVar: number) {
    this.stringVar.next(newStringVar);
  }
}
