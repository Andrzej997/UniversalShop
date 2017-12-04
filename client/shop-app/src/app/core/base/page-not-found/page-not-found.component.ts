import {Component} from '@angular/core';
import {BaseComponent} from '../base.component';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.scss']
})
export class PageNotFoundComponent extends BaseComponent {

  constructor() {
    super('PageNotFoundComponent');
  }

}
