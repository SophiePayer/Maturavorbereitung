import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';

const routes: Routes = [
  {path: "", "pathMatch": 'full', redirectTo: "patientoverview"},
  {path: "patientoverview", component: PatientOverviewComponent},
  {path: "patient/new", component: PatientFormComponent},
  {path: "patient/:id", component: PatientFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
