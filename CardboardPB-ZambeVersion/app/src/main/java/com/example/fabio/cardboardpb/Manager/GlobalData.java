package com.example.fabio.cardboardpb.Manager;

import com.example.fabio.cardboardpb.Manager.Enum.Eye;

/**
 * Created by matteo on 03/02/2015.
 */
public class GlobalData {

    private int absolutePosition;
    private boolean isEnd1=false;
    private boolean isEnd2=false;
    private boolean isEnd3=false;
    private int life=3;
    private Eye eye;
    private int level=1;
    private int points=0;

    public int getAbsolutePosition() {
        return absolutePosition;
    }

    public boolean isEnd1() {
        return isEnd1;
    }

    public boolean isEnd2() {
        return isEnd2;
    }

    public boolean isEnd3() {
        return isEnd3;
    }

    public Eye getEye() {
        return eye;
    }

    public void setAbsolutePosition(int absolutePosition) {
        this.absolutePosition = absolutePosition;
    }

    public void decreaseAbosolutePosition(){
        this.absolutePosition--;
    }

    public void increaseAbosolutePosition(){
        this.absolutePosition++;
    }

    public void setEnd1(boolean isEnd1) {
        this.isEnd1 = isEnd1;
    }

    public void setEnd2(boolean isEnd2) {
        this.isEnd2 = isEnd2;
    }

    public void setEnd3(boolean isEnd3) {
        this.isEnd3 = isEnd3;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public int getLevel() {
        return level;
    }

    public int getLife() {
        return life;
    }

    public void increasePoints(){
        points+=10;
    }

    public Integer getPoints(){
        Integer i=new Integer(points);
        return i;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel(){
        level++;
    }

    public void decreaseLife(){
        life--;
    }

}
