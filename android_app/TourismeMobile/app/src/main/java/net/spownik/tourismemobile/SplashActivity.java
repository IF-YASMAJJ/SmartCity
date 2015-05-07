package net.spownik.tourismemobile;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    // public static Activity ac;
    public static int nbrFin = 0;

    final static private long ONE_MINUTE = 60000;
    final static private long FIVE_MINUTES = ONE_MINUTE * 5;
    final static private long TWO_MINUTES = ONE_MINUTE * 2;
    final static private long TWENTY_SECONDS = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView image1 = (ImageView) findViewById(R.id.imagepremieresplash);
        final ImageView image2 = (ImageView) findViewById(R.id.imagedeuxiemesplash);
        image2.setVisibility(View.INVISIBLE);
        final ImageView image3 = (ImageView) findViewById(R.id.imagetroisieme);
        image3.setVisibility(View.INVISIBLE);

        Animation animation = AnimationUtils.loadAnimation(getBaseContext(),
                R.anim.anim_droit);
        final Animation animation2 = AnimationUtils.loadAnimation(
                getBaseContext(), R.anim.anim_droit);
        final Animation animation3 = AnimationUtils.loadAnimation(
                getBaseContext(), R.anim.anim_droit);

        image1.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                image2.startAnimation(animation2);
                image2.setVisibility(View.VISIBLE);
            }
        });
        animation2.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                image3.startAnimation(animation3);
                image3.setVisibility(View.VISIBLE);
            }
        });
        animation3.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), LieuxProchesActivity.class));
                finish();
            }
        });

    }

}
