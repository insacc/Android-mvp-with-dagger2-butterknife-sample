package org.insacc.toddssyndrome.PatientTestResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.insacc.toddssyndrome.Config;
import org.insacc.toddssyndrome.DaggerModule.PatientTestResultModule.DaggerPatientTestResultComponent;
import org.insacc.toddssyndrome.DaggerModule.PatientTestResultModule.PatientTestResultModule;
import org.insacc.toddssyndrome.PatientList.PatientListActivity;
import org.insacc.toddssyndrome.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by can on 18.02.2017.
 * The user interface which simply displays the result of the syndrome test
 */

public class PatientTestResultActivity extends AppCompatActivity implements PatientTestResultContract.View {
    @Inject
    PatientTestResultContract.Presenter mPresenter;

    @BindView(R.id.patient_test_result_text)
    TextView mTestResult;

    @BindView(R.id.patient_test_result_done)
    Button mDoneButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_test_result_view);

        ButterKnife.bind(this);

        DaggerPatientTestResultComponent.builder().patientTestResultModule(new PatientTestResultModule(this))
                .build().inject(this);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.callPatientListView();
            }
        });

        mPresenter.loadTestResult();
    }

    @Override
    public void setTestResult() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            setResultTextView(intent.getExtras().getInt(Config.PATIENT_TEST_RESULT));
        }
    }

    @Override
    public void openPatientListUi() {
        this.finish();
    }

    private void setResultTextView(int testResult) {
        mTestResult.setText(String.valueOf(testResult) + " % ");
    }
}
