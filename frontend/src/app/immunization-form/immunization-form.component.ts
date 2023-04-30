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
     reference: '',
      identifier: {},
      type: '',
      display: '',
    };
    
  emptyImmunization: any = {
    id: null,
    iotnumber: '',
    expirationdate: '',
    patient: null,
    occurrenceDateTime: '',
    occurrenceString: '',
  };

  emptyPeriod: any = {
    id:0,
    start: "",
    end: ""
  }

  private patientFormGroup = () => new FormGroup({
    reference: new FormControl<string>(""),
    type: new FormControl<string>(""),
    display: new FormControl<string>(""),
    identifier: this.identifierFormGroup(),
  });

  private identifierFormGroup = () => new FormGroup({
    use: new FormControl<string>(""),
    value: new FormControl<string>(""),
    system: new FormControl<string>(""),
    display: new FormControl<string>(""),
    period: this.periodFormGroup(),
  });

  private periodFormGroup = () => new FormGroup({
    start: new FormControl<Date>(this.emptyPeriod.start),
    end: new FormControl<Date>(this.emptyPeriod.end)
  })

  public ImmunizationForm: FormGroup = new FormGroup({
    id: new FormControl<string>(this.emptyImmunization.id),
    iotnumber: new FormControl<string>(this.emptyImmunization.iotnumber),
    expirationdate: new FormControl<Date>(this.emptyImmunization.expirationdate),
    patient: this.patientFormGroup(),
    occurrenceDateTime: new FormControl<Date>(this.emptyImmunization.occurrenceDateTime),
    occurrenceString: new FormControl<string>(this.emptyImmunization.occurrenceString),

  });


  getPatientControls(): any {
    return (this.ImmunizationForm.controls["patient"] as any).controls;
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
