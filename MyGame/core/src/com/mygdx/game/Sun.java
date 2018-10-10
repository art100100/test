package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Sun {
    Texture bImage;
    Vector3 bPos;

    public Sun(Texture tImage, Vector3 tRec) {
        bImage = tImage;
        bPos = new Vector3();
        bPos.x = tRec.x;
        bPos.y = tRec.y;
    }

    public void dispose() {
        bImage.dispose();
    }
}
