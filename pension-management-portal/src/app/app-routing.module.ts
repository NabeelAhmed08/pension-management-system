import { PageNotFoundComponentComponent } from './page-not-found-component/page-not-found-component.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './login/login/auth.guard';
import { ViewPensionComponent } from './pension/view-pension/view-pension.component';
import { ViewPensionerDetailComponent } from './pensioner/view-pensioner-detail/view-pensioner-detail.component';
import { ListPensionerDetailComponent } from './pensioner/list-pensioner-detail/list-pensioner-detail.component';
import { AuthComponent } from './login/login/auth.component';
import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';

export const routingConfiguration: ExtraOptions = {
  paramsInheritanceStrategy: 'always'
};

const routes: Routes = [

  {
    path: '',
    component: HomeComponent,
    pathMatch:'full'
  },

  {
    path: 'login',
    component: AuthComponent,
    pathMatch:'full'
  },

  {
    path: 'pensioner',
    children:[

         {path: 'list', component:ListPensionerDetailComponent,pathMatch:'full',canActivate:[AuthGuard] },
         {path: 'view/:id', component: ViewPensionerDetailComponent,canActivate:[AuthGuard]},
         {path: 'process', component:ViewPensionComponent,pathMatch:'full',canActivate:[AuthGuard] }

        ]
      },


      {
        path: 'not-found', component:PageNotFoundComponentComponent
      },

      {path: '**', redirectTo:'/not-found'}










];

@NgModule({
  imports: [RouterModule.forRoot(routes,routingConfiguration)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
