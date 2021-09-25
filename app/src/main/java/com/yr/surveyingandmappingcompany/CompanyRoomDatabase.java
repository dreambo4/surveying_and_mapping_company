package com.yr.surveyingandmappingcompany;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Company.class}, version = 1, exportSchema = false)
public abstract class CompanyRoomDatabase extends RoomDatabase {
    private static CompanyRoomDatabase sInstance;

    public abstract CompanyDao companyDao();

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
            };

    public static CompanyRoomDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (CompanyRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            CompanyRoomDatabase.class, "company_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return sInstance;
    }
}
