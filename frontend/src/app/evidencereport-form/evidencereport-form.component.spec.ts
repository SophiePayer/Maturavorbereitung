import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvidencereportFormComponent } from './evidencereport-form.component';

describe('EvidencereportFormComponent', () => {
  let component: EvidencereportFormComponent;
  let fixture: ComponentFixture<EvidencereportFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvidencereportFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvidencereportFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
