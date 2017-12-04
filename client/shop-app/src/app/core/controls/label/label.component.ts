import {Component, Input} from '@angular/core';
import {BaseComponent} from '../../base/base.component';

@Component({
  selector: 'app-label',
  templateUrl: './label.component.html',
  styleUrls: ['./label.component.scss']
})
export class LabelComponent extends BaseComponent {

  @Input('labelKey') labelKey: string;
  private _label: string;
  @Input('_class') _class: string;

  constructor() {
    super('LabelComponent');
  }

  onLocaleValueChange(value: string): void {
    this._label = value;
  }


  get label(): string {
    return this._label;
  }
}
