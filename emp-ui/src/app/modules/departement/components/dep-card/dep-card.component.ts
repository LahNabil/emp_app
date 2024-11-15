import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DepartementResponse} from "../../../../models/DepartementResponse";

@Component({
  selector: 'app-dep-card',
  templateUrl: './dep-card.component.html',
  styleUrl: './dep-card.component.scss'
})
export class DepCardComponent {
  private _dep: DepartementResponse = {};
  img: string = "https://cronuts.digital/wp-content/uploads/2023/12/6D03D.png"

  @Output() private edit: EventEmitter<DepartementResponse> = new EventEmitter<DepartementResponse>();
  @Output() private details: EventEmitter<DepartementResponse> = new EventEmitter<DepartementResponse>();
  @Output() private archive: EventEmitter<DepartementResponse> = new EventEmitter<DepartementResponse>();
  get dep(): DepartementResponse {
    return this._dep;
  }

  @Input()
  set dep(value: DepartementResponse) {
    this._dep = value;
  }


  onEdit() {
    this.edit.emit(this._dep);

  }
  onArchive(){
    this.archive.emit(this._dep);
  }

  onDetails() {
    this.details.emit(this._dep);
  }
}
