package org.insacc.toddssyndrome.PatientList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.R;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 * Recyclerview adapter which describes how the patient list should be displayed.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {

    private List<Patient> mPatientList;

    private PatientsListContract.View mView;

    private Context mContext;

    public PatientListAdapter(PatientsListContract.View view, List<Patient> patients, Context context) {
        this.mPatientList = patients;
        this.mView = view;
        this.mContext = context;
    }

    public void updateList(List<Patient> patients) {
        this.mPatientList = patients;
        notifyDataSetChanged();
    }

    @Override
    public PatientListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_item, parent, false);

        return new PatientListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(PatientListViewHolder holder, int position) {

        final Patient patient = mPatientList.get(position);

        holder.mPatientName.setText(patient.getLastName());
        holder.mPatientFirstName.setText(patient.getFirstName());
        holder.mPatientGender.setText(patient.getGender() == 1 ? mContext.getString(R.string.female_label)
                : mContext.getString(R.string.male_label));

        holder.mPatientTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.onPatientClicked(patient.getPatientID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPatientList.size();
    }

    public static class PatientListViewHolder extends RecyclerView.ViewHolder {

        private TextView mPatientName, mPatientFirstName, mPatientGender;
        private Button mPatientTests;

        public PatientListViewHolder(View itemView) {
            super(itemView);

            mPatientFirstName = (TextView) itemView.findViewById(R.id.patient_list_item_first_name);
            mPatientName = (TextView) itemView.findViewById(R.id.patient_list_item_name);
            mPatientGender = (TextView) itemView.findViewById(R.id.patient_list_item_gender);
            mPatientTests = (Button) itemView.findViewById(R.id.patient_list_tests_button);
        }
    }
}
