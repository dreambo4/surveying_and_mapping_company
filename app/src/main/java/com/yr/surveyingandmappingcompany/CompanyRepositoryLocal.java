package com.yr.surveyingandmappingcompany;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 本地資料庫 資料讀取
 */
public class CompanyRepositoryLocal {

    private CompanyDao mCompanyDao;

    public CompanyRepositoryLocal(Application application) {
        CompanyRoomDatabase db = CompanyRoomDatabase.getDatabase(application);
        mCompanyDao = db.companyDao();
    }

    /**
     * 取得所有公司資訊
     * @return 公司資訊串列
     */
    public LiveData<List<Company>> getAll() {
        return mCompanyDao.getAll();
    }

    /**
     * 取得 n 筆公司資訊
     * @param number 欲取得筆數
     * @return 公司資訊串列
     */
    public LiveData<List<Company>> getAll(int number) {
        return mCompanyDao.getAll(number);
    }

    /**
     * 新增一筆公司資訊
     * 若此 `unifiedBusinessNo` 已存在，則以新資料取代之
     * @param company 公司資訊
     * @return 被影響公司資訊的ID
     * @throws ExecutionException 新增發生錯誤
     * @throws InterruptedException 新增被中斷
     */
    public long insert(Company company) throws ExecutionException, InterruptedException {
        long companyId = new InsertAsyncTask(mCompanyDao).execute(company).get();
        return companyId;
    }

    private static class InsertAsyncTask extends AsyncTask<Company, Void, Long> {
        private CompanyDao mAsyncTaskDao;

        InsertAsyncTask(CompanyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(Company... companies) {
            long companyId = mAsyncTaskDao.insert(companies[0]);
            return companyId;
        }
    }
}
