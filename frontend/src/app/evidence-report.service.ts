import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EvidenceReport } from './models/evidencereport';

@Injectable({
  providedIn: 'root'
})
export class EvidenceReportService {

  constructor(private http: HttpClient) { }

  private static BASE_PATIENT_URL = "http://localhost:8080/api/evidencereport/";

  //Alle Patienten bekommen
  public getAllEvidenceReport(){
    return this.http.get<EvidenceReport[]>(EvidenceReportService.BASE_PATIENT_URL);
  }

  //Einen Patienten durch ID bekommen
  public getEvidenceReportById(id:string){
    return this.http.get<EvidenceReport>(EvidenceReportService.BASE_PATIENT_URL + id)
  }

  // Einen Practitioner editieren
  public putEvidenceReport(evidencereport:any){
    return this.http.put<EvidenceReport>(EvidenceReportService.BASE_PATIENT_URL + evidencereport.id, evidencereport)
  }

  //Einen Practitioner löschen
  public deleteEvidenceById(id:string){
    return this.http.delete(EvidenceReportService.BASE_PATIENT_URL + id)
  }

  //Einen Practitioner hinzufügen
  public postEvidenceReport(evidencereport:any){
    return this.http.post<EvidenceReport>(EvidenceReportService.BASE_PATIENT_URL, evidencereport)
  }
}
