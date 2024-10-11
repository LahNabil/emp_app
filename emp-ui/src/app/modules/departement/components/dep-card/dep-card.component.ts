import {Component, Input} from '@angular/core';
import {DepartementResponse} from "../../../../models/DepartementResponse";

@Component({
  selector: 'app-dep-card',
  templateUrl: './dep-card.component.html',
  styleUrl: './dep-card.component.scss'
})
export class DepCardComponent {
  private _dep: DepartementResponse = {};
  img: string = "https://cronuts.digital/wp-content/uploads/2023/12/6D03D.png"

  get dep(): DepartementResponse {
    return this._dep;
  }

  @Input()
  set dep(value: DepartementResponse) {
    this._dep = value;
  }


}
