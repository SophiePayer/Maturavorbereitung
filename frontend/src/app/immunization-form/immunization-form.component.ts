import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ImmunizationService } from '../immunization.service';

@Component({
  selector: 'app-immunization-form',
  templateUrl: './immunization-form.component.html',
  styleUrls: ['./immunization-form.component.css']
})
export class ImmunizationFormComponent implements OnInit {
  

  constructor(
    private immunizationService: ImmunizationService, 
    private formBuilder: FormBuilder, 
    private route: ActivatedRoute, 
    private router: Router 
    ) { }

    emptyPatient: any = {
      id: null,
      active: false,
      birthdate: '',
      gender: 'male',
      deceasedBoolean: false,
      deceasedDateTime:null,
      identifier: [],
      address: [],
      telecom: [],
      name: []
    };
    
  emptyImmunization: any = {
    id: null,
    iotnumber: '',
    expirationdate: '',
    patient: null,
    occurrenceDateTime: '',
    occurrenceString: '',
  };

  public ImmunizationForm: FormGroup = new FormGroup({
    id: new FormControl<string>(this.emptyImmunization.id),
    iotnumber: new FormControl<string>(this.emptyImmunization.iotnumber),
    expirationdate: new FormControl<Date>(this.emptyImmunization.expirationdate),
    patient: this.createPatientFormGroup(),
    occurrenceDateTime: new FormControl<Date>(this.emptyImmunization.occurrenceDateTime),
    occurrenceString: new FormControl<string>(this.emptyImmunization.occurrenceString),

  });

  createPatientFormGroup(): FormGroup {
    return new FormGroup({
        id: new FormControl<number | null>(null),
        active: new FormControl<boolean>(this.emptyPatient.active),
        birthdate: new FormControl<Date>(this.emptyPatient.birthdate),
        gender: new FormControl<string>(this.emptyPatient.gender),
        deceasedBoolean: new FormControl<boolean>(this.emptyPatient.deceasedBoolean),
        identifier: new FormArray<FormGroup>([]),
        address: new FormArray<FormGroup>([]),
        telecom: new FormArray<FormGroup>([]),
        name: new FormArray<FormGroup>([])
      });
    
  }
  

  ngOnInit(): void {
    this.route.params.subscribe(params => {

      if (params['id'] === undefined || params['id'] === null || params['id'] === ""){
        this.ImmunizationForm.patchValue(this.emptyImmunization);
      }else{
        this.immunizationService.getImmunizationById(params['id']).subscribe((response: any) => {          

          // adjust comment controls amount
         
          this.ImmunizationForm.patchValue(response);
          console.log(response);
          
          console.log("ImmunizationForm",this.ImmunizationForm.value);
          
        });
      }

    });
  }

  onDateChange(event: any) {

    console.log(event.target.value);
    
    //this.objectForm.patchValue({ creationDateTime: new Date(event.target.value) });
  }

  onSubmit() {
    if (this.ImmunizationForm.valid) {     
      
      const updatedObject: any = {
        id: this.ImmunizationForm.value.id,
        iotnumber: this.ImmunizationForm.value.iotnumber,
        expirationdate: this.ImmunizationForm.value.expirationdate,
        patient: this.ImmunizationForm.value.patient,
        occurrenceDateTime: this.ImmunizationForm.value.occurrenceDateTime,
        occurrenceString: this.ImmunizationForm.value.occurrenceString,

      };

      console.log(this.ImmunizationForm.value.occurrenceDateTime);
      console.log(updatedObject);
      this.immunizationService.putImmunization(updatedObject).subscribe(response => {
        console.log(response);
        this.router.navigate(["/immunizationoverview"]);
        
      });
    }
  }

}
