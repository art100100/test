package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Moon extends Body {
    Body mPlanet;
    public Moon(Planets tBody, Texture tImage, int tDist, int tSd) {
        super(tBody.bCenter);
        mPlanet = new Body();
        mPlanet = tBody;
        bImage = tImage;
        distance = tDist;
        speed = tSd;
        updatePos();
    }

    @Override
    public void updatePos() {
        super.updatePos();
        if (mPlanet != null) {
        bCenter.x = mPlanet.bPos.x;
        bCenter.y = mPlanet.bPos.y;}
    }

    @Override
    public void dispose() {
        super.dispose();
        mPlanet.dispose();
    }
}
