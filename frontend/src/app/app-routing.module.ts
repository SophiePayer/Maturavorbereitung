import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';
import { ImmunizationFormComponent } from './immunization-form/immunization-form.component';
import { ImmunizationOverviewComponent } from './immunization-overview/immunization-overview.component';

const routes: Routes = [
  {path: "", "pathMatch": 'full', redirectTo: "immunizationoverview"},
  {path: "patientoverview", component: PatientOverviewComponent},
  {path: "patient/new", component: PatientFormComponent},
  {path: "patient/:id", component: PatientFormComponent},
  {path: "immunizationoverview", component: ImmunizationOverviewComponent},
  {path: "immunization/new", component: ImmunizationFormComponent},
  {path: "immunization/:id", component: ImmunizationFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
