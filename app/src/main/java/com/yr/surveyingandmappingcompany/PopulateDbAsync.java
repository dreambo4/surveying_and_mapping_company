package com.yr.surveyingandmappingcompany;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    private final CompanyDao mDao;

    public PopulateDbAsync(CompanyRoomDatabase db) {
        mDao = db.companyDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        return null;
    }
}
