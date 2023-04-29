import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { ReactiveFormsModule } from '@angular/forms';

import { ImmunizationFormComponent } from './immunization-form/immunization-form.component';
import { ImmunizationOverviewComponent } from './immunization-overview/immunization-overview.component';
import { ObservationFormComponent } from './observation-form/observation-form.component';
import { ObservationOverviewComponent } from './observation-overview/observation-overview.component';
import { MedicationOverviewComponent } from './medication-overview/medication-overview.component';
import { MedicationFormComponent } from './medication-form/medication-form.component';
import { EvidencereportFormComponent } from './evidencereport-form/evidencereport-form.component';
import { EvidencereportOverviewComponent } from './evidencereport-overview/evidencereport-overview.component';




@NgModule({
  declarations: [
    AppComponent,
    PatientOverviewComponent,
    PatientFormComponent,
    ImmunizationFormComponent,
    ImmunizationOverviewComponent,
    ObservationFormComponent,
    ObservationOverviewComponent,
    MedicationFormComponent,
    MedicationOverviewComponent,
    EvidencereportFormComponent,
    EvidencereportOverviewComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
