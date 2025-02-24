import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthServiceService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (data:any) => {
        localStorage.setItem('authToken', data.token);
        localStorage.setItem('userRole', data.role);
        this.router.navigate(['/movies']);
      },
      error: (err: any) => {
        let message = 'Login failed.';
        if (err.error && err.error.message) {
          message += ' ' + err.error.message;
        } else if (err.message) {
          message += ' ' + err.message;
        }
        this.errorMessage = "User or Pass incorrect";
      }
    });
  }
}
