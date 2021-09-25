package com.yr.surveyingandmappingcompany;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompanyDao {

    @Query("SELECT * FROM companies Order by Issuance_Date desc")
    LiveData<List<Company>> getAll();

    @Query("SELECT * FROM companies Order by Issuance_Date desc limit 0, :limit")
    LiveData<List<Company>> getAll(int limit);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Company company);
}
