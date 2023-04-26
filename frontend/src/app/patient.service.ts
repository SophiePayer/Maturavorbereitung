import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from './models/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  private static BASE_PATIENT_URL = "http://localhost:8080/api/patient/";

  //Alle Patienten bekommen
  public getAllPatients(){
    return this.http.get<Patient[]>(PatientService.BASE_PATIENT_URL);
  }

  //Einen Patienten durch ID bekommen
  public getPatientById(id:string){
    return this.http.get<Patient>(PatientService.BASE_PATIENT_URL + id)
  }

  // Einen Practitioner editieren
  public putPatient(patient:any){
    return this.http.put<Patient>(PatientService.BASE_PATIENT_URL + patient.id, patient)
  }

  //Einen Practitioner löschen
  public deletePatientById(id:string){
    return this.http.delete(PatientService.BASE_PATIENT_URL + id)
  }

  //Einen Practitioner hinzufügen
  public postPatient(patient:any){
    return this.http.post<Patient>(PatientService.BASE_PATIENT_URL, patient)
  }
}
