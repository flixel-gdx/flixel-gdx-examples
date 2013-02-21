package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.common.B2FlxV2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

/**
 *
 * @author Ka Wing Chin
 */
public class TestRaycast extends Test
{
	private B2FlxBox box;
	private RayCastAnyCallback callback;
	
	@Override
	public void create()
	{
		super.create();
		title.setText("Raycast");
		
		B2FlxB.world.setGravity(new Vector2(0, 0));
		
		FixtureDef fd = new FixtureDef();
		fd.density = 4;
		fd.friction = .4f;
		fd.restitution = .3f;
		
		box = (B2FlxBox) new B2FlxBox(10, 150, 60, 8)
			.setFixtureDef(fd)
			.setAngle(180)
			.setDraggable(true)
			.create();
		add(box);
		
		add(new B2FlxCircle(100, 100, 30)
			.setFixtureDef(fd)
			.setDraggable(true)
			.create());
		
//		B2FlxB.world.rayCast(cb, new Vector2(0, 0), new Vector2(0, 0));
		callback = new RayCastAnyCallback(); 
	}
	
	@Override
	public void update()
	{
		Vector2 p1 = box.body.getWorldPoint(new B2FlxV2(30.1f, 0));
		Vector2 p2 = box.body.getWorldPoint(new B2FlxV2(130.1f, 0));
		
		B2FlxB.world.rayCast(callback, p1, p2);
		// TODO: fix this part
//		ShapeRenderer laser = FlxG.flashGfx.getShapeRenderer();
//		laser.end();
//		laser.begin(ShapeType.Line);
//		laser.setColor(Color.RED);
//		laser.line(p1.x * 32, p1.y * 32, p2.x * 60, p2.y * 60);
//		laser.end();
		super.update();
	}
	
	@Override
	public void draw()
	{
		super.draw();
		
		
	}
	
	RayCastCallback cb = new RayCastCallback()
	{
		
		//private boolean hit;
		//private Vector2 point;
		//private Vector2 normal;

		@Override
		public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction)
		{
			FlxG.log(fixture);
			//Body body = fixture.getBody();
			//hit = true;
			//this.point = point;
			//this.normal = normal;
			return fraction;
		}
	};
}

class RayCastAnyCallback implements RayCastCallback {
    
	
    public RayCastAnyCallback()
	{
    	m_hit = false;
	}
   
   
    boolean m_hit;
    Vector2 m_point;
    Vector2 m_normal;
    
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction)
	{
		Body body = fixture.getBody();
        Object userData = body.getUserData();
        if (userData != null) {
                int index = (Integer) userData;
                if (index == 0) {
                        // filter
                        return -1f;
                }
        }
       
        m_hit = true;
        m_point = point;
        m_normal = normal;
        return 0f;
	}
};