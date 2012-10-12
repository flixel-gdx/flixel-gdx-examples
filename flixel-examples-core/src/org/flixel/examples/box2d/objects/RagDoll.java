package org.flixel.examples.box2d.objects;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.plugin.flxbox2d.B2FlxV2;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 *
 * @author Ka Wing Chin
 */
public class RagDoll extends FlxGroup
{
	public RagDoll(float x, float y)
	{
		FixtureDef fixDef = new FixtureDef();
		fixDef.density = 1f;
		fixDef.friction = .4f;
		fixDef.restitution = .3f;
		
		// Head
		B2FlxCircle head = (B2FlxCircle) new B2FlxCircle(x + 2f, y - 1f, 12.5f)
			.setFixtureDef(fixDef)
			.create();
		head.body.applyLinearImpulse(new Vector2(FlxG.random() * 100 - 50,  FlxG.random() * 100 -50), 
				head.body.getWorldCenter());
		add(head);
		
		// 1st Torso
		fixDef.density = 1;
		fixDef.friction = .4f;
		fixDef.restitution = .1f;
		B2FlxBox torso1 = (B2FlxBox) new B2FlxBox(x, y + 28f, 30f, 20f)
			.setFixtureDef(fixDef)
			.create();
		add(torso1);
		
		// 2nd Torso
		B2FlxBox torso2 = (B2FlxBox) new B2FlxBox(x, y + 43f, 30f, 20f)
			.setFixtureDef(fixDef)
			.create();
		add(torso2);
		
		// 3rd Torso
		B2FlxBox torso3 = (B2FlxBox) new B2FlxBox(x, y + 58f, 30f, 20f)
			.setFixtureDef(fixDef)
			.create();
		add(torso3);
		
		// Upper Arm L
		B2FlxBox upperArmL = (B2FlxBox) new B2FlxBox(x-33f, y+24f, 36f, 13f)
			.setFixtureDef(fixDef)
			.create();
		add(upperArmL);
		
		// Upper Arm R
		B2FlxBox upperArmR = (B2FlxBox) new B2FlxBox(x+27f, y+24f, 36f, 13f)
			.setFixtureDef(fixDef)
			.create();
		add(upperArmR);
		
		// Lower Arm L
		B2FlxBox lowerArmL = (B2FlxBox) new B2FlxBox(x-60f, y+24f, 34f, 12f)
			.setFixtureDef(fixDef)
			.create();
		add(lowerArmL);
		
		// Lower Arm R
		B2FlxBox lowerArmR = (B2FlxBox) new B2FlxBox(x+57f, y+24f, 34f, 12f)
			.setFixtureDef(fixDef)
			.create();
		add(lowerArmR);
		
		// Upper Leg L
		B2FlxBox upperLegL = (B2FlxBox) new B2FlxBox(x, y+73f, 15f, 44f)
			.setFixtureDef(fixDef)
			.create();
		add(upperLegL);
		
		// Upper Leg R
		B2FlxBox upperLegR = (B2FlxBox) new B2FlxBox(x+15f, y+73f, 15f, 44f)
			.setFixtureDef(fixDef)
			.create();
		add(upperLegR);
		
		// Lower Leg L
		B2FlxBox lowerLegL = (B2FlxBox) new B2FlxBox(x+1f, y+110f, 12f, 40f)
			.setFixtureDef(fixDef)
			.create();
		add(lowerLegL);
		
		// Lower Leg R
		B2FlxBox lowerLegR = (B2FlxBox) new B2FlxBox(x+17f, y+110f, 12f, 40f)
			.setFixtureDef(fixDef)
			.create();
		add(lowerLegR);
		
		
		
		// JOINTS
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.enableLimit = true;
		
		// Head to shoulders
		new B2FlxRevoluteJoint(torso1, head, jointDef)
			.setLowerAngle(-40f)
			.setUpperAngle(40f)
			.setAnchorA(head.body.getWorldCenter())
			.create();
		
		// Upper arm to shoulders
		// L
		new B2FlxRevoluteJoint(torso1, upperArmL, jointDef)
			.setLowerAngle(-85f)
			.setUpperAngle(130f)
			.setAnchorA(new B2FlxV2(upperArmL.x+30f, torso1.y+2f))
			.create();
		
		// R
		new B2FlxRevoluteJoint(torso1, upperArmR, jointDef)
			.setLowerAngle(-130f)
			.setUpperAngle(85f)
			.setAnchorA(new B2FlxV2(upperArmR.x+6f, torso1.y+2f))
			.create();
		
		// Lower arm to upper arm
		// L
		new B2FlxRevoluteJoint(upperArmL, lowerArmL, jointDef)
			.setLowerAngle(-130f)
			.setUpperAngle(10f)
			.setAnchorA(new B2FlxV2(lowerArmL.x+32f, upperArmL.y+6f))
			.create();
		
		// R
		new B2FlxRevoluteJoint(upperArmR, lowerArmR, jointDef)
			.setLowerAngle(-10f)
			.setUpperAngle(130f)
			.setAnchorA(new B2FlxV2(lowerArmR.x+4f, upperArmR.y+6f))
			.create();
		
		// Shoulders/stomach
		new B2FlxRevoluteJoint(torso1, torso2, jointDef)
			.setLowerAngle(-15f)
			.setUpperAngle(15f)
			.setAnchorA(torso1.body.getWorldCenter())
			.create();
		
		// Stomach/hips
		new B2FlxRevoluteJoint(torso2, torso3, jointDef)
			.setLowerAngle(-15f)
			.setUpperAngle(15f)
			.setAnchorA(torso2.body.getWorldCenter())
			.create();
		
		// Torso to upper leg
		// L
		new B2FlxRevoluteJoint(torso3, upperLegL, jointDef)
			.setLowerAngle(-25f)
			.setUpperAngle(45f)
			.setAnchorA(new B2FlxV2(torso3.x + 8f, torso3.y + 20f))
			.create();
		
		// R
		new B2FlxRevoluteJoint(torso3, upperLegR, jointDef)
			.setLowerAngle(-25f)
			.setUpperAngle(45f)
			.setAnchorA(new B2FlxV2(torso3.x + 22f, torso3.y + 20f))
			.create();
		
		// Lower leg to upper leg
		// L
		new B2FlxRevoluteJoint(upperLegL, lowerLegL, jointDef)
			.setLowerAngle(-25f)
			.setUpperAngle(115f)
			.setAnchorA(new B2FlxV2(lowerLegL.x + 6f, lowerLegL.y + 8f))
			.create();
		
		// R
		new B2FlxRevoluteJoint(upperLegR, lowerLegR, jointDef)
			.setLowerAngle(-115f)
			.setUpperAngle(25f)
			.setAnchorA(new B2FlxV2(lowerLegR.x + 6f, lowerLegR.y + 8f))
			.create();
	}
}

