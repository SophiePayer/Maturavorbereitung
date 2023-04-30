import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ObservationService } from '../observation.service';

@Component({
  selector: 'app-observation-form',
  templateUrl: './observation-form.component.html',
  styleUrls: ['./observation-form.component.css']
})
export class ObservationFormComponent implements OnInit {
  
  constructor(
    private observationService: ObservationService, 
    private formBuilder: FormBuilder, 
    private route: ActivatedRoute, 
    private router: Router 
    ) { }

  emptyObservation: any = {
      id: null,
      canonical: '',
      instantiatesReference: null,
      observationDefinition: null,
      basedon: [],
      triggeredby: [],
  };

  emptyIdentifier: any = {
    id: 0, 
    use: "usual",
    value: "",
    system: "",
    type: "",
    display: "",
    period: []
  };

  emptyPeriod: any = {
    id:0,
    start: "",
    end: ""
  };

  emptyBasedOn: any = {
    id: 0, 
    reference: "",
    type: "",
    display: ""
  };

  emptyTriggeredBy: any = {
    id: 0, 
  };

  private periodFormGroup = () => new FormGroup({
    start: new FormControl<Date>(this.emptyPeriod.start),
    end: new FormControl<Date>(this.emptyPeriod.end)
  });

  private identifierFormGroup = () => new FormGroup({
    use: new FormControl<string>(""),
    value: new FormControl<string>(""),
    system: new FormControl<string>(""),
    display: new FormControl<string>(""),
    period: this.periodFormGroup(),
  });

  private triggeredbyFormGroup = () => new FormGroup({
    system: new FormControl<string>("")
  });

  private basedonFormGroup = () => new FormGroup({
    system: new FormControl<string>(""), 
    reference: new FormControl<string>(""),
    type: new FormControl<string>(""),
    display: new FormControl<string>("")
  });


  public observationForm: FormGroup = new FormGroup({
    id: new FormControl<number | null>(null),
    canonical: new FormControl<string>("ok"),
    instantiatesReference: this.createInstantiatesReferenceFormGroup(),
    observationDefinition: this.createObservationDefinitionFormGroup(),
    identifier: new FormArray<FormGroup>([]),
    basedon: new FormArray<FormGroup>([]),
    triggeredby: new FormArray<FormGroup>([]),
  });

  createObservationDefinitionFormGroup():FormGroup {
    return new FormGroup({
      id: this.formBuilder.control('')

    });
  }

  createInstantiatesReferenceFormGroup():FormGroup {
    return new FormGroup({
      id: this.formBuilder.control(''),
      reference: this.formBuilder.control(''),
      type: this.formBuilder.control(''),
      display: this.formBuilder.control('')
    });
  }


  public addTriggeredBy(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.observationForm.controls["triggerdby"] as any).push(this.triggeredbyFormGroup());
    }
  }

  public addIdentifier(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.observationForm.controls["identifier"] as any).push(this.identifierFormGroup());
    }
  }

  public addBasedOn(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.observationForm.controls["basedon"] as any).push(this.basedonFormGroup());
    }
  }

  removeTriggeredBy(index: number) {
    (this.observationForm.controls["triggeredby"] as any).removeAt(index);
  }

  removeIdentifier(index: number) {
    (this.observationForm.controls["identifier"] as any).removeAt(index);
  }

  removeBasedOn(index: number) {
    (this.observationForm.controls["basedon"] as any).removeAt(index);
  }

  getTriggeredByControls(): any {
    return (this.observationForm.controls["triggeredby"] as any).controls;
  }

  getIdentifierControls(): any {
    return (this.observationForm.controls["identifier"] as any).controls;
  }

  getBasedOnControls(): any {
    return (this.observationForm.controls["address"] as any).controls;
  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {

      if (params['id'] === undefined || params['id'] === null || params['id'] === ""){
        this.observationForm.patchValue(this.observationForm);
      }else{
        this.observationService.getObservationById(params['id']).subscribe((response: any) => {          

          // adjust comment controls amount
          this.addBasedOn(response.address.length);
          this.addIdentifier(response.identifier.length);
          this.addTriggeredBy(response.telecom.length);

          this.observationForm.patchValue(response);
          console.log(response);
          
          console.log("ObservationForm",this.observationForm.value);
          
        });
      }

    });
  }

  onDateChange(event: any) {

    console.log(event.target.value);
    
    //this.objectForm.patchValue({ creationDateTime: new Date(event.target.value) });
  }

  onSubmit() {
    if (this.observationForm.valid) {      
      const updatedObject: any = {
        id: this.observationForm.value.id,
        canonical: this.observationForm.value.canonical,
        instantiatesReference: this.observationForm.value.instantiatesReference,
        observationDefinition: this.observationForm.value.observationDefinition,
        basedon: this.observationForm.value.basedon,
        triggeredby: this.observationForm.value.triggeredby,
        identifier: this.observationForm.value.identifier,
      };

      console.log(this.observationForm.value.canonical);
      console.log(updatedObject);
      this.observationService.putObservation(updatedObject).subscribe(response => {
        console.log(response);
        this.router.navigate(["/observationoverview"]);
        
      });
    }
  }

}
