package com.yr.surveyingandmappingcompany;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * API 資料讀取
 */
public class CompanyRepositoryApi {

    private String TAG = getClass().getSimpleName();
    private Application mApplication;

    public CompanyRepositoryApi(Application application) {
        mApplication = application;
    }

    /**
     * 將公司資訊存入本地資料庫
     * @param company 公司資訊
     */
    private void saveCompanyData(Company company) {
        long companyId = 0;
        CompanyRepositoryLocal companyRepositoryLocal = new CompanyRepositoryLocal(mApplication);
        try {
            companyId = companyRepositoryLocal.insert(company);
        } catch (ExecutionException e) {
            Log.d(TAG, "saveCompanyData: 新增發生錯誤! " + e);
        } catch (InterruptedException e) {
            Log.d(TAG, "saveCompanyData: 新增中斷! " + e);
        }
    }

    /**
     * 取得API資料
     */
    public void loadmApiData() {
        RequestQueue requestQueue = Volley.newRequestQueue(mApplication);
        String url = "https://od.moi.gov.tw/api/v1/rest/datastore/301000000A-001772-005";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) {
                                List<Company> companyList = new ArrayList<>();
                                JSONObject result = response.getJSONObject("result");
                                JSONArray records = result.getJSONArray("records");
                                for (int i = 1; i < records.length(); i++) {
                                    // 第0筆是標題，跳過
                                    JSONObject record = records.getJSONObject(i);
                                    Company company = new Company(
                                            record.getString("Survey_Industry"),
                                            record.getString("Unified_Business_No"),
                                            record.getString("Address"),
                                            record.getString("TEL"),
                                            record.getString("Business_Scope"),
                                            record.getString("Business_Conditions"),
                                            record.getString("Survey_Industry_No"),
                                            record.getString("Issuance_Date"),
                                            record.getString("Expiration_Date"),
                                            record.getString("Survey_Engineer"),
                                            record.getString("Surveyor")
                                    );
                                    companyList.add(company);
                                    saveCompanyData(company);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse API下載失敗: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onResponse API下載失敗: " + error.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}
