package com.thuypham.ptithcm.simplebaseapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.thuypham.ptithcm.simplebaseapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from MovieEntity")
    suspend fun getNowPlaying(): List<MovieEntity>?
}