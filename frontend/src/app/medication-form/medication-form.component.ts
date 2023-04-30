import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicationService } from '../medication.service';

@Component({
  selector: 'app-medication-form',
  templateUrl: './medication-form.component.html',
  styleUrls: ['./medication-form.component.css']
})
export class MedicationFormComponent implements OnInit {


  constructor( private medicationService: MedicationService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router ) { }



    emptyMedication: any = {
      id: null,
      identifier: [],
      status: 'active',
      totalVolume: {},
      ingredient: [],
      batch: {},
      code: {}
    };

    medicationForm = this.fb.group({
      id: this.fb.control(''),
      identifier: this.fb.array([]),
      status: this.fb.control(''),
      ingredient: this.fb.array([]),
      batch: this.createBatchFormGroup(),
      totalVolume: this.createQuantityFormGroup(),
      code: this.createCodeableConceptFormGroup(),

    });

    createIdentifierFormGroup(): FormGroup{
      return new FormGroup({
        use: this.fb.control(''),
        system: this.fb.control(''),
        value: this.fb.control(''),
        display: this.fb.control(''),
        period: this.createPeriodFormGroup()
        })
    }
    public createPeriodFormGroup(){
      return new FormGroup({
          start: this.fb.control(''),
          end: this.fb.control('')
      })
    }

    createIngredientFormGroup(): FormGroup {
      return new FormGroup({
        isActive: this.fb.control(Boolean),
        //item: this.createCodeableReferenceFormGroup(),
        strengthRatio: this.createRatioFormGroup(),
        strengthCC: this.createCodeableConceptFormGroup(),
        strengthQuantity: this.createQuantityFormGroup()
      })
    }
  createRatioFormGroup():FormGroup {
    return new FormGroup({
      number: this.createQuantityFormGroup()
    })
  }
  createCodeableReferenceFormGroup():FormGroup {
    return new FormGroup({
      concept: this.createCodeableConceptFormGroup(),
      reference:this.createReferenceFormGroup()
    })
  }
  createReferenceFormGroup(): FormGroup {
    return new FormGroup({
      reference: this.fb.control(''),
      type : this.fb.control(''),
      identifier: this.createIdentifierFormGroup(),
      display: this.fb.control('')
    })
  }

    createBatchFormGroup(): FormGroup {
      return new FormGroup({
        iotnumber: this.fb.control(''),
        expirationdate: this.fb.control('')
      })
    }

    createCodeableConceptFormGroup(): FormGroup {
      return new FormGroup({
        coding: this.fb.array([this.createCodingFormGroup()]),
        text: this.fb.control('')
      })
    }
  createCodingFormGroup(): FormGroup {
    return new FormGroup({
      system:this.fb.control(''),
      version:this.fb.control(''),
      code:this.fb.control(''),
      display:this.fb.control(''),
      userSelected:this.fb.control(Boolean),
    })
  }
    createQuantityFormGroup():FormGroup {
      return new FormGroup({
        decimal:this.fb.control(Number),
        system:this.fb.control(''),
        comparator:this.fb.control(''),
        code:this.fb.control(''),
        unit:this.fb.control('')
      })
    }

    public addIdentifier(amount: number = 1): void{

      for (let i = 0; i < amount; i++) {
        (this.medicationForm.controls["identifier"] as any).push(this.createIdentifierFormGroup());
      }
    }
    public addIngredient(amount: number = 1): void{

      for (let i = 0; i < amount; i++) {
        (this.medicationForm.controls["ingredient"] as any).push(this.createIngredientFormGroup());
      }
    }

    removeIdentifier(index: number) {
      (this.medicationForm.controls["identifier"] as any).removeAt(index);
    }

    removeIngredient(index: number) {
      (this.medicationForm.controls["ingredient"] as any).removeAt(index);
    }

    getIdentifierControls(): any {
      return (this.medicationForm.controls["identifier"] as any).controls;
    }

    getIngredientControls(): any {
      return (this.medicationForm.controls["ingredient"] as any).controls;
    }

  ngOnInit(): void {

    this.route.params.subscribe(params => {

      if (params['id'] === undefined || params['id'] === null || params['id'] === ""){
        this.medicationForm.patchValue(this.emptyMedication);
      }else{
        this.medicationService.getMedicationById(params['id']).subscribe((response: any) => {

          // adjust comment controls amount
          this.addIngredient(response.ingredient.length);
          this.addIdentifier(response.identifier.length);

          this.medicationForm.patchValue(response);
          console.log(response);

          console.log("MedicationForm",this.medicationForm.value);

        });
      }

    });
  }

  onDateChange(event: any) {

    console.log(event.target.value);

    //this.objectForm.patchValue({ creationDateTime: new Date(event.target.value) });
  }

  onSubmit() {
    if (this.medicationForm.valid) {
      const updatedObject: any = {
        id: this.medicationForm.value.id,
        identifier: this.medicationForm.value.identifier,
        status: this.medicationForm.value.status,
        ingredient: this.medicationForm.value.ingredient,
        batch: this.medicationForm.value.batch,
        totalVolume: this.medicationForm.value.totalVolume,
        code:this.medicationForm.value.code,

      };

      console.log(updatedObject);
      this.medicationService.putMedication(updatedObject).subscribe(response => {
        console.log(response);
        this.router.navigate(["/medicationoverview"]);

      });
    }
  }

}
