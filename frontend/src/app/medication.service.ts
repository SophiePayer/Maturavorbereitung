import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medication } from './models/medication';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {

  constructor(private http: HttpClient) { }

  private static BASE_MEDICATION_URL = "http://localhost:8080/api/medication/";

  //Alle Medication bekommen
  public getAllMedications(){
    return this.http.get<Medication[]>(MedicationService.BASE_MEDICATION_URL);
  }

  //Eine Medication durch ID bekommen
  public getMedicationById(id:string){
    return this.http.get<Medication>(MedicationService.BASE_MEDICATION_URL + id)
  }

  // Eine Medication editieren
  public putMedication(medication:any){
    return this.http.put<Medication>(MedicationService.BASE_MEDICATION_URL + medication.id, medication)
  }

  //Einen Practitioner löschen
  public deleteMedicationById(id:string){
    return this.http.delete(MedicationService.BASE_MEDICATION_URL + id)
  }

  //Einen Practitioner hinzufügen
  public postMedication(medication:any){
    return this.http.post<Medication>(MedicationService.BASE_MEDICATION_URL, medication)
  }
}
