import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EvidencereportFormComponent } from './evidencereport-form/evidencereport-form.component';
import { EvidencereportOverviewComponent } from './evidencereport-overview/evidencereport-overview.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';

const routes: Routes = [
  {path: "", "pathMatch": 'full', redirectTo: "patientoverview"},
  {path: "patientoverview", component: PatientOverviewComponent},
  {path: "patient/new", component: PatientFormComponent},
  {path: "patient/:id", component: PatientFormComponent},
  {path: "evidenceoverview", component: EvidencereportOverviewComponent},
  {path: "evidence/new", component: EvidencereportFormComponent},
  {path: "evidence/:id", component: EvidencereportFormComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
