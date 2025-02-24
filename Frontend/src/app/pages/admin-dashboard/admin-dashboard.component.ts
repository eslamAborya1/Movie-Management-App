import { Component } from '@angular/core';
import { MovieServiceService } from 'src/app/movie-service.service';
import { OmdbMovie } from '../OmdbMovie';
import { Movie } from '../movie';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent {
   
   title: string = '';
   year: string = '';
   plot: string = '';
 
   
   movie: OmdbMovie | null = null;
   error: string = '';

   movies: OmdbMovie[] = [];

   moviee:Movie | null = null;

   movieExistsInDb: boolean = false;

   constructor(private omdbService: MovieServiceService,private router: Router) {}
 
   ngOnInit(): void {
  }
   
  searchMovie(): void {
    this.error = '';
    this.movie = null;
    this.movieExistsInDb = false;
    
    this.omdbService.getMovie(this.title, this.year, this.plot)
      .subscribe({
        next: (result: OmdbMovie) => {
          if (result && result.Response && result.Response.toLowerCase() === 'true') {
            this.movie = result;
            const imdbID = result.imdbID || result.imdbID;
            if (imdbID) {
              this.checkMovieInDatabase(imdbID);
            } else {
              console.error('IMDb ID is undefined in the result');
            }
          } else {
            this.error = result ? result.Error || 'Error retrieving movie' : 'Error retrieving movie';
          }
        },
        error: (err: any) => {
          this.error = "No Movie found";
        }
      });
  }


  deleteMovie(imdbID: any) {
    this.omdbService.deleteMovie(imdbID).subscribe({
      next: () => {
        this.movies = this.movies.filter(movie => movie.imdbID !== imdbID);
        this.movieExistsInDb = false;
        alert('Movie Deleted successfully!');

      },
      error: (err) => {
        this.error = 'Error deleting movie: ' + err.message;
      }
    });
  }

  

  addMovie(movie: OmdbMovie): void {
    if (!this.moviee) {
      this.moviee = { title: '', year: '', genre: '', imdbID: '' };
    }
    
    this.moviee.title = movie.Title;
    this.moviee.year = movie.Year;
    this.moviee.genre = movie.Genre;
    this.moviee.imdbID=movie.imdbID;

    this.omdbService.addMovie(this.moviee).subscribe(
      res => {
        alert('Movie added successfully!');
      },
      err => {
        console.error('Error adding movie', err);
        alert('Error adding movie');
      }
    );
  }
   isMovieInList(movie: OmdbMovie): boolean {
    return this.movies.some(m => m.imdbID === movie.imdbID);
  }
  

  checkMovieInDatabase(imdbID: string): void {
    this.omdbService.getMovieById(imdbID).subscribe({
      next: (movieData: OmdbMovie) => {
        if (!movieData || !movieData.imdbID) {
          this.handleMovieNotFound();
        } else {
          this.movieExistsInDb = true;
        }
      },
      error: (err) => {
        this.movieExistsInDb = false;
      }
    });
  }
  
  private handleMovieNotFound(): void {
    this.movieExistsInDb = false;
  }
  
 
}