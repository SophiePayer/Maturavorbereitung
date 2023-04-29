import { Component, OnInit } from '@angular/core';
import { EvidenceReportService } from '../evidence-report.service';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-evidencereport-form',
  templateUrl: './evidencereport-form.component.html',
  styleUrls: ['./evidencereport-form.component.css']
})
export class EvidencereportFormComponent implements OnInit {

  constructor(
    private evidenceService: EvidenceReportService, 
    private formBuilder: FormBuilder, 
    private route: ActivatedRoute, 
    private router: Router 
    ) { }


  emptyEvidenceReport: any = {
    id: null,
    url: '',
    status: 'draft',
    UsageContext: [],
    Identifier: [],
    Subject: null
  };

  emptyIdentifier: any = {
    id: 0, 
    use: "usual",
    value: "",
    system: "",
    type: "",
    display: "",
    period: []
  }

  emptyUsageContext: any = {
    id: null, 
    code: {},
    valueCodeableConcept: {},
    valueReference: {},
    
  }
 emptyPeriod: any = {
    id:null,
    start: "",
    end: ""
  }
  emptyCoding: any = {
    id:null,
    system: "",
    version: "",
    code: "",
    display: "",
    userSelected: true

  }

  emptyCodeableConcept: any = {
    id:null,
    coding: [],
    text: ""
  }
  
  emptyReference: any = {
    id:null,
    reference: "",
    type: "",
    identifier: {},
    display: ""
  }

  emptySubject: any = {
    id:null,
    characteristic: [],
    note: [],
  }

  emptyCharacteristic: any = {
    id: null, 
    code: {},
    valueCodeableConcept: {},
    valueReference: {},
  }

  emptyAnnotation: any = {
    id: null, 
    authorReference: {},
    authorString: "",
    text: "",
    time: ""
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

  
  private usageContextFormGroup = () => new FormGroup({
    code: this.codingFormGroup(),
    valueCodeableConcept: this.codeableconceptFormGroup(),
    valueReference: this.referenceFormGroup(),
  });

  private codingFormGroup = () => new FormGroup({
    code: new FormControl<string>(""),
    system: new FormControl<string>(""),
    display: new FormControl<string>(""),
    version: new FormControl<string>(""),
    userSelected: new FormControl<boolean>(true)
  });

  private codeableconceptFormGroup = () => new FormGroup({
    coding: new FormArray([this.codingFormGroup()]),
    text: new FormControl<string>(""),
  });

  private referenceFormGroup = () => new FormGroup({
    reference: new FormControl<string>(""),
    type: new FormControl<string>(""),
    display: new FormControl<string>("")
  });

  private subjectFormGroup = () => new FormGroup({
    characteristics: new FormArray([this.characteristicFormGroup()]),
    note: new FormArray([this.annotationFormgroup()])
  
  });

  private characteristicFormGroup = () => new FormGroup({
    code: this.codingFormGroup(),
    valueCodeableConcept: this.codeableconceptFormGroup(),
    valueReference: this.referenceFormGroup()
  });

  private annotationFormgroup = () => new FormGroup({
    authorReference: this.referenceFormGroup(),
    authorString: new FormControl<string>(""),
    time: new FormControl<string>(""),
    text: new FormControl<string>("")
  });

  public evidenceReportForm: FormGroup = new FormGroup({
    id: new FormControl<number | null>(null),
    status: new FormControl<string>(this.emptyEvidenceReport.status),
    identifier: new FormArray<FormGroup>([]),
    useContext: new FormArray<FormGroup>([]),
    subject: new FormGroup({})
  });

  public addIdentifier(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.evidenceReportForm.controls["identifier"] as any).push(this.identifierFormGroup());
    }
  }

  public adduseContext(amount: number = 1): void{

    for (let i = 0; i < amount; i++) {
      (this.evidenceReportForm.controls["useContext"] as any).push(this.usageContextFormGroup());
    }
  }
 

  removeIdentifier(index: number) {
    (this.evidenceReportForm.controls["identifier"] as any).removeAt(index);
  }

  removeuseContext(index: number) {
    (this.evidenceReportForm.controls["useContext"] as any).removeAt(index);
  }


  getIdentifierControls(): any {
    return (this.evidenceReportForm.controls["identifier"] as any).controls;
  }

  getuseContextControls(): any {
    return (this.evidenceReportForm.controls["useContext"] as any).controls;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {

      if (params['id'] === undefined || params['id'] === null || params['id'] === ""){
        this.evidenceReportForm.patchValue(this.emptyEvidenceReport);
      }else{
        this.evidenceService.getEvidenceReportById(params['id']).subscribe((response: any) => {          

          // adjust comment controls amount
          this.addIdentifier(response.identifier.length);
          this.adduseContext(response.useContext.length)

          this.evidenceReportForm.patchValue(response);
          
          
        });
      }

    });
  }

  onDateChange(event: any) {

    console.log(event.target.value);
    
  }

  onSubmit() {
    if (this.evidenceReportForm.valid) {      
      const updatedObject: any = {
        id: this.evidenceReportForm.value.id,
        status: this.evidenceReportForm.value.status,
        identifier: this.evidenceReportForm.value.identifier,
        useContext: this.evidenceReportForm.value.useContext,
        subject: this.evidenceReportForm.value.subject,
      };

      console.log(this.evidenceReportForm.value.id);
      console.log(updatedObject);
      this.evidenceService.putEvidenceReport(updatedObject).subscribe(response => {
        console.log(response);
        this.router.navigate(["/evidencereport"]);
        
      });
    }
  }

}
