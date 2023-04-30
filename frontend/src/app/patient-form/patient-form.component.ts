import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {

  constructor(
    private patientService: PatientService, 
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

  emptyIdentifier: any = {
    id: 0, 
    use: "usual",
    value: "",
    system: "",
    display: "",
    period: []
  }

  emptyPeriod: any = {
    id:0,
    start: "",
    end: ""
  }

  emptyName: any = {
    id: 0, 
    use: "usual",
    text: "",
    family: ""
  }

  emptyAddress: any = {
    id: 0, 
    use: "home",
    city: ""
  }

  emptyTelecom: any = {
    id: 0, 
    use: "home",
    value: ""
  }

  private periodFormGroup = () => new FormGroup({
    start: new FormControl<Date>(this.emptyPeriod.start),
    end: new FormControl<Date>(this.emptyPeriod.end)
  })

  private identifierFormGroup = () => new FormGroup({
    use: new FormControl<string>(""),
    value: new FormControl<string>(""),
    system: new FormControl<string>(""),
    display: new FormControl<string>(""),
    period: this.periodFormGroup(),
  });

  private nameFormGroup = () => new FormGroup({
    use: new FormControl<string>(""),
    text: new FormControl<string>(""),
    family: new FormControl<string>(""),
    given: new FormArray([]),
    prefix: new FormArray([]),
    suffix: new FormArray([]),
    period: this.periodFormGroup()
  });

  private addressFormGroup = () => new FormGroup({
    use: new FormControl<string>(""),
      type: new FormControl<string>(""),
      text: new FormControl<string>(""),
      line: new FormArray([new FormControl<string>("")]),
      city: new FormControl<string>(""),
      district: new FormControl<string>(""),
      state: new FormControl<string>(""),
      postalCode: new FormControl<number>(0),
      country: new FormControl<string>(""),
      period: this.periodFormGroup()

  });

  private telecomFormGroup = () => new FormGroup({
    system: new FormControl<string>(""),
    value: new FormControl<string>(""),
    use: new FormControl<string>(""),
    rank: new FormControl<number>(0),
    period: this.periodFormGroup()
  });

  public patientForm: FormGroup = new FormGroup({
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

  public addName(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.patientForm.controls["name"] as any).push(this.nameFormGroup());
    }
  }

  public addIdentifier(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.patientForm.controls["identifier"] as any).push(this.identifierFormGroup());
    }
  }

  public addAddress(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.patientForm.controls["address"] as any).push(this.addressFormGroup());
    }
  }

  public addTelecom(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.patientForm.controls["telecom"] as any).push(this.telecomFormGroup());
    }
  }

  removeName(index: number) {
    (this.patientForm.controls["name"] as any).removeAt(index);
  }

  removeIdentifier(index: number) {
    (this.patientForm.controls["identifier"] as any).removeAt(index);
  }

  removeAddress(index: number) {
    (this.patientForm.controls["address"] as any).removeAt(index);
  }

  removeTelecom(index: number) {
    (this.patientForm.controls["telecom"] as any).removeAt(index);
  }

  getNameControls(): any {
    return (this.patientForm.controls["name"] as any).controls;
  }

  getLines(form:any){
    return form.controls.line.controls;
  }

  getGiven(form:any){
    return form.controls.given.controls;
  }

  getPrefix(form:any){
    return form.controls.prefix.controls;
  }

  getSuffix(form:any){
    return form.controls.suffix.controls;
  }

  getIdentifierControls(): any {
    return (this.patientForm.controls["identifier"] as any).controls;
  }

  getAddressControls(): any {
    return (this.patientForm.controls["address"] as any).controls;
  }

  getTelecomControls(): any {
    return (this.patientForm.controls["telecom"] as any).controls;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {

      if (params['id'] === undefined || params['id'] === null || params['id'] === ""){
        this.patientForm.patchValue(this.emptyPatient);
      }else{
        this.patientService.getPatientById(params['id']).subscribe((response: any) => {          

          // adjust comment controls amount
          this.addAddress(response.address.length);
          this.addIdentifier(response.identifier.length);
          this.addTelecom(response.telecom.length);
          this.addName(response.name.length)

          this.patientForm.patchValue(response);
          console.log(response);
          
          console.log("PatientForm",this.patientForm.value);
          
        });
      }

    });
  }

  onDateChange(event: any) {

    console.log(event.target.value);
    
    //this.objectForm.patchValue({ creationDateTime: new Date(event.target.value) });
  }

  onSubmit() {
    if (this.patientForm.valid) {      
      const updatedObject: any = {
        id: this.patientForm.value.id,
        decreasedBoolean: this.patientForm.value.deceasedBoolean,
        active: this.patientForm.value.active,
        gender: this.patientForm.value.gender,
        birthdate: this.patientForm.value.birthdate,
        telecom: this.patientForm.value.telecom,
        address: this.patientForm.value.address,
        identifier: this.patientForm.value.identifier,
        name: this.patientForm.value.name
      };

      console.log(this.patientForm.value.deceasedDateTime);
      console.log(updatedObject);
      this.patientService.putPatient(updatedObject).subscribe(response => {
        console.log(response);
        this.router.navigate(["/patientoverview"]);
        
      });
    }
  }

}
