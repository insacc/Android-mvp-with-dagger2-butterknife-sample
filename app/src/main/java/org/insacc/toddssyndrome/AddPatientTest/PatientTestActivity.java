package org.insacc.toddssyndrome.AddPatientTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.insacc.toddssyndrome.Config;
import org.insacc.toddssyndrome.DaggerModule.DatabaseHelperModule;
import org.insacc.toddssyndrome.DaggerModule.PatientTestModule.DaggerPatientTestComponent;
import org.insacc.toddssyndrome.DaggerModule.PatientTestModule.PatientTestModule;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;
import org.insacc.toddssyndrome.MyApp;
import org.insacc.toddssyndrome.PatientTestResult.PatientTestResultActivity;
import org.insacc.toddssyndrome.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by can on 18.02.2017.
 * Activity class to add syndrome test for a patient
 */

public class PatientTestActivity extends AppCompatActivity implements PatientTestContract.View {

    @Inject
    PatientTestContract.Presenter mPresenter;

    @BindView(R.id.gender_radio_group)
    RadioGroup mPatientGenderGroup;

    @BindView(R.id.migraine_radio_group)
    RadioGroup mPatientMigraineGroup;

    @BindView(R.id.drug_radio_group)
    RadioGroup mPatientDrugGroup;

    @BindView(R.id.patient_test_name)
    EditText mPatientName;

    @BindView(R.id.patient_test_first_name)
    EditText mPatientFirstName;

    @BindView(R.id.patient_test_done_button)
    Button mDoneButton;

    @BindView(R.id.patient_test_age)
    NumberPicker mAgePicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient_test_view);

        DaggerPatientTestComponent.builder().patientTestModule(new PatientTestModule(this))
                .appComponent(((MyApp) getApplicationContext()).getAppComponent())
                .databaseHelperModule(new DatabaseHelperModule())
                .build().inject(this);
        ButterKnife.bind(this);

        mAgePicker.setMinValue(0);
        mAgePicker.setMaxValue(90);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.evaluateAndSavePatientTest(collectPatientInfo(), collectTestInfo());
            }
        });
    }

    private Patient collectPatientInfo() {
        String firstName = mPatientFirstName.getText().toString();
        String lastName = mPatientName.getText().toString();

        if (firstName.length() == 0 || lastName.length() == 0) {
            return null;
        }
        Patient patient = new Patient(firstName, lastName);
        patient.setGender(getSelectedGender());
        return patient;
    }

    /**
     * Collects the selections of the user and returns the patient test object
     *
     * @return
     */
    private PatientTest collectTestInfo() {
        int age = mAgePicker.getValue();
        int selectedDrug = getSelectedDrug();
        int selectedGender = getSelectedGender();
        int selectedMigraine = getSelectedMigraine();
        PatientTest patientTest = new PatientTest(age, selectedMigraine, selectedGender, selectedDrug);

        return patientTest;
    }

    /**
     * @return the selection for the gender question.
     */
    private int getSelectedGender() {
        int checkedRadioButton = mPatientGenderGroup.getCheckedRadioButtonId();
        View radioButton = mPatientGenderGroup.findViewById(checkedRadioButton);
        int selectedBooking = mPatientGenderGroup.indexOfChild(radioButton);

        return selectedBooking;
    }

    /**
     * @return the selection for the drug question
     */
    private int getSelectedDrug() {
        int checkedRadioButton = mPatientDrugGroup.getCheckedRadioButtonId();
        View radioButton = mPatientDrugGroup.findViewById(checkedRadioButton);
        int selectedBooking = mPatientDrugGroup.indexOfChild(radioButton);

        return selectedBooking;
    }

    /**
     * @return the selection for the migraine question
     */
    private int getSelectedMigraine() {
        int checkedRadioButton = mPatientMigraineGroup.getCheckedRadioButtonId();
        View radioButton = mPatientMigraineGroup.findViewById(checkedRadioButton);
        int selectedBooking = mPatientMigraineGroup.indexOfChild(radioButton);

        return selectedBooking;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void openTestResultUi(int testResult) {
        Intent intent = new Intent(this, PatientTestResultActivity.class);
        intent.putExtra(Config.PATIENT_TEST_RESULT, testResult);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void displayFillAllFieldsMessage() {
        Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
    }
}
