import {Component, Input} from '@angular/core';
import {DepartementResponse} from "../../../../models/DepartementResponse";

@Component({
  selector: 'app-dep-card',
  templateUrl: './dep-card.component.html',
  styleUrl: './dep-card.component.scss'
})
export class DepCardComponent {
  get depCover(): string {
    return this._depCover;
  }

  private _dep: DepartementResponse = {};

  private _depCover: string = "";


  get dep(): DepartementResponse {
    return this._dep;
  }

  @Input()
  set dep(value: DepartementResponse) {
    this._dep = value;
  }



}
