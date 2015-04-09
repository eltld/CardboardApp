package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Manager.Enum.Eye;
import com.example.fabio.cardboardpb.Manager.GlobalData;
import com.example.fabio.cardboardpb.Thread.AnimationBackgroundView;
import com.example.fabio.cardboardpb.Thread.AnimationLoopThread;
import com.example.fabio.cardboardpb.Thread.GameThread;
import com.example.fabio.cardboardpb.R;
import com.example.fabio.cardboardpb.Thread.PanoramaAsyncTask;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private int width;
    private int height;
    private static MainActivity instance;
    private Eye eye;
    private TimerTask timerTask;
    private Timer timer;

    public GlobalData globalData;
    private GameThread gameThread;
    private AnimationLoopThread glt;
    private AnimationBackgroundView backgroundViewLeft;
    private AnimationBackgroundView backgroundViewRight;
    private boolean isEndEnemyLane1 =false;
    private boolean isEndEnemyLane2 = false;
    private boolean isEndEnemyLane3 = false;
    RelativeLayout relativeLayoutLeft;
    RelativeLayout relativeLayoutRight;
    RelativeLayout relativeLayoutAnimationLeft;
    RelativeLayout relativeLayoutAnimationRight;
    FrameLayout frameLayoutLeft;
    FrameLayout frameLayoutRight;

    //Our car
    private ImageView carLeft;
    private ImageView carRight;

    //Enemies
    private ArrayList<ImageView> enemyLeftLane1= new ArrayList<ImageView>();
    private ArrayList<ImageView> enemyLeftLane2= new ArrayList<ImageView>();
    private ArrayList<ImageView> enemyLeftLane3= new ArrayList<ImageView>();
    private ArrayList<ImageView> enemyRightLane1= new ArrayList<ImageView>();
    private ArrayList<ImageView> enemyRightLane2= new ArrayList<ImageView>();
    private ArrayList<ImageView> enemyRightLane3= new ArrayList<ImageView>();


    private ArrayList<ImageView> panoramaLeft1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeft2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeftSky= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRightSky= new ArrayList<ImageView>();

    private ImageView target1;
    private ImageView target2;
    private ImageView target3;

    private TextView t1; //REMOVE THIS
    private TextView t2; //REMOVE THIS

    private TextView textLevelLeft;
    private TextView textLifeLeft;
    private TextView textPointsLeft;
    private TextView textLevelRight;
    private TextView textLifeRight;
    private TextView textPointsRight;

    private String id_user, id_doctor;

    private int leftCarPosition;
    private int rightCarPosition;
    private boolean running=true;

    private Activity activity;

    public MainActivity(){
        instance=this;
    }
    public static Context getContext() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        activity=this;
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        Intent intent=getIntent();

        globalData= new GlobalData();
        Bundle data = getIntent().getExtras();

        eye=(Eye) intent.getSerializableExtra("eye");

        id_user=(String) intent.getSerializableExtra("id_user");

        id_doctor=(String) intent.getSerializableExtra("id_doctor");

        carLeft = (ImageView) findViewById(R.id.imageViewMyCarLeft);

        carRight = (ImageView) findViewById(R.id.imageViewMyCarRight);


        enemyLeftLane1.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane1Id0));
        enemyLeftLane1.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane1Id1));
        enemyLeftLane1.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane1Id2));
        enemyRightLane1.add((ImageView) findViewById(R.id.imageViewEnemyRightLane1Id0));
        enemyRightLane1.add((ImageView) findViewById(R.id.imageViewEnemyRightLane1Id1));
        enemyRightLane1.add((ImageView) findViewById(R.id.imageViewEnemyRightLane1Id2));

        enemyLeftLane2.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane2Id0));
        enemyLeftLane2.add( (ImageView) findViewById(R.id.imageViewEnemyLeftLane2Id1));
        enemyLeftLane2.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane2Id2));
        enemyRightLane2.add( (ImageView) findViewById(R.id.imageViewEnemyRightLane2Id0));
        enemyRightLane2.add( (ImageView) findViewById(R.id.imageViewEnemyRightLane2Id1));
        enemyRightLane2.add( (ImageView) findViewById(R.id.imageViewEnemyRightLane2Id2));


        enemyLeftLane3.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane3Id0));
        enemyLeftLane3.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane3Id1));
        enemyLeftLane3.add((ImageView) findViewById(R.id.imageViewEnemyLeftLane3Id2));

        enemyRightLane3.add((ImageView) findViewById(R.id.imageViewEnemyRightLane3Id0));
        enemyRightLane3.add((ImageView) findViewById(R.id.imageViewEnemyRightLane3Id1));
        enemyRightLane3.add((ImageView) findViewById(R.id.imageViewEnemyRightLane3Id2));


        panoramaLeft1.add((ImageView) findViewById(R.id.tree_left1));
        panoramaLeft2.add((ImageView) findViewById(R.id.tree_left2));
        panoramaRight1.add((ImageView) findViewById(R.id.tree_right1));
        panoramaRight2.add((ImageView) findViewById(R.id.tree_right2));
        panoramaLeft1.add((ImageView) findViewById(R.id.signal_left1));
        panoramaLeft2.add((ImageView) findViewById(R.id.signal_left2));
        panoramaRight1.add((ImageView) findViewById(R.id.signal_right1));
        panoramaRight2.add((ImageView) findViewById(R.id.signal_right2));
        panoramaLeft1.add((ImageView) findViewById(R.id.house_left1));
        panoramaLeft2.add((ImageView) findViewById(R.id.house_left2));
        panoramaRight1.add((ImageView) findViewById(R.id.house_right1));
        panoramaRight2.add((ImageView) findViewById(R.id.house_right2));


        panoramaLeftSky.add((ImageView)findViewById(R.id.cloud_left));
        panoramaRightSky.add((ImageView)findViewById(R.id.cloud_right));

        textLevelLeft=(TextView) findViewById(R.id.textViewLevelLeft);
        textLifeLeft = (TextView)findViewById(R.id.textViewLifeLeft);
        textPointsLeft = (TextView)findViewById(R.id.textViewScoreLeft);

        textLevelRight=(TextView) findViewById(R.id.textViewLevelRight);
        textLifeRight = (TextView)findViewById(R.id.textViewLifeRight);
        textPointsRight = (TextView)findViewById(R.id.textViewScoreRight);


        target1=(ImageView) findViewById(R.id.target1);
        target2=(ImageView) findViewById(R.id.target2);
        target3=(ImageView) findViewById(R.id.target3);

        globalData.setAbsolutePosition(2);

        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView6);

        //getCollision(animationEnemies);

        relativeLayoutLeft = (RelativeLayout)findViewById(R.id.relativeLayoutLeft);
        relativeLayoutRight = (RelativeLayout)findViewById(R.id.relativeLayoutRight);

        frameLayoutLeft = (FrameLayout)findViewById(R.id.frameLayoutGameOverLeft);
        frameLayoutRight = (FrameLayout)findViewById(R.id.frameLayoutGameOverRight);

        relativeLayoutAnimationLeft=(RelativeLayout)findViewById(R.id.relativeLayoutAnimationBackgroundLeft);
        relativeLayoutAnimationRight=(RelativeLayout)findViewById(R.id.relativeLayoutAnimationBackgroundRight);
        backgroundViewLeft=new AnimationBackgroundView(this);
        backgroundViewRight=new AnimationBackgroundView(this);
        relativeLayoutAnimationLeft.addView(backgroundViewLeft);
        relativeLayoutAnimationRight.addView(backgroundViewRight);

        gameThread=new GameThread(this,t1,t2,textLevelLeft,textLifeLeft, textPointsLeft,
                textLevelRight,textLifeRight, textPointsRight,
                enemyLeftLane1,enemyLeftLane2,enemyLeftLane3,enemyRightLane1,
                enemyRightLane2,enemyRightLane3,target1,target2,target3,globalData,eye,
                relativeLayoutAnimationLeft,relativeLayoutAnimationRight, width, height,id_user);


        gameThread.start();


        timerTask= new TimerTask() {
             @Override
             public void run() {
                 PanoramaAsyncTask p = new PanoramaAsyncTask(panoramaLeft1, panoramaLeft2, panoramaRight1,
                         panoramaRight2, panoramaLeftSky, panoramaRightSky, width, height);

                 p.execute();
             }
         };

        timer= new Timer();
        timer.schedule(timerTask,0,20000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * we want to kill the activity when it go on pause
     */
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_HEADSETHOOK:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key home press
                    playAgain();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    /**
     * handle the key + press event
     */
    private void volumeUp() {
        if(globalData.getLife()>0){
        if (globalData.getAbsolutePosition()> 1) {
            globalData.decreaseAbosolutePosition();
            leftCarPosition -= width*0.2;
            rightCarPosition -= width*0.2;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            globalData.setAbsolutePosition(1);
        }
        }
        else{ //only if globalData.getLife==0
            relativeLayoutLeft.removeView(frameLayoutLeft);
            relativeLayoutRight.removeView(frameLayoutRight);

            globalData.setLife(3);
            globalData.setScore(0);

            globalData.setRunnable(true);
            //GameManager newGame=new GameManager();

            //todo resettare gameManager


        }

        //detectCollision();
        /*
        new AlertDialog.Builder(this)
                .setTitle("test mode")
                .setMessage("key + pressed").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
            }
        }).show();
*/
    }


    //this method will be modify soon

    /**
     * handle the key - press event
     */
    private void volumeDown() {
        if(globalData.isRunnable()){
        if (globalData.getAbsolutePosition() < 3) {
            globalData.increaseAbosolutePosition();
            leftCarPosition += width*0.2;
            rightCarPosition += width*0.2;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            globalData.setAbsolutePosition(3);
        }
        }
       // detectCollision();

       /*
        new AlertDialog.Builder(this)
                .setTitle("test mode")
                .setMessage("key - pressed").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
          }
}).show();
*/
    }

    private void detectCollision() {

        //TODO far sparire macchine in caso di no collision e animazione in caso di scontro

        if(!(globalData.getAbsolutePosition()==1 && isEndEnemyLane1) &&
                !(globalData.getAbsolutePosition()==2 && isEndEnemyLane2) &&
                !(globalData.getAbsolutePosition()==3 && isEndEnemyLane3)){
            t1.setText("no collision");
        }

        if(globalData.getAbsolutePosition()==1 && isEndEnemyLane1){
            t1.setText("scontro su 1");
            //TODO Explosion animation on Line 1
        }

        if(globalData.getAbsolutePosition()==2 && isEndEnemyLane2){
            t1.setText("scontro su 2");
            //TODO Explosion animation on Line 2
        }

        if(globalData.getAbsolutePosition()==3 && isEndEnemyLane3){
            t1.setText("scontro su 3");
            //TODO Explosion animation on Line 3
        }
    }

    /**
     * handle the home button pression
     */
    private void playAgain() {

        Intent restart = new Intent(MainActivity.this, MainActivity.class);
        startActivity(restart);


    }






}

