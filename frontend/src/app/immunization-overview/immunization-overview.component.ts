import { Component, OnInit } from '@angular/core';
import { Immunization } from '../models/immunization';
import { ImmunizationService } from '../immunization.service';

@Component({
  selector: 'app-immunization-overview',
  templateUrl: './immunization-overview.component.html',
  styleUrls: ['./immunization-overview.component.css']
})
export class ImmunizationOverviewComponent implements OnInit {

  constructor(private immunizationService: ImmunizationService) { }

  immunizationData: any[] = [];

  ngOnInit(): void {
    this.getImmunizations();
  }

  getImmunizations(){
    this.immunizationService.getAllImmunizations().subscribe((data: Immunization[]) => {
      this.immunizationData = data;
      console.log(data);
      
    });
  }

  removeImmunization(id:string){    
    this.immunizationService.deleteImmunizationById(id).subscribe(data =>{
      console.log('deleted');
      this.getImmunizations();
    })
  }

}
