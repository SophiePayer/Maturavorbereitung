import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Immunization } from './models/immunization';

@Injectable({
  providedIn: 'root'
})
export class ImmunizationService {

  constructor(private http: HttpClient) { }

  private static BASE_IMMUNIZATION_URL = "http://localhost:8080/api/immunization/";

  //Alle Patienten bekommen
  public getAllImmunizations(){
    return this.http.get<Immunization[]>(ImmunizationService.BASE_IMMUNIZATION_URL);
  }

  //Einen Patienten durch ID bekommen
  public getImmunizationById(id:string){
    return this.http.get<Immunization>(ImmunizationService.BASE_IMMUNIZATION_URL + id)
  }

  // Einen Practitioner editieren
  public putImmunization(immunization:any){
    return this.http.put<Immunization>(ImmunizationService.BASE_IMMUNIZATION_URL + immunization.id, immunization)
  }

  //Einen Practitioner löschen
  public deleteImmunizationById(id:string){
    return this.http.delete(ImmunizationService.BASE_IMMUNIZATION_URL + id)
  }

  //Einen Practitioner hinzufügen
  public postImmunization(immunization:any){
    return this.http.post<Immunization>(ImmunizationService.BASE_IMMUNIZATION_URL, immunization)
  }
}
