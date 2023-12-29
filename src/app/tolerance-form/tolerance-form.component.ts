import {Component, OnInit} from '@angular/core';
import {ToleranceService} from "../services/tolerance.service";
import {debounceTime, Observable} from "rxjs";
import {gradeOfTolerance} from "../model/gradeOfTolerance.model";
import {standardAllowance} from "../model/standardAllowance.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Borehole} from "../model/borehole.model";

interface Food {
  value: string;
  viewValue: string;
}


@Component({
  selector: 'app-tolerance-form',
  templateUrl: './tolerance-form.component.html',
  styleUrls: ['./tolerance-form.component.css']
})
export class ToleranceFormComponent implements OnInit {


  standardAllowancePosts$!: Observable<standardAllowance[]>;
  gradeOfTolerancePosts$!: Observable<gradeOfTolerance[]>;
  borehole!: Borehole;

  toleranceForm!: FormGroup;

  displayedColumns: string[] = ['nominalDiameter', 'lowerDeviation', 'upperDeviation'];


  constructor(private ts: ToleranceService, private formBuilder: FormBuilder) {
  }


  ngOnInit() {
    this.toleranceForm = this.formBuilder.group({
      nominalDiameter: ['', [Validators.required, Validators.pattern('^\\d+(\\.\\d+)?$')]],
      standardAllowance: [''],
      gradeOfTolerance: [''],
    });

    this.loadStandardAllowancePost();

    this.loadGradeOfTolerancePost();


    // Subscribe to value changes for standardAllowance form control
    this.toleranceForm.get('standardAllowance')?.valueChanges
      .pipe(debounceTime(500))
      .subscribe((value) => {
        this.loadGradeOfTolerancePost(value);
      });

    // Subscribe to value changes for gradeOfTolerance form control
    this.toleranceForm.get('gradeOfTolerance')?.valueChanges
      .pipe(debounceTime(500))
      .subscribe((value) => {
        this.loadStandardAllowancePost(value);
        // You can perform actions here based on the selected value
      });
  }

  loadStandardAllowancePost(gradeOfTolerance?: number): void {
    this.standardAllowancePosts$ = this.ts.getStandardAllowancePost(gradeOfTolerance);
  }

  loadGradeOfTolerancePost(standardAllowanceId?: number): void {
    this.gradeOfTolerancePosts$ = this.ts.getGradeOfTolerancePost(standardAllowanceId);
  }

  loadTolerance(nominalDiameter: number, standardAllowance: number, gradeOfTolerance: number): void {
    this.ts.getTolerance(nominalDiameter, standardAllowance, gradeOfTolerance)
      .subscribe((data: Borehole) => {
        this.borehole = data;
      });
  }

  handleSubmit() {
    if (this.toleranceForm.invalid) {
      return;
    }
    console.log(this.toleranceForm.value);
    this.loadTolerance(this.toleranceForm.value.nominalDiameter, this.toleranceForm.value.standardAllowance, this.toleranceForm.value.gradeOfTolerance);
  }

}
