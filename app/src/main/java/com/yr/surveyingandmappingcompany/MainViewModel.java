package com.yr.surveyingandmappingcompany;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private CompanyRepositoryLocal mCompanyRepositoryLocal;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mCompanyRepositoryLocal = new CompanyRepositoryLocal(application);

        boolean mIsInternetConnected = new InternetChecker().isConnected(application);
        if (mIsInternetConnected) {
            CompanyRepositoryApi CompanyRepositoryApi = new CompanyRepositoryApi(application);
            CompanyRepositoryApi.loadmApiData();
        }
    }

    /**
     * 取得所有公司資訊
     * @return 公司資訊串列
     */
    public LiveData<List<Company>> getCompanyList() {
        return mCompanyRepositoryLocal.getAll();
    }

    /**
     * 取得 n 筆公司資訊
     * @param number 欲取得筆數
     * @return 公司資訊串列
     */
    public LiveData<List<Company>> getCompanyList(int number) {
        return mCompanyRepositoryLocal.getAll(number);
    }

}
