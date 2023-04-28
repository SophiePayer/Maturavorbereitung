import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientOverviewComponent } from './patient-overview/patient-overview.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EvidencereportFormComponent } from './evidencereport-form/evidencereport-form.component';
import { EvidencereportOverviewComponent } from './evidencereport-overview/evidencereport-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientOverviewComponent,
    PatientFormComponent,
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
