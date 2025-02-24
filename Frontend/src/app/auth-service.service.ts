import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private loginUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) { }

  login(username: string, password: string):any {
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);
    
      return this.http.post(this.loginUrl, null, { params, responseType: 'text' })
      .pipe(
        map(response => {
          const tokenMatch = response.match(/Token:\s*(.+?)\s*\|/);
          const roleMatch = response.match(/Role:\s*(.+)/);
          if (tokenMatch && roleMatch) {
            return { token: tokenMatch[1].trim(), role: roleMatch[1].trim() };
          }
          throw new Error('Invalid response format');
        })
      );
    
  }
}