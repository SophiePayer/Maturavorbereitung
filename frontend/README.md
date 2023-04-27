# Angular Kochrezept

Zielsetzung: Daten vom Backend im Frontend anzeigen, bearbeiten und löschen.

-> Bei Matura: 
    - node_modules und .angular Ordner löschen
    - Starten ab Punkt 6

1. Neues Angular Projekt anlegen
    - ng new <projekname>
        - Would you like to add Angular routing? -> yes
        - Which style sheet format would you liek to use? -> CSS
        -> Projekt wird angelegt mit Git- Repository [gitignore ignoriert bereits alle Dateien, welche beim build generiert werden]
    - siehe https://angular.io/cli/new

2. Im Terminal in den Ordner <projektname> wechseln
    - cd <projektname>

3. Angular Material Dependency hinzufügen
    - ng add @angular/material
        - The package @angular/material<@version> will be installed and executed -> Yes 
        - Bei Folgefragen immer den Default- Wert (Enter)

4. Projekt starten 
    - ng serve
    - im Browser localhost:4200
    - Wenn funktioniert, Programm stoppen mit Strg + C

5. Neue Angular Material Schematic hinzufügen
    - ng generate @angular/material:address-form PractitionerForm
    - Wenn nicht funktioniert -> Versionsproblem:
        - alle angular 15.2.1
        - typescript. 4.9.5
        - log.json löschen
        - npm install
    - Die Schematic is unter /src/app/PractitionerForm aufrufbar

6. Projekt starten
    - ng serve

7. Modules basierend auf Backend erstellen
    - localhost:8080/api/Practitioner -> Backend muss funtkionieren!
    - JSON auf json2ts.com umwandeln lassen
    - neue Datei modules.ts erstellen und die generierten interfaces hineinkopieren
    - Rootobject in Practitioner umändern

8. HTTP Module hinzufügen
    - app.module.ts
        - import { HttpClientModule } from '@angular/common/http';
        - bei imports hinzufügen
    
9. Dataservice erstellen
    - ng c s Practitioner
    - constructor(private http: HttpClient) { ... }

10. Dataservice einrichten
    - static BASE_PRACTITIONER_URL:string = 'http://localhost:8080/api/Practitioner/';
    - Alle Practitioners holen
        -   public getAllPractitioner(){
                return this.http.get<Practitioner[]>(PractitionerService.BASE_PRACTITIONER_URL);
            }
    - Einen Practitioner bekommen
        -   public getPractitionerById(id:string){
                return this.http.getPractitioner(PractitionerService.BASE_PRACTITIONER_URL + id);
            }
    - Einen Practitioner editieren
        - public editPractitionerById(data:any){
            return this.http.put<Practitioner>(PractitionerService.BASE_PRACTITIONER_URL + data.id, data)
          }
    - Einen Practitioner löschen
        - public deletePractitionerById(id:string){
            return this.http.delete(PractitionerService.BASE_PRACTITIONER_URL + id)
          }

11. Table Schematics hinzufügen
    - ng generate @angular/material:table PractitionerTable

12. Table Schemetics anpassen
    - In practitioner-table-datasource.ts
        - Alle PractitionerTableObjects auf dein Model umändern -> z.B. Practitioner
        - Sort by Name hinauslöschen
    - In practitioner-table-component.ts
        - Dataservice im constructor hinzufügen -> constructor(private service: PractitionerService)
        - in NgAfterViewInit
            - Service um alle Objekte zu holen implementieren und dann in this.table.datasource speichern
                - this.service.getAllPractitioners().subscribe(data => { this.table.datasource = data})
        - bei displayColumns 'edit' hinzufügen
        - Funktion um den Namen der Objekte anzuzeigen hinzufügen
            - humanNameToString(row:Practitioner): string{
                return row.name.map(name => name.prefix.join(" ")+ " " + name.given.join(" ") + " " + " " + name.family + " " + " " + name.suffix.join(" ")).join(";");}
    - In practitioner-table.component.html
        - bei dem Column, welches den Namen anzeigt, die Funktion humanNameToString hinzufügen
            - anstatt {{row.name}} -> {{humanNameToString(row)}}
        - Column kopieren für Edit und routerLink zu PractitionerForm
            - <a routerLink='/practitioner/{{row.id}}'>Edit</a>

13. Routing anpassen
    - Damit man gleich zum Table weitergeleitet wird bei Routes[]:
        - {path:"", component:PractitionerTableComponent}

14. AddressForm Schematic an Practitioner anpassen
    - states und onSubmit() hinauslöschen
    - addressForm zu PractitionerForm umbenennen
    - JSON vom Server hineinkopieren und dann im Formbuilder umsetzen

15. Aufbau der Form in TS
    - [] -> new FormArray([...])
    - {} -> new FormGroup({...})
    - ("", 0, true) -> new FormControl<string,number,boolean,Date>("",0,true,new Date())

16. Aufbau der Form in HTML
    - Wegen Angular Schematics:
        - <mat-card>
            <mat-card-header>Hier Header einfügen</mat-card-header>
            <mat-card-content>Hier Content (FormArrays, FormGroups, FormControls) einfügen</mat-card-content>
            <mat-card-actions>Hier Buttons (Hinzufügen) einfügen
                <button mat-raised-button color="primary" (click)="removeIdentifier(identifierIndex)">Delete Identifier</button>
            </mat-card-actions>
          </mat-card>
    - <form [formGroup]="identifier">
        - Bei FormControl: 
            - <div class="row">
                <div class="col">
                    <mat-form-field class="full-width">
                    <input matInput placeholder="System" formControlName="system">
                    </mat-form-field>
                </div>
              </div>
        - Bei FormGroup:
            - <ng-container formGroupName="period">
                <FormControls anführen>
              </ng-container>
        - Bei FormArray:
            - <span *ngFor="let identifier of practitionerForm.controls.identifier.controls; let identifierIndex=index;" [formGroup]='identifier'>
                <FormControls anführen>
              </span>
              <div><button mat-raised-button color="primary" (click)="addNewIdentifier()">Add Identifier</button></div>

17. Routing
    - in app.routing.component.ts bei Routes[]
        - {path: "", component: ""} -> Variablen als z.B. path: "practitioner/:id"
    - in app.component.html
        - <router-outlet></router-outlet>

18. Ausprobieren

19. Ein Objekt bekommen nach ID
    - getPractitioner(){
        this.route.params.subscribe(params =>{
        this.service.getPractitionerById(params["id"]).subscribe(data =>{
        this.currentPractitioner=data
        });
      }
    - Damit die einzelnen FormArrays gemäß des Backends ausgefüllt werden:
        - this.practitionerForm.controls.identifier.clear();
          while (
            this.practitionerForm.controls.identifier.length <
            (this.currentPractitioner?.identifier?.length ?? 0)
          ) {
            this.addNewIdentifier();
          }        
        - Am Ende müssen die Daten vom Backend noch in die Form gespeichert werden
            - this.practitionerForm.patchValue(this.currentPractitioner as any);
    - Im OnInit():
        - getPractitioner();

20. Objekt editieren/löschen
    - Weitere z.B. Identifier hinzufügen:
        - Im HTML Button Hinzufügen mit einem Click Event unter <mat-card-content>
            - <button mat-raised-button color="primary" (click)="addNewIdentifier()">Add Identifier</button>
        - Im TS Methode hinzufügen
            - addNewIdentifier(){
                this.practitionerForm.controls.identifier.push(this.createIdentifierFormGroup());
              }
    - Löschen von z.B Identifier durch Index:
        - Im HTML Button Hinzufügen mit einem Click Event unter <mat-card-content>
            - <mat-card-actions>
                <button mat-raised-button color="primary" (click)="removeAtIdentfier(identifierIndex)">Delete Identifier</button>
              </mat-card-actions>
        - Im TS Methode hinzufügen
            - removeAtIdentfier(index:number){
                this.practitionerForm.controls.identifier.removeAt(index);
              } 
        - WICHTIG, dass identifierIndex=index im *ngFor steht!

22. Form speichern und an Backend senden
    - // update
      const merged = this.practitionerForm.value;
      const id = merged.id;
      const withoutIds = omitDeep(merged,"id");
      
      // falls server streikt, kann es sein, dass sie die ids rauslöschen müssen
      
      this.service.editPractitionerById({id, ... withoutIds}).subscribe(response => {
        console.log('put', response);
        this.getPractitioner()
      });
    

23. Practitioner löschen
    - in Dataservice
        - public deletePractitionerById(id:string){
            return this.http.delete(PractitionerService.BASE_PRACTITIONER_URL + id)
          }
    - in practitioner-table.component.html
        - <ng-container matColumnDef="delete">
            <th mat-header-cell *matHeaderCellDef mat-sort-header>Delete</th>
            <td mat-cell *matCellDef="let row">
                <button mat-raised-button color="primary" (click)="removePractitioner(row.id)">Delete</button>
            </td>
          </ng-container>
    - in practitioner-table.component.ts
        -  removePractitioner(id:string){    
                this.service.deletePractitionerById(id).subscribe(data =>{
                console.log('deleted');
                this.getPractitioners()
                })
            }
        - bei displayColumns 'delete' hinzufügen

24. Neuen Practitioner hinzufügen
    - practitioner-table-html
        - <button mat-raised-button color="primary" routerLink='/practitioner/new'>New Practitioner</button>
    - app-routing.module.ts
        - {path: "practitioner/new", component:PractitionerFormComponent}
    - practitioner-form.ts
        - im ngOnInit umändern auf:
            - this.route.params.subscribe(params =>{
                if(params["id"] != "new"){
                    this.getPractitioner();
                }
              });















package.json sollte so aussehen:

'''
{
  "name": "matura-vorbereitung",
  "version": "0.0.0",
  "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "watch": "ng build --watch --configuration development",
    "test": "ng test"
  },
  "private": true,
  "dependencies": {
    "@angular/animations": "^15.2.1",
    "@angular/cdk": "^15.2.1",
    "@angular/common": "^15.2.1",
    "@angular/compiler": "^15.2.1",
    "@angular/core": "^15.2.1",
    "@angular/forms": "^15.2.1",
    "@angular/material": "^15.2.1",
    "@angular/platform-browser": "^15.2.1",
    "@angular/platform-browser-dynamic": "^15.2.1",
    "@angular/router": "^15.2.1",
    "lodash-omitdeep": "^1.0.14",
    "omit-deep-lodash": "^1.1.7",
    "rxjs": "~7.5.0",
    "tslib": "^2.3.0",
    "zone.js": "~0.11.4"
  },
  "devDependencies": {
    "@angular-devkit/build-angular": "^15.2.1",
    "@angular/cli": "~15.2.1",
    "@angular/compiler-cli": "^15.2.1",
    "@types/jasmine": "~4.0.0",
    "@types/lodash": "^4.14.191",
    "jasmine-core": "~4.3.0",
    "karma": "~6.4.0",
    "karma-chrome-launcher": "~3.1.0",
    "karma-coverage": "~2.2.0",
    "karma-jasmine": "~5.1.0",
    "karma-jasmine-html-reporter": "~2.0.0",
    "typescript": "~4.9.5"
  }
}
'''

# Frontend

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.2.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.