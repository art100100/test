package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Planets extends Body {
    Body pBody;
    public Planets(Body tBody, Texture tImage, int tDist, int tSd) {
        super(tBody.bCenter);
        pBody = new Body();
        pBody = tBody;
        bImage = tImage;
        distance = tDist;
        speed = tSd;
        updatePos();
    }

    @Override
    public void dispose() {
        super.dispose();
        pBody.dispose();
    }
}
