package org.insacc.toddssyndrome.PatientTestsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.insacc.toddssyndrome.Config;
import org.insacc.toddssyndrome.DaggerModule.DatabaseHelperModule;
import org.insacc.toddssyndrome.DaggerModule.PatientTestsListModule.DaggerPatientTestsListComponent;
import org.insacc.toddssyndrome.DaggerModule.PatientTestsListModule.PatientTestsListModule;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;
import org.insacc.toddssyndrome.MyApp;
import org.insacc.toddssyndrome.PatientList.PatientListActivity;
import org.insacc.toddssyndrome.PatientList.PatientListAdapter;
import org.insacc.toddssyndrome.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by can on 18.02.2017.
 * The user interface which displays the list of  stored syndrome tests of a user whose id is passed
 * to this class
 */

public class PatientTestsListActivity extends AppCompatActivity implements PatientTestsListContract.View {

    @Inject
    PatientTestsListContract.Presenter mPresenter;

    @BindView(R.id.patient_test_recycler_list)
    RecyclerView mPatientTestsRecyclerView;

    @BindView(R.id.patient_test_list_name)
    TextView mPatientName;

    @BindView(R.id.patient_test_list_gender)
    TextView mPatientGender;

    @BindView(R.id.patient_test_list_toolbar)
    Toolbar mToolbar;

    private PatientTestListAdapter mListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_test_list_view);

        DaggerPatientTestsListComponent.builder().patientTestsListModule(new PatientTestsListModule(this))
                .appComponent((((MyApp) getApplicationContext()).getAppComponent()))
                .databaseHelperModule(new DatabaseHelperModule())
                .build().inject(this);

        ButterKnife.bind(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListAdapter = new PatientTestListAdapter(new ArrayList<PatientTest>(), this);
        mPatientTestsRecyclerView.setLayoutManager(mLayoutManager);
        mPatientTestsRecyclerView.setAdapter(mListAdapter);

        mPresenter.loadPatient(extractPatientID());

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.callPatientListUi();
            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mPatientTestsRecyclerView.getContext(), mLayoutManager.getOrientation());
        mPatientTestsRecyclerView.addItemDecoration(dividerItemDecoration);

    }


    private long extractPatientID() {
        if (getIntent().getExtras() != null) {
            return getIntent().getExtras().getLong(Config.PATIENT_ID);
        }

        return -1;
    }

    @Override
    public void populatePatient(Patient patient) {
        mListAdapter.updateList(patient.getPatientTest());
        setPatientInfos(patient);
    }

    private void setPatientInfos(Patient patientInfos) {
        mPatientName.setText(patientInfos.getFirstName() + " " + patientInfos.getLastName());
        mPatientGender.setText(patientInfos.getGender() == 1 ? getString(R.string.female_label)
                : getString(R.string.male_label));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void openPatientListUi() {
        this.finish();
    }
}
