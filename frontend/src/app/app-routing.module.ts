import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicationFormComponent } from './medication-form/medication-form.component';
import { MedicationOverviewComponent } from './medication-overview/medication-overview.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';

const routes: Routes = [
  {path: "", "pathMatch": 'full', redirectTo: "patientoverview"},
  {path: "patientoverview", component: PatientOverviewComponent},
  {path: "patient/new", component: PatientFormComponent},
  {path: "patient/:id", component: PatientFormComponent},
  {path:"medicationoverview", component:MedicationOverviewComponent},
  {path:"medication/new", component:MedicationFormComponent},
  {path:"medication/:id", component:MedicationFormComponent},
  {path:"observationoverview", component:MedicationOverviewComponent},
  {path:"observation/new", component:MedicationFormComponent},
  {path:"observation/:id", component:MedicationFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
