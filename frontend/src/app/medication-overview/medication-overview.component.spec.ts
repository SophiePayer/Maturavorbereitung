import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationOverviewComponent } from './medication-overview.component';

describe('MedicationOverviewComponent', () => {
  let component: MedicationOverviewComponent;
  let fixture: ComponentFixture<MedicationOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicationOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicationOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
