package org.insacc.toddssyndrome.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by can on 18.02.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    //Database information
    private static final String DATABASE_NAME = "toddSyndrome.db";
    private static final int DATABASE_VERSION = 1;

    //Patient table fields
    public static final String PATIENTS_TABLE = "patients";
    public static final String PATIENT_NAME = "p_name";
    public static final String PATIENT_FIRST_NAME = "p_first_name";
    public static final String PATIENT_GENDER = "p_gender";


    private static final String CREATE_PATIENTS_TABLE = "CREATE TABLE " + PATIENTS_TABLE +
            " ( " + PATIENT_NAME + " TEXT, " + PATIENT_FIRST_NAME + " TEXT," + PATIENT_GENDER + " INT, " +
            " PRIMARY KEY( " + PATIENT_NAME + ", " + PATIENT_FIRST_NAME + "))";

    //Syndrome test results table fields
    public static final String TEST_RESULT_TABLE = "test_results";
    public static final String PATIENT_TEST_AGE = "t_age";
    public static final String PATIENT_TEST_ID = "t_pid";
    public static final String TEST_MIGRAINE_RESULT = "t_migraine";
    public static final String TEST_DRUG_RESULT = "t_drug_result";
    public static final String TEST_RESULT = "t_result";


    private static final String CREATE_TEST_RESULT_TABLE = "CREATE TABLE " + TEST_RESULT_TABLE + "("
            + PATIENT_TEST_AGE + " INT, " + TEST_MIGRAINE_RESULT + " INT, " + TEST_DRUG_RESULT
            + " INT, " + TEST_RESULT + " INT, "
            + PATIENT_TEST_ID + " BIGINT, FOREIGN KEY (" + PATIENT_TEST_ID + " ) REFERENCES "
            + PATIENTS_TABLE + "(ROWID))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PATIENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_TEST_RESULT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
