package micheal.egypt.android.jobexperience;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Job Experience");
        Button button , button1;

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.WriteBtn);
        button1 = (Button) findViewById(R.id.ReadBtn);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.WriteBtn){
            intent = new Intent(MainActivity.this,FormActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ReadBtn){
            intent = new Intent(MainActivity.this,All_formsActivity.class);
            startActivity(intent);
        }


    }

}
