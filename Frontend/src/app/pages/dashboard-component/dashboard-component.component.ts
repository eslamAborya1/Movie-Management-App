import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-component',
  templateUrl: './dashboard-component.component.html',
  styleUrls: ['./dashboard-component.component.scss']
})
export class DashboardComponentComponent {

  isAdmin: boolean = false;


  constructor(private router: Router) {}


  ngOnInit(): void {
    const role = localStorage.getItem('userRole'); 
    this.isAdmin = role === 'ADMIN';
  }
  
  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userRole');
    this.router.navigate(['/login']);
  }
}
