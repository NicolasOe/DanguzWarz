package dangusgame.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor{

	TextureRegion region;
    public MyActor (TextureRegion tr, float x,float y) 
    {
        region = tr;
        setX(x);
        setY(y);
        setWidth(tr.getRegionWidth());
        setHeight(tr.getRegionHeight());
        
    }
    
    public MyActor (TextureRegion tr) 
    {
        region = tr;
        setWidth(tr.getRegionWidth());
        setHeight(tr.getRegionHeight());
        
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = this.getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
    public void setTexture(TextureRegion tr)
    {
    	region = tr;
    }
}
