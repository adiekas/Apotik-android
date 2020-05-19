package apotikku.hummasoft.com.apotikku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    TextView screen1,screen2,screen3,screen4,screen5,screen6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        screen1=(TextView)findViewById(R.id.screen1);
        screen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        screen2=(TextView)findViewById(R.id.screen2);
        screen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        screen3=(TextView)findViewById(R.id.screen3);

        screen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,PropertyDetailsActivity.class);
                startActivity(intent);
            }
        });

        screen4=(TextView)findViewById(R.id.screen4);

        screen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,GaleriActivity.class);
                startActivity(intent);
            }
        });

        screen5=(TextView)findViewById(R.id.screen5);

        screen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

//        screen6=(TextView)findViewById(R.id.screen6);
//
//        screen6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(FirstActivity.this,GaleriActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
