import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImmunizationOverviewComponent } from './immunization-overview.component';

describe('ImmunizationOverviewComponent', () => {
  let component: ImmunizationOverviewComponent;
  let fixture: ComponentFixture<ImmunizationOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImmunizationOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImmunizationOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
