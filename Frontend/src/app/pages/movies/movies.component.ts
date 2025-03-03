import { Component, OnInit } from '@angular/core';
import { MovieServiceService } from 'src/app/movie-service.service';
import { Movie } from '../movie';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss']
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  error: string = '';
  searchTerm: string = '';
  currentPage: number = 0;
  pageSize: number = 10;
  totalItems: number = 0;

  constructor(private movieService: MovieServiceService) {}

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.movieService.searchMovies(this.searchTerm, this.currentPage, this.pageSize).subscribe({
      next: (data: any) => {
        this.movies = data.content;            // list of movies
        this.totalItems = data.totalElements; // number of movies
      },
      error: (err) => {
        console.error('Error loading movies', err);
        this.error = 'Error loading movies';
      }
    });
  }

  onSearch(): void {
    this.currentPage = 0; 
    this.loadMovies();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.loadMovies();
  }
  totalPages(): number {
    return Math.ceil(this.totalItems / this.pageSize);
  }
}