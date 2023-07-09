package com.bignerdranch.android.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.criminalintent.Crime
import java.util.UUID
import kotlinx.coroutines.flow.Flow
//data access object
@Dao
interface CrimeDao {
    //pull info out of database
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id:UUID): Crime
}