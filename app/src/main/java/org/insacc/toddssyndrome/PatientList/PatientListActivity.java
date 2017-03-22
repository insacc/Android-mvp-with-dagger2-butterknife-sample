package org.insacc.toddssyndrome.PatientList;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.insacc.toddssyndrome.Config;
import org.insacc.toddssyndrome.DaggerModule.PatientListModule.DaggerPatientListComponent;
import org.insacc.toddssyndrome.DaggerModule.PatientListModule.PatientListModule;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.MyApp;
import org.insacc.toddssyndrome.AddPatientTest.PatientTestActivity;
import org.insacc.toddssyndrome.PatientTestsList.PatientTestsListActivity;
import org.insacc.toddssyndrome.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User interface which displays the list of the patients.
 */
public class PatientListActivity extends AppCompatActivity implements PatientsListContract.View {

    @Inject
    PatientsListContract.Presenter mPresenter;

    @BindView(R.id.patient_recycler_list)
    RecyclerView mPatientList;

    @BindView(R.id.add_new_test_fab)
    FloatingActionButton mAddTestFab;

    private PatientListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        ButterKnife.bind(this);
        DaggerPatientListComponent.builder().appComponent(((MyApp) getApplicationContext()).getAppComponent())
                .patientListModule(new PatientListModule(this)).build().inject(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListAdapter = new PatientListAdapter(this, new ArrayList<Patient>(), this);
        mPatientList.setLayoutManager(mLayoutManager);
        mPatientList.setAdapter(mListAdapter);

        mAddTestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.callAddTestView();
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mPatientList.getContext(),
                mLayoutManager.getOrientation());
        mPatientList.addItemDecoration(dividerItemDecoration);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadPatientList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void populatePatientList(List<Patient> patients) {
        mListAdapter.updateList(patients);
    }

    @Override
    public void openAddTestUi() {
        startActivity(new Intent(this, PatientTestActivity.class));
    }

    @Override
    public void onPatientClicked(long patientID) {
        mPresenter.callPatientTestDetail(patientID);
    }

    @Override
    public void openPatientTests(long patientID) {
        Intent intent = new Intent(this, PatientTestsListActivity.class);
        intent.putExtra(Config.PATIENT_ID, patientID);
        startActivity(intent);
    }
}
