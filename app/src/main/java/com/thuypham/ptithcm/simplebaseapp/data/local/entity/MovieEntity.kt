package com.thuypham.ptithcm.simplebaseapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String? = "",
    val overview: String? = "",
    val posterPath: String? = ""
)

fun MovieEntity.movieEntityToMovie(): Movie {
    val movieEntity = this
    return Movie().apply {
        id = movieEntity.id
        title = movieEntity.title
        overview = movieEntity.overview
        posterPath = movieEntity.posterPath
    }
}


fun Movie.movieToMovieEntity(): MovieEntity {
    val movie = this
    return MovieEntity(movie.id ?: 0, movie.title, movie.overview, movie.posterPath)
}

fun List<MovieEntity>?.movieEntitiesToMovies(): ArrayList<Movie> {
    val movies = arrayListOf<Movie>()
    this?.forEach {
        movies.add(it.movieEntityToMovie())
    }
    return movies
}
