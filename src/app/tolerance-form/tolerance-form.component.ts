import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ToleranceService} from "../services/tolerance.service";
import {debounceTime, Observable} from "rxjs";
import {gradeOfTolerance} from "../model/gradeOfTolerance.model";
import {standardAllowance} from "../model/standardAllowance.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Borehole} from "../model/borehole.model";
import {UserService} from "../services/user.service";
import {User} from "../model/user.model";

interface Food {
  value: string;
  viewValue: string;
}


@Component({
  selector: 'app-tolerance-form',
  templateUrl: './tolerance-form.component.html',
  styleUrls: ['./tolerance-form.component.scss']
})
export class ToleranceFormComponent implements OnInit {


  standardAllowancePosts$!: Observable<standardAllowance[]>;
  gradeOfTolerancePosts$!: Observable<gradeOfTolerance[]>;
  borehole!: Borehole;

  userData!: User;

  displayAnswer: boolean = false;

  toleranceForm!: FormGroup;

  displayedColumns: string[] = ['nominalDiameter', 'lowerDeviation', 'upperDeviation'];



  constructor(private ts: ToleranceService,
              private formBuilder: FormBuilder,
              private userService: UserService){
  }



  ngOnInit() {
    this.toleranceForm = this.formBuilder.group({
      nominalDiameter: ['', [Validators.required, Validators.pattern('^\\d+(\\.\\d+)?$')]],
      standardAllowance: [''],
      gradeOfTolerance: [''],
    });

    this.loadStandardAllowancePost();

    this.loadGradeOfTolerancePost();

    this.userService.userData$.subscribe((userData) => {
      this.userData = userData;
      console.log('Received data in tolerance component:', userData);
    }, error => {
      console.log(error);
    }
    );

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
        console.log(this.userData);
        if (this.borehole) {
          this.displayAnswer = true;
          console.log(this.borehole);
          this.ts.postTolerance(this.borehole.nominalDimension, this.borehole.upperLimitDeviation, this.borehole.lowerLimitDeviation, this.userData.id).subscribe({
            next: (postData) => {
              console.log(postData);
              console.log('success');
            },
            error: (postError) => {
              console.log(postError);
            }
          });
        }
      });
  }

  handleSubmit() {
    if (this.toleranceForm.invalid) {
      return;
    }
    console.log(this.toleranceForm.value);
    this.loadTolerance(this.toleranceForm.value.nominalDiameter, this.toleranceForm.value.standardAllowance, this.toleranceForm.value.gradeOfTolerance);
  }
  clearForm() {
    this.toleranceForm.reset();
    this.displayAnswer = false;
  }
  @Output() back: EventEmitter<void> = new EventEmitter<void>();
  handleBack(): void {
    this.clearForm();
    this.back.emit();
  }
}
