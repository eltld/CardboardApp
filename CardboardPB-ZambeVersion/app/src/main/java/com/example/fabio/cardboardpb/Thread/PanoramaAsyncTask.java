package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.fabio.cardboardpb.Animation.AnimationPanorama;
import com.example.fabio.cardboardpb.Manager.Enum.Side;
import com.example.fabio.cardboardpb.Manager.PanoramaManager;

import java.util.ArrayList;

/**
 * Created by matteo on 03/04/2015.
 */
public class PanoramaAsyncTask extends AsyncTask<Void, Void, Void> {

    private ArrayList<ImageView> panoramaLeft1;
    private ArrayList<ImageView> panoramaLeft2;
    private ArrayList<ImageView> panoramaRight1;
    private ArrayList<ImageView> panoramaRight2;
    private ArrayList<ImageView> panoramaRightSky;
    private ArrayList<ImageView> panoramaLeftSky;
    private PanoramaManager panoramaManager;
    private Activity activity;
    private AnimationPanorama animationPanorama;

    /**
     * @param panoramaLeft1
     * @param panoramaLeft2
     * @param panoramaRight1
     * @param panoramaRight2
     * @param panoramaLeftSky
     * @param panoramaRightSky
     * @param activity
     */
    public PanoramaAsyncTask(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaLeft2, ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaRight2, ArrayList<ImageView> panoramaLeftSky, ArrayList<ImageView> panoramaRightSky, Activity activity) {
        this.panoramaLeft1 = panoramaLeft1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaLeftSky = panoramaLeftSky;
        this.panoramaRightSky = panoramaRightSky;
        this.activity = activity;
        animationPanorama = new AnimationPanorama();
        panoramaManager = new PanoramaManager();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    public Void doInBackground(Void... params) {
        panoramaManager.randomPanorama();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                animationPanorama.hideImage(panoramaLeft1.get(0));
                animationPanorama.hideImage(panoramaLeft1.get(1));
                animationPanorama.hideImage(panoramaLeft1.get(2));
                animationPanorama.hideImage(panoramaLeft2.get(0));
                animationPanorama.hideImage(panoramaLeft2.get(1));
                animationPanorama.hideImage(panoramaLeft2.get(2));
                animationPanorama.hideImage(panoramaRight1.get(0));
                animationPanorama.hideImage(panoramaRight1.get(1));
                animationPanorama.hideImage(panoramaRight1.get(2));
                animationPanorama.hideImage(panoramaRight2.get(0));
                animationPanorama.hideImage(panoramaRight2.get(1));
                animationPanorama.hideImage(panoramaRight2.get(2));

                if (panoramaManager.getSelectedSide().equals(Side.LEFT)) {
                    if (panoramaManager.getIdSubject() == 1) {
                        animationPanorama.showImage(panoramaLeft1.get(0));
                        animationPanorama.showImage(panoramaRight1.get(0));
                        animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(0), panoramaRight1.get(0));
                    }
                    if (panoramaManager.getIdSubject() == 2) {
                        animationPanorama.showImage(panoramaLeft1.get(1));
                        animationPanorama.showImage(panoramaRight1.get(1));
                        animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(1), panoramaRight1.get(1));
                    } else {
                        animationPanorama.showImage(panoramaLeft1.get(2));
                        animationPanorama.showImage(panoramaRight1.get(2));
                        animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(2), panoramaRight1.get(2));
                    }
                } else {
                    if (panoramaManager.getIdSubject() == 1) {
                        animationPanorama.showImage(panoramaLeft2.get(0));
                        animationPanorama.showImage(panoramaRight2.get(0));
                        animationPanorama.animatePanoramaRightView(panoramaLeft2.get(0), panoramaRight2.get(0));
                    }
                    if (panoramaManager.getIdSubject() == 1) {
                        animationPanorama.showImage(panoramaLeft2.get(1));
                        animationPanorama.showImage(panoramaRight2.get(1));
                        animationPanorama.animatePanoramaRightView(panoramaLeft2.get(1), panoramaRight2.get(1));
                    } else {
                        animationPanorama.showImage(panoramaLeft2.get(2));
                        animationPanorama.showImage(panoramaRight2.get(2));
                        animationPanorama.animatePanoramaRightView(panoramaLeft2.get(2), panoramaRight2.get(2));
                    }
                }

                animationPanorama.animatePanoramaCloud(panoramaLeftSky.get(0), panoramaRightSky.get(0));
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

    }

}