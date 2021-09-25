package com.yr.surveyingandmappingcompany;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;

@Entity(tableName = "companies")
public class Company {

    /**
     * 公司統一編號
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Unified_Business_No")
    private String unifiedBusinessNo;

    /**
     * 測繪業公司名稱
     */
    @NonNull
    @ColumnInfo(name = "Survey_Industry")
    private String surveyIndustry;

    /**
     * 營業住址
     */
    @NonNull
    @ColumnInfo(name = "Address")
    private String address;

    /**
     * 公司電話
     */
    @NonNull
    @ColumnInfo(name = "TEL")
    private String tel;

    /**
     * 營業範圍
     */
    @NonNull
    @ColumnInfo(name = "Business_Scope")
    private String businessScope;

    /**
     * 營業狀態
     */
    @NonNull
    @ColumnInfo(name = "Business_Conditions")
    private String businessConditions;

    /**
     * 測繪業登記證號碼
     */
    @NonNull
    @ColumnInfo(name = "Survey_Industry_No")
    private String surveyIndustryNo;

    /**
     * 測繪業登記證核發日期
     */
    @NonNull
    @ColumnInfo(name = "Issuance_Date")
    private String issuanceDate;

    /**
     * 測繪業登記證有效期限
     */
    @NonNull
    @ColumnInfo(name = "Expiration_Date")
    private String expirationDate;

    /**
     * 登記測量技師人數
     */
    @NonNull
    @ColumnInfo(name = "Survey_Engineer")
    private String surveyEngineer;

    /**
     * 登記測量員人數
     */
    @NonNull
    @ColumnInfo(name = "Surveyor")
    private String surveyor;

    public Company(@NonNull String surveyIndustry,
                   @NonNull String unifiedBusinessNo,
                   @NonNull String address,
                   @NonNull String tel,
                   @NonNull String businessScope,
                   @NonNull String businessConditions,
                   @NonNull String surveyIndustryNo,
                   @NonNull String issuanceDate,
                   @NonNull String expirationDate,
                   @NonNull String surveyEngineer,
                   @NonNull String surveyor
    ) {
//        this.id = UUID.randomUUID().toString();
        this.surveyIndustry = surveyIndustry;
        this.unifiedBusinessNo = unifiedBusinessNo;
        this.address = address;
        this.tel = tel;
        this.businessScope = businessScope;
        this.businessConditions = businessConditions;
        this.surveyIndustryNo = surveyIndustryNo;
        this.issuanceDate = issuanceDate;
        this.expirationDate = expirationDate;
        this.surveyEngineer = surveyEngineer;
        this.surveyor = surveyor;
    }

    @NonNull
    @Override
    public String toString() {
        HashMap<String, String> companyMap = new HashMap<>();
        companyMap.put("Survey_Industry", surveyIndustry);
        companyMap.put("Unified_Business_No", unifiedBusinessNo);
        companyMap.put("Address", address);
        companyMap.put("TEL", tel);
        companyMap.put("Business_Scope", businessScope);
        companyMap.put("Business_Conditions", businessConditions);
        companyMap.put("Survey_Industry_No", surveyIndustryNo);
        companyMap.put("Issuance_Date", issuanceDate);
        companyMap.put("Expiration_Date", expirationDate);
        companyMap.put("Survey_Engineer", surveyEngineer);
        companyMap.put("Surveyor", surveyor);

        return companyMap.toString();
    }

    @NonNull
    public String getSurveyIndustry() {
        return surveyIndustry;
    }

    @NonNull
    public String getUnifiedBusinessNo() {
        return unifiedBusinessNo;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getTel() {
        return tel;
    }

    @NonNull
    public String getBusinessScope() {
        return businessScope;
    }

    @NonNull
    public String getBusinessConditions() {
        return businessConditions;
    }

    @NonNull
    public String getSurveyIndustryNo() {
        return surveyIndustryNo;
    }

    @NonNull
    public String getIssuanceDate() {
        return issuanceDate;
    }

    @NonNull
    public String getExpirationDate() {
        return expirationDate;
    }

    @NonNull
    public String getSurveyEngineer() {
        return surveyEngineer;
    }

    @NonNull
    public String getSurveyor() {
        return surveyor;
    }

    public void setUnifiedBusinessNo(@NonNull String unifiedBusinessNo) {
        this.unifiedBusinessNo = unifiedBusinessNo;
    }

    public void setSurveyIndustry(@NonNull String surveyIndustry) {
        this.surveyIndustry = surveyIndustry;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public void setTel(@NonNull String tel) {
        this.tel = tel;
    }

    public void setBusinessScope(@NonNull String businessScope) {
        this.businessScope = businessScope;
    }

    public void setBusinessConditions(@NonNull String businessConditions) {
        this.businessConditions = businessConditions;
    }

    public void setSurveyIndustryNo(@NonNull String surveyIndustryNo) {
        this.surveyIndustryNo = surveyIndustryNo;
    }

    public void setIssuanceDate(@NonNull String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public void setExpirationDate(@NonNull String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setSurveyEngineer(@NonNull String surveyEngineer) {
        this.surveyEngineer = surveyEngineer;
    }

    public void setSurveyor(@NonNull String surveyor) {
        this.surveyor = surveyor;
    }
}
