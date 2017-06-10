package micheal.egypt.android.jobexperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class All_formsActivity extends AppCompatActivity {

    private RecyclerView mFormList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_forms);
        setTitle("Forms");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forms");

        mFormList = (RecyclerView) findViewById(R.id.form_list);
        mFormList.setHasFixedSize(true);
        mFormList.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Form,FormViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Form, FormViewHolder>(
                Form.class,
                R.layout.form_row,
                FormViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(FormViewHolder viewHolder, Form model, int position) {

                viewHolder.setName("Name: "+ model.getName());
                viewHolder.setEmail("Email: "+ model.getEmail());
                viewHolder.setMobile("Mobile: "+ model.getMobile());
                viewHolder.setCategorie("Categorie: " + model.getCategorie());
                viewHolder.setExperience("Experience: " + model.getExperience() + " years");
            }
        };

        mFormList.setAdapter(firebaseRecyclerAdapter);





    }


    public static class FormViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public FormViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
            TextView form_name = (TextView) mView.findViewById(R.id.form_username);
            form_name.setText(name);
        }

        public void setEmail (String email){
            TextView form_email = (TextView) mView.findViewById(R.id.form_email);
            form_email.setText(email);
        }

        public void setMobile (String mobile){
            TextView form_mobile = (TextView) mView.findViewById(R.id.form_mobile);
            form_mobile.setText(mobile);
        }

        public void setCategorie ( String categorie){
            TextView form_categorie = (TextView) mView.findViewById(R.id.form_categorie);
            form_categorie.setText(categorie);
        }

        public void setExperience (String experience){
            TextView form_experience = (TextView) mView.findViewById(R.id.form_experience);
            form_experience.setText(experience);
        }
    }
}
