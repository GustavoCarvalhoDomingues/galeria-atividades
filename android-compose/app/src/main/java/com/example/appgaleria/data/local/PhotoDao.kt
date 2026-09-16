package com.example.appgaleria.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos ORDER BY id DESC") fun observeAll(): Flow<List<PhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(photo: PhotoEntity)
    @Delete suspend fun delete(photo: PhotoEntity)
}
