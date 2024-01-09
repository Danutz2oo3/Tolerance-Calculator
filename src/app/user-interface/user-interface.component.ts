import {Component, EventEmitter, Output, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {UserService} from "../services/user.service";
import {User} from "../model/user.model";
import {HistoryService} from "../services/history.service";
import {Borehole} from "../model/borehole.model";
@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.scss'],
  standalone: true,
  imports: [MatIconModule]
})
export class UserInterfaceComponent implements OnInit{

  userData!: User;
  historyElem: Borehole[] = [];
  constructor(private userService: UserService,
              private historyService: HistoryService) {}

  ngOnInit(): void {
    this.userService.userData$.subscribe((userData) => {
      this.userData = userData;
    });
  }

  @Output() logOut: EventEmitter<void> = new EventEmitter<void>();
  @Output() calculator: EventEmitter<void> = new EventEmitter<void>();
  @Output() history: EventEmitter<void> = new EventEmitter<void>();
  onLogOutClick(): void {
    this.logOut.emit();
  }
  onCalculatorClick(): void {
    this.calculator.emit();
    this.userService.sendUserData(this.userData);
  }
  onHistoryClick(): void {
    this.history.emit();
    this.historyService.getCalculationHistory(this.userData.id).subscribe({
      next: (historyElem) => {
        this.historyElem = historyElem;
        this.sendDataToHistory(this.historyElem);
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
  sendDataToHistory(data: any): void {
    this.historyService.sendHistoryData(data);
  }
}

