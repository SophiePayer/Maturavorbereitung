import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observation } from './models/observation';

@Injectable({
  providedIn: 'root'
})
export class ObservationService {

  constructor(private http: HttpClient) { }

  private static BASE_OBSERVATION_URL = "http://localhost:8080/api/observation/";

  //Alle Observations bekommen
  public getAllObservations(){
    return this.http.get<Observation[]>(ObservationService.BASE_OBSERVATION_URL);
  }

  //Eine Observation durch ID bekommen
  public getObservationById(id:string){
    return this.http.get<Observation>(ObservationService.BASE_OBSERVATION_URL + id)
  }

  // Einen Practitioner editieren
  public putObservation(observation:any){
    return this.http.put<Observation>(ObservationService.BASE_OBSERVATION_URL + observation.id, observation)
  }

  //Einen Practitioner löschen
  public deleteObservationById(id:string){
    return this.http.delete(ObservationService.BASE_OBSERVATION_URL + id)
  }

  //Einen Practitioner hinzufügen
  public postObservation(observation:any){
    return this.http.post<Observation>(ObservationService.BASE_OBSERVATION_URL, observation)
  }
}
