import { Component, OnInit } from '@angular/core';
import { Observation } from '../models/observation';
import { ObservationService } from '../observation.service';

@Component({
  selector: 'app-observation-overview',
  templateUrl: './observation-overview.component.html',
  styleUrls: ['./observation-overview.component.css']
})
export class ObservationOverviewComponent implements OnInit {

  constructor(private observationService: ObservationService) { }

  observationData: any[] = [];

  ngOnInit(): void {
    this.getObservations();
  }

  getObservations(){
    this.observationService.getAllObservations().subscribe((data: Observation[]) => {
      this.observationData = data;
      console.log(data);
      
    });
  }

  removeObservation(id:string){    
    this.observationService.deleteObservationById(id).subscribe(data =>{
      console.log('deleted');
      this.getObservations();
    })
  }

}
