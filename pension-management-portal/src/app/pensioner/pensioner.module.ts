import { ReactiveFormsModule } from '@angular/forms';
import { ViewPensionerDetailComponent } from './view-pensioner-detail/view-pensioner-detail.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { ListPensionerDetailComponent } from './list-pensioner-detail/list-pensioner-detail.component';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { SearchPensionerComponent } from './search-pensioner/search-pensioner.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS, MAT_SNACK_BAR_DEFAULT_OPTIONS_FACTORY} from '@angular/material/snack-bar';


@NgModule({
  declarations: [
    ListPensionerDetailComponent,
    ViewPensionerDetailComponent,
    SearchPensionerComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatTableModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    MatSnackBarModule


  ],
  exports: [
    SearchPensionerComponent
  ],
  providers:[
 {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS,useValue: {duration: 2500} }
  ]
})
export class PensionerModule { }
