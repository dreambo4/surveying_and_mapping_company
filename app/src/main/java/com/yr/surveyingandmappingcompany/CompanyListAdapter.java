package com.yr.surveyingandmappingcompany;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.CompanyViewHolder> {

    private final LayoutInflater mInflater;
    private List<Company> mCompanies;

    public CompanyListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.company_item, parent, false);
        return new CompanyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        if (mCompanies != null) {
            Company current = mCompanies.get(position);
            holder.mSurveyIndustry.setText(current.getSurveyIndustry());
            holder.mUnifiedBusinessNo.setText(current.getUnifiedBusinessNo());
            holder.mAddress.setText(current.getAddress());
            holder.mTel.setText(current.getTel());
            holder.mBusinessScope.setText(current.getBusinessScope());
            holder.mBusinessConditions.setText(current.getBusinessConditions());
            holder.mSurveyIndustryNo.setText(current.getSurveyIndustryNo());
            holder.mIssuanceDate.setText(current.getIssuanceDate());
            holder.mExpirationDate.setText(current.getExpirationDate());
            holder.mSurveyEngineer.setText(current.getSurveyEngineer());
            holder.mSurveyor.setText(current.getSurveyor());
        }
    }

    void setCompanies(List<Company> companies) {
        mCompanies = companies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCompanies != null)
            return mCompanies.size();
        else return 0;
    }

    static class CompanyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mSurveyIndustry, mUnifiedBusinessNo, mAddress, mTel, mBusinessScope,
                mBusinessConditions, mSurveyIndustryNo, mIssuanceDate, mExpirationDate, mSurveyEngineer, mSurveyor;

        private CompanyViewHolder(View itemView) {
            super(itemView);
            mSurveyIndustry = itemView.findViewById(R.id.survey_industry);
            mUnifiedBusinessNo = itemView.findViewById(R.id.unified_business_no);
            mAddress = itemView.findViewById(R.id.address);
            mTel = itemView.findViewById(R.id.tel);
            mBusinessScope = itemView.findViewById(R.id.business_scope);
            mBusinessConditions = itemView.findViewById(R.id.business_conditions);
            mSurveyIndustryNo = itemView.findViewById(R.id.survey_industry_no);
            mIssuanceDate = itemView.findViewById(R.id.issuance_date);
            mExpirationDate = itemView.findViewById(R.id.expiration_date);
            mSurveyEngineer = itemView.findViewById(R.id.survey_engineer);
            mSurveyor = itemView.findViewById(R.id.surveyor);
        }
    }
}
