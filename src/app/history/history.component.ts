import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HistoryService} from "../services/history.service";
import {historyElem} from "../model/historyElem.model";
import {Borehole} from "../model/borehole.model";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit{
  historyElem: Borehole[] = [];

  constructor(private historyService: HistoryService,
               ) {}

  ngOnInit(): void {
    this.historyService.getHistoryData().subscribe((data) => {
      this.historyElem = data;
      console.log('Received data in history component:', data);
    });
  }
  @Output() back: EventEmitter<void> = new EventEmitter<void>();
  handleBack() {
    this.back.emit();
  }
}
