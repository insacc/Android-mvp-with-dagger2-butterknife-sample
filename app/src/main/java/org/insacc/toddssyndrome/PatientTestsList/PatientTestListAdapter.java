package org.insacc.toddssyndrome.PatientTestsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.insacc.toddssyndrome.Model.PatientTest;
import org.insacc.toddssyndrome.R;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 * Recycler view adapter class which describes how the list of the syndrome tests should be displayed.
 */

public class PatientTestListAdapter extends RecyclerView.Adapter<PatientTestListAdapter.TestListViewHolder> {

    private List<PatientTest> mPatientTestList;

    private Context mContext;

    public PatientTestListAdapter(List<PatientTest> patientTests, Context context) {
        this.mPatientTestList = patientTests;
        this.mContext = context;

    }

    public void updateList(List<PatientTest> patientTests) {
        this.mPatientTestList = patientTests;
        notifyDataSetChanged();

    }

    @Override
    public TestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_test_item, parent, false);
        return new TestListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(TestListViewHolder holder, int position) {
        PatientTest patientTest = mPatientTestList.get(position);

        holder.mTestAge.setText(String.valueOf(patientTest.getAge()));
        holder.mTestDrug.setText(patientTest.isUsedHallucinogenicDrugs() == 1 ?
                mContext.getString(R.string.yes_label) : mContext.getString(R.string.no_label));
        holder.mTestMigraine.setText(patientTest.hasMigraine() == 1 ?
                mContext.getString(R.string.yes_label) : mContext.getString(R.string.no_label));

        holder.mTestResult.setText(String.valueOf(patientTest.getTestResult()) + " %");
    }

    @Override
    public int getItemCount() {
        return mPatientTestList.size();
    }

    public static class TestListViewHolder extends RecyclerView.ViewHolder {

        private TextView mTestAge, mTestDrug, mTestMigraine, mTestResult;

        public TestListViewHolder(View itemView) {
            super(itemView);
            mTestAge = (TextView) itemView.findViewById(R.id.patient_test_age);
            mTestDrug = (TextView) itemView.findViewById(R.id.patient_test_drug);
            mTestMigraine = (TextView) itemView.findViewById(R.id.patient_test_migraine);
            mTestResult = (TextView) itemView.findViewById(R.id.patient_test_item_result);
        }
    }
}
