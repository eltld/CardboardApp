package com.example.fabio.cardboardpb.Manager;

import android.widget.ImageView;

import com.example.fabio.cardboardpb.Manager.Enum.Eye;

/**
 * Created by matteo on 05/02/2015.
 */
public class PenalizationManager implements PenalizationManagerInterface {

    private Eye eye;
    private ImageView enemyLeftLane1Id0;
    private ImageView enemyRightLane1Id0;
    private ImageView enemyLeftLane2Id0;
    private ImageView enemyRightLane2Id0;
    private ImageView enemyLeftLane3Id0;
    private ImageView enemyRightLane3Id0;

    /**
     *
     * @param enemyLeftLane1Id0
     * @param enemyRightLane1Id0
     * @param enemyLeftLane2Id0
     * @param enemyRightLane2Id0
     * @param enemyLeftLane3Id0
     * @param enemyRightLane3Id0
     */
    public PenalizationManager(ImageView enemyLeftLane1Id0, ImageView enemyLeftLane2Id0,ImageView enemyLeftLane3Id0, ImageView enemyRightLane1Id0,  ImageView enemyRightLane2Id0,
                                ImageView enemyRightLane3Id0) {
        this.enemyLeftLane1Id0 = enemyLeftLane1Id0;
        this.enemyRightLane1Id0 = enemyRightLane1Id0;
        this.enemyLeftLane2Id0 = enemyLeftLane2Id0;
        this.enemyRightLane2Id0 = enemyRightLane2Id0;
        this.enemyLeftLane3Id0 = enemyLeftLane3Id0;
        this.enemyRightLane3Id0 = enemyRightLane3Id0;
    }

    @Override
    public void penalize(Eye eye) {
        switch(eye){
            case LEFT_EYE:{
                enemyLeftLane1Id0.setAlpha(50);
                enemyLeftLane2Id0.setAlpha(50);
                enemyLeftLane3Id0.setAlpha(50);

            }break;
            case RIGHT_EYE:{
                enemyRightLane1Id0.setAlpha(50);
                enemyRightLane2Id0.setAlpha(50);
                enemyRightLane3Id0.setAlpha(50);
            }break;
            default:{}break;
        }

    }
}