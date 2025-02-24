import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OmdbMovie } from './pages/OmdbMovie';
import { Observable } from 'rxjs';
import { Movie } from './pages/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieServiceService {

  private apiUrl = 'http://localhost:8080/admin/omdb-movie';
  private baseUrl = 'http://localhost:8080/admin';
  private movieUrl = 'http://localhost:8080/admin/add-movie';
  private AllmovieUrl = 'http://localhost:8080/admin/movies';


  constructor(private http: HttpClient) {}

    getMovieById(imdbId: string): Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/movie/${imdbId}`);
    }

    getAllMovies(page: number, size: number): Observable<any> {
      return this.http.get<Movie[]>(`${this.AllmovieUrl}?page=${page}&size=${size}`);
    }
    
  
  addMovie(movie: Movie): Observable<any> {
    return this.http.post<any>(this.movieUrl, movie, { responseType: 'text' as 'json' });
  }

  getMovie(title?: string, year?: string, plot?: string): Observable<OmdbMovie> {
    let params = new HttpParams();

    if (title && title.trim() !== '') {
      params = params.set('title', title.trim());
    }
    if (year && year.trim() !== '') {
      params = params.set('year', year.trim());
    }
    if (plot && plot.trim() !== '') {
      params = params.set('plot', plot.trim());
    }

    return this.http.get<OmdbMovie>(this.apiUrl, { params });
  }

   deleteMovie(imdbId: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/remove-movie/${imdbId}`, { responseType: 'text' });
  }

  deleteMovies(imdbIds: string[]): Observable<any> {
    return this.http.request('delete', `${this.baseUrl}/remove-movie`, { body: imdbIds });
  }

  addMovies(movies: OmdbMovie[]): Observable<any> {
    return this.http.post(`${this.baseUrl}/admin/add-movie`, movies);
  }
  searchMovies(title: string, page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('title', title)
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.baseUrl}/movies/search`, { params });
  }
  
}

