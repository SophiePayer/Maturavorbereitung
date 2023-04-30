import { TestBed } from '@angular/core/testing';

import { EvidenceReportService } from './evidence-report.service';

describe('EvidenceReportService', () => {
  let service: EvidenceReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvidenceReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
