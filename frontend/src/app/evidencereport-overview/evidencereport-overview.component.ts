import { Component, OnInit } from '@angular/core';
import { EvidenceReportService } from '../evidence-report.service';
import { EvidenceReport } from '../models/evidencereport';

@Component({
  selector: 'app-evidencereport-overview',
  templateUrl: './evidencereport-overview.component.html',
  styleUrls: ['./evidencereport-overview.component.css']
})
export class EvidencereportOverviewComponent implements OnInit {

  constructor(private evidenceService: EvidenceReportService) { }

  evidenceData: any[] = [];

  ngOnInit(): void {
    this.getEvidencReports();
  }

  getEvidencReports(){
    this.evidenceService.getAllEvidenceReport().subscribe((data: EvidenceReport[]) => {
      this.evidenceData = data;
      console.log(data);
      
    });
  }

  removeEvidenceReport(id:string){    
    this.evidenceService.deleteEvidenceById(id).subscribe(data =>{
      console.log('deleted');
      this.getEvidencReports();
    })
  }

}
