package micheal.egypt.android.jobexperience;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseForm;

    Intent intent;
    private ProgressDialog mProgress;

    private EditText mName;
    private EditText mEmail;
    private EditText mMobile;
    private EditText mCategorie;
    private EditText mExperience;
    private Button mSubmitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        setTitle("Form");
        mProgress = new ProgressDialog(this);
        mName = (EditText) findViewById(R.id.NameField);
        mEmail = (EditText) findViewById(R.id.EmailField);
        mMobile = (EditText) findViewById(R.id.MobileField);
        mCategorie = (EditText) findViewById(R.id.CategorieField);
        mExperience = (EditText) findViewById(R.id.ExperienceField);

        mSubmitBtn = (Button) findViewById(R.id.SubmitBtn);
        mSubmitBtn.setOnClickListener(this);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forms");

    }

    private void submitForm() {

        mProgress.setMessage("Posting Form...");
        final String name_value = mName.getText().toString().trim();
        final String email_value = mEmail.getText().toString().trim();
        final String mobile_value = mMobile.getText().toString().trim();
        final String categorie_value = mCategorie.getText().toString().trim();
        final String experience_value = mExperience.getText().toString().trim();

        if(!TextUtils.isEmpty(name_value) && !TextUtils.isEmpty(email_value) && !TextUtils.isEmpty(mobile_value) && !TextUtils.isEmpty(categorie_value) && !TextUtils.isEmpty(experience_value)){
            mProgress.show();
            mProgress.setCancelable(false);
            mProgress.setCanceledOnTouchOutside(false);
            mDatabaseForm = mDatabase.push();

            mDatabaseForm.child("name").setValue(name_value);
            mDatabaseForm.child("email").setValue(email_value);
            mDatabaseForm.child("mobile").setValue(mobile_value);
            mDatabaseForm.child("categorie").setValue(categorie_value);
            mDatabaseForm.child("experience").setValue(experience_value);

            mProgress.dismiss();
            Toast.makeText(getApplicationContext(),"Form has been submitted successfully",Toast.LENGTH_LONG).show();
            intent = new Intent(FormActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Error Form isn't Submitted ",Toast.LENGTH_LONG).show();

        }




    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SubmitBtn) {
            boolean error = false;

            if (mName.getText().toString().length() == 0) {
                mName.setError("Name is required");
                error = true;
            }

            if (mEmail.getText().toString().length() == 0) {
                mEmail.setError("A valid email is required");
                error = true;
            }

            if (mMobile.getText().toString().length() == 0) {
                mMobile.setError("A valid Mobile Number is required");
                error = true;
            }

            if (mCategorie.getText().toString().length() == 0) {
                mCategorie.setError("Categorie is required");
                error = true;
            }

            if (mExperience.getText().toString().length() == 0) {
                mExperience.setError("A valid Experience is required");
                error = true;
            }

            if (error == false) {
                submitForm();
            }
        }
    }
}


