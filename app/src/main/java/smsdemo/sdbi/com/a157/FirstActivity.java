package smsdemo.sdbi.com.a157;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/1/14.
 */

public class FirstActivity extends Activity implements View.OnClickListener{
    private Button startMainActivity,startSecondActivity,startActivityThridActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        startMainActivity=findViewById(R.id.startMainActivity);
        startSecondActivity=findViewById(R.id.startSecondActivity);
        startActivityThridActivity=findViewById(R.id.startThirdActivity);
        startMainActivity.setOnClickListener(this);
        startSecondActivity.setOnClickListener(this);
        startActivityThridActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startMainActivity:
                Intent intent1=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.startSecondActivity:
                Intent intent2=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent2);
                break;
            case R.id.startThirdActivity:
                Intent intent3=new Intent(FirstActivity.this,ThirdActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
