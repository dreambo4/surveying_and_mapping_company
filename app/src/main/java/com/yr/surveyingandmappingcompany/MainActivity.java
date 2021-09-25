package com.yr.surveyingandmappingcompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;
    private RecyclerView mRecyclerView;
    private Button mSearch;
    private TextView mRowNum, mNoDataMsg;
    private ProgressDialog mProgressDialog;
    private CompanyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InternetChecker checker = new InternetChecker();
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loading...");
        if (checker.isConnected(this)) mProgressDialog.show();

        mSearch = findViewById(R.id.search);
        mRecyclerView = findViewById(R.id.company_recycler_view);
        mRowNum = findViewById(R.id.row_num);
        mNoDataMsg = findViewById(R.id.no_data_message);

        mAdapter = new CompanyListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearch.setOnClickListener(searchListener);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.getCompanyList().observe(this, new Observer<List<Company>>() {
            @Override
            public void onChanged(List<Company> companies) {
                mAdapter.setCompanies(companies);
                int listSize = companies.size();
                mRowNum.setText(Integer.toString(listSize));
                if (listSize == 0) {
                    mNoDataMsg.setVisibility(View.VISIBLE);
                } else {
                    mProgressDialog.dismiss();
                    mNoDataMsg.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 搜尋按鈕監聽器
     */
    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mProgressDialog.show();
            EditText numberEditText = findViewById(R.id.number_editext);
            AlertDialog.Builder errMessage = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("數字格式有誤!")
                    .setPositiveButton("OK", null);

            try {
                int number = Integer.parseInt(numberEditText.getText().toString());
                if ((number % 1) != 0) {
                    errMessage.show();
                    mProgressDialog.dismiss();
                } else {
                    mMainViewModel.getCompanyList(number).observe(MainActivity.this,
                            new Observer<List<Company>>() {
                                @Override
                                public void onChanged(List<Company> companies) {
                                    mAdapter.setCompanies(companies);
                                    int listSize = companies.size();
                                    mRowNum.setText(Integer.toString(listSize));
                                    if (number == 0 || listSize == 0) {
                                        mNoDataMsg.setVisibility(View.VISIBLE);
                                    } else {
                                        mNoDataMsg.setVisibility(View.GONE);
                                    }
                                    mProgressDialog.dismiss();
                                }
                            });
                }
            } catch (NumberFormatException e) {
                errMessage.show();
                mProgressDialog.dismiss();
            }
        }
    };
}