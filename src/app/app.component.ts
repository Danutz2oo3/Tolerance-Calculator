import { Component } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {NgFor} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [
    trigger('fadeInOut', [
      state('void', style({ opacity: 0 })),
      transition(':enter, :leave', [animate(1000)]),
    ]),
  ],
})
export class AppComponent {

  title = "toleranceGUI";
  showLoginForm: boolean = true;
  showToleranceForm: boolean = false;
  showUserInterface: boolean = false;
  showHistory: boolean = false;
  handleSignIn(): void {
    // Toggle the visibility of the tolerance form
    this.showUserInterface = true;
    this.showLoginForm = false;
  }

  handleLogOut(): void {
    // Toggle the visibility of the tolerance form
    this.showUserInterface = false;
    this.showLoginForm = true;
  }

  handleBack(): void {
    this.showUserInterface = true;
    this.showToleranceForm = false;
    this.showHistory = false;
  }

  handleCalculator(): void {
    // Toggle the visibility of the tolerance form
    this.showUserInterface = false;
    this.showToleranceForm = true;
  }

  handleHistory(): void {
    // Toggle the visibility of the tolerance form
    this.showHistory = true;
    this.showUserInterface = false;
  }
}
