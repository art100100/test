package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class Body {
    Texture bImage;
    Vector3 bPos;
    Vector3 bCenter;
    int distance;
    int speed;
    double angle;

    public Body(Vector3 tRec) {
        bPos = new Vector3();
        bPos.x = tRec.x;
        bPos.y = tRec.y;
        bCenter = new Vector3();
        bCenter.x = tRec.x;
        bCenter.y = tRec.y;
        distance = 0;
        speed = 0;
        angle = 0;
        updatePos();
    }

    public Body() {
    }

    public void updatePos() {
        angle += speed * Gdx.graphics.getDeltaTime() * 0.2f;
        if (angle > 2 * Math.PI)
            angle = 0;
        bPos.x = (float) (bCenter.x + Math.cos(angle) * distance);
        bPos.y = (float) (bCenter.y + Math.sin(angle) * distance);
    }

    public void dispose() {
        bImage.dispose();
    }
}
