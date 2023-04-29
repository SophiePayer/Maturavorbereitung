import { Component, OnInit } from '@angular/core';
import { MedicationService } from '../medication.service';
import { Medication } from '../models/medication';

@Component({
  selector: 'app-medication-overview',
  templateUrl: './medication-overview.component.html',
  styleUrls: ['./medication-overview.component.css']
})
export class MedicationOverviewComponent implements OnInit {
  medicationData: any[] = [];


  constructor(private medicationService: MedicationService, ) { }

  ngOnInit(): void {
    this.getMedications();
  }

  getMedications(){
    this.medicationService.getAllMedications().subscribe((data:Medication[])=>{
      this.medicationData = data
      console.log(data);
      
    });
  }

  removeMedication(id:string){    
    this.medicationService.deleteMedicationById(id).subscribe(data =>{
      console.log('deleted');
      this.getMedications();
    })
  }
}
