import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient-overview',
  templateUrl: './patient-overview.component.html',
  styleUrls: ['./patient-overview.component.css']
})
export class PatientOverviewComponent implements OnInit {

  constructor(private patientService: PatientService) { }

  patientData: any[] = [];

  ngOnInit(): void {
    this.getPatients();
  }

  getPatients(){
    this.patientService.getAllPatients().subscribe((data: Patient[]) => {
      this.patientData = data;
      console.log(data);
      
    });
  }

  removePatient(id:string){    
    this.patientService.deletePatientById(id).subscribe(data =>{
      console.log('deleted');
      this.getPatients();
    })
  }

}
