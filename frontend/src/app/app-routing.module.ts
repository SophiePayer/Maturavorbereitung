import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EvidencereportFormComponent } from './evidencereport-form/evidencereport-form.component';
import { EvidencereportOverviewComponent } from './evidencereport-overview/evidencereport-overview.component';
import { MedicationFormComponent } from './medication-form/medication-form.component';
import { MedicationOverviewComponent } from './medication-overview/medication-overview.component';
import { ObservationFormComponent } from './observation-form/observation-form.component';
import { ObservationOverviewComponent } from './observation-overview/observation-overview.component';
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
  {path: "immunization/:id", component: ImmunizationFormComponent},

  {path: "evidenceoverview", component: EvidencereportOverviewComponent},
  {path: "evidence/new", component: EvidencereportFormComponent},
  {path: "evidence/:id", component: EvidencereportFormComponent},
  {path:"medicationoverview", component:MedicationOverviewComponent},
  {path:"medication/new", component:MedicationFormComponent},
  {path:"medication/:id", component:MedicationFormComponent},
  {path:"observationoverview", component:ObservationOverviewComponent},
  {path:"observation/new", component:ObservationFormComponent},
  {path:"observation/:id", component:ObservationFormComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
