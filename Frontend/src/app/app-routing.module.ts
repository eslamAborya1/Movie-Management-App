import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponentComponent } from './pages/dashboard-component/dashboard-component.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { MoviesComponent } from './pages/movies/movies.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponentComponent } ,
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'movies', component: MoviesComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
