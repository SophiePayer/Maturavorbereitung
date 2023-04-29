import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvidencereportOverviewComponent } from './evidencereport-overview.component';

describe('EvidencereportOverviewComponent', () => {
  let component: EvidencereportOverviewComponent;
  let fixture: ComponentFixture<EvidencereportOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvidencereportOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvidencereportOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
