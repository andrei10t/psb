import { Component } from '@angular/core';
import { faMoneyBillWave, faHands } from '@fortawesome/free-solid-svg-icons';
import { faUniversity } from '@fortawesome/free-solid-svg-icons';
import { faHandshake } from '@fortawesome/free-solid-svg-icons';
import { faHandHolding } from '@fortawesome/free-solid-svg-icons';
import { MatPaginatorIntl } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'virtual service';
  faMoneyBillWave = faMoneyBillWave;
  faUniversity = faUniversity;
  faHandshake = faHandshake;
  faHandHolding = faHandHolding;

  change() {

  }

}

const polishRangeLabel = (page: number, pageSize: number, length: number) => {
  if (length == 0 || pageSize == 0) { return `0 of ${length}`; }

  length = Math.max(length, 0);

  const startIndex = page * pageSize;

  const endIndex = startIndex < length ?
    Math.min(startIndex + pageSize, length) :
    startIndex + pageSize;

  return `${startIndex + 1} - ${endIndex} of ${length}`;
}

export function getPolishPaginatorIntl() {
  const paginatorIntl = new MatPaginatorIntl();

  paginatorIntl.itemsPerPageLabel = 'Items per page:';
  paginatorIntl.nextPageLabel = 'Next page';
  paginatorIntl.previousPageLabel = 'Previous page';
  paginatorIntl.getRangeLabel = polishRangeLabel;

  return paginatorIntl;
}
