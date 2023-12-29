import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToleranceFormComponent } from './tolerance-form.component';

describe('ToleranceFormComponent', () => {
  let component: ToleranceFormComponent;
  let fixture: ComponentFixture<ToleranceFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ToleranceFormComponent]
    });
    fixture = TestBed.createComponent(ToleranceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
