package org.flixel.examples.breath;

import org.flixel.*;
import org.flixel.event.IFlxCollision;

public class Firefish extends FlxSprite {
    
    private String GlowImage = "examples/breath/data/pack:glow-light";
    
    private String FishImage = "examples/breath/data/pack:firefish";
    
    private FlxPoint start_point;
    private FlxObject destination;
    
    private int move_speed = 120;        

    public FlxSprite glow;
    public FlxSprite darkness;        

    public Player player;

    //private FlxPoint display_test_point;
    private FlxTilemap water;
    //private FlxTilemap walls;        
    
    public Firefish(int X, int Y, FlxSprite darkness, FlxTilemap water, FlxTilemap walls) {
        super(X, Y);

        loadGraphic(FishImage, false, true, 7, 4);

        scale.x = scale.y = (float) (0.2f + (Math.random() * 1.2f));
        setAlpha((float) (0.4f + (Math.random() * 0.6f)));
        
        start_point = new FlxPoint();
        destination = new FlxObject();
        
        start_point.x = X;
        start_point.y = Y;

        this.water = water;
        //this.walls = walls;
        
        this.darkness = darkness;

        //createGraphic(1,1,0xffffffff);

        glow = new FlxSprite(X,Y,GlowImage);
        glow.scale = new FlxPoint(2,2);
        glow.setAlpha(1);
        glow.blend = "screen";
        
        maxVelocity.x = maxVelocity.y = 200;

        //display_test_point = new FlxPoint();
        
        get_new_destination();
        
        drag.x = drag.y = 100;
    }

    @Override 
    public void update() {
        if(destination.x < this.x) {
            velocity.x -= move_speed * FlxG.elapsed;
        } else {
            velocity.x += move_speed * FlxG.elapsed;
        }

        if(destination.y < this.y) {
            velocity.y -= move_speed * FlxG.elapsed;
        } else {
            velocity.y += move_speed * FlxG.elapsed;
        }

        if(Math.abs(destination.y - this.y) < 4 &&
            Math.abs(destination.x - this.x) < 4) {
            
            get_new_destination();
        }

        // Animation
        if(velocity.x > 0) {
            setFacing(RIGHT);
        } else if(velocity.x < 0) {
            setFacing(LEFT);
        }
        
        super.update();
    }

    public void drawGlow() {
        /*
        getScreenXY(display_test_point);

        if(display_test_point.x > FlxG.width * 2 ||
            display_test_point.x < -FlxG.width ||
            display_test_point.y > FlxG.height * 2 ||
            display_test_point.y < -FlxG.height) {
            return;
        }
       
        FlxPoint firefly_point = new FlxPoint();
        
        getScreenXY(firefly_point);

        darkness.stamp(
            glow,
            (int)(firefly_point.x - (glow.width / 2f)),
            (int)(firefly_point.y - (glow.height/ 2f))
        );
        */
    	
        glow.x = (int)(x - (glow.width / 2f));
        glow.y = (int)(y - (glow.height/ 2f));
        
        glow.draw();
    }

    @Override
    public void kill() {
        glow.kill();
        super.kill();
    }

    public void get_new_destination() {
        boolean done = false;

        while(!done) {
            if(player != null) {
                destination = new FlxObject(
                    player.x + ((int)(Math.random() * 100) - 50),
                    player.y + ((int)(Math.random() * 100) - 50)
                );
            } else {
                destination = new FlxObject(
                    start_point.x + ((int)(Math.random() * 100) - 50),
                    start_point.y + ((int)(Math.random() * 100) - 50)
                );
            }

            move_speed = (int) (140 + (Math.random() * 20));

            if(water.overlaps(destination)) {
                done = true;
            }
        }
    }

/*
    @Override
    public void hitLeft(FlxObject Contact, float Velocity) {
        get_new_destination();
        super.hitLeft(Contact,Velocity);            
    }
    
    override public function hitTop(Contact:FlxObject, Velocity:Number):void {
        get_new_destination();
        super.hitTop(Contact,Velocity);
    }
    override public function hitBottom(Contact:FlxObject, Velocity:Number):void {
        get_new_destination();
        super.hitBottom(Contact,Velocity);
    }*/
    
    public static IFlxCollision callback = new IFlxCollision(){

		@Override
		public void callback(FlxObject Object1, FlxObject Object2) {
			((Firefish)Object1).get_new_destination();
		}
    	
    };
    
}