package smsdemo.sdbi.com.a157;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ThirdActivity extends AppCompatActivity {
    private MyCountDownTimer mc;
    private TextView tv;
    private int i=0,s=0;
    private ImageView mouse;
    private Handler handler;
    public int[][] position=new int[][] {{40,320},{245,320},{440,320},{150,510},{350,510},{30,710},{240,710},{440,710}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.third_activity);
        mouse= (ImageView) findViewById(R.id.imageView3);
        mouse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);
                i++;
                Toast.makeText(ThirdActivity.this,"打到 [ "+i+" ]只地鼠! ",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int index=0;
                while(!Thread.currentThread().isInterrupted()){
                    index=new Random().nextInt(position.length);
                    Message message=handler.obtainMessage();
                    message.arg1=index;
                    message.what=0x101;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(new Random().nextInt(50)+50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index=0;
                if (msg.what == 0x101){
                    index=msg.arg1;
                    mouse.setX(position[index][0]);
                    mouse.setY(position[index][1]);
                    mouse.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        View view = findViewById(R.id.f3);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
//        alphaAnimation.setDuration(3000);
//        view.startAnimation(alphaAnimation);
        tv= (TextView) findViewById(R.id.timer3);
        mc = new MyCountDownTimer(20000, 1000);
        mc.start();
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,dialogActivity.class);
////                intent.putExtra("sum",s);
//                startActivity(intent);
//            }
//        });
        new Handler().postDelayed(new Runnable() {//以下是核心代码
            @Override
            public void run() {
                //从启动动画ui跳转到主ui
                s=s+i;
                Intent intent = new Intent(ThirdActivity.this,dialogActivity.class);
                intent.putExtra("sum",s);
                startActivity(intent);
                // 结束当前启动动画的界面
                ThirdActivity.this.finish();
            }
        }, 20000);    //，设置动画的显示时间，单位为毫秒
    }

    private Handler handler1 = new Handler();
    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            tv.setText("游戏结束!");
        }

        public void onTick(long millisUntilFinished) {
            tv.setText( "剩余游戏时间"+millisUntilFinished / 1000 + "秒");
        }
    }

}


