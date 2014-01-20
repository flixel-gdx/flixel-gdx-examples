package org.flixel.examples.performance;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxU;

import com.badlogic.gdx.math.MathUtils;

public class PlayState extends FlxState
{
	public static final int ITERATIONS = 10000;
	public static final float FLOAT1 = -2450.50f;
	public static final float FLOAT2 = -500f;
	public static final float FLOAT3 = 500f;
	public static final float FLOAT4 = -2450.50f;
	public static final int sInt = 90;
	public long start;
	public long elapsed;
	public FlxText output;

	@Override
	public void create()
	{
		output = new FlxText(0, 0, 200);
		add(output);
		
		log("ITERATIONS " + ITERATIONS);
		log("==================================");
		
		testMathAbs();
		testMathATan2();
		testMathFloor();
		testMathRound();
		testMathPow();
		testMathMax();
		testMathMin();
		testMathRandom();		
	}
	
	public void startTimer()
	{		
		start = System.currentTimeMillis();
	}
	
	public void stopTimer()
	{
		elapsed = System.currentTimeMillis() - start;
		log("elapsed time = " + elapsed + "ms");
	    log((elapsed * 1000.0) / 1000000 + " ms per execution");
	    log("----------------------------------");
	}

	public void log(String string)
	{
		output.setText(output.getText() + string + "\n");
		FlxG.log(string);
	}
	
	@SuppressWarnings("unused")
	public void testMathAbs()
	{
		//dry run to give everything time to start up
		float result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT3);
		}
		
		log(" ");
		log("TestMathAbs");
		log("Math.abs");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.abs(FLOAT2);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT2);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT2);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT2);
			result = Math.abs(FLOAT3);
			result = Math.abs(FLOAT2);
			result = Math.abs(FLOAT3);
		}
		stopTimer();
		
		log("FlxU.abs");
		startTimer();
	    for(int i = ITERATIONS - 1; i >= 0; i--)
		{	    	
			result = FlxU.abs(FLOAT2);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT2);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT2);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT2);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT2);
			result = FlxU.abs(FLOAT3);
		}
	    stopTimer();
	}
	
	// TODO: math atan
	@SuppressWarnings("unused")
	public void testMathATan()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
			result = Math.atan(FLOAT1);
		}
	}
	
	@SuppressWarnings("unused")
	public void testMathATan2()
	{
		log(" ");
		log("TestMathATan2");
		log("Math.atan2");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
			result = Math.atan2(FLOAT1, FLOAT4);
		}
		stopTimer();
		
		log("Gdx.atan2");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
			result = MathUtils.atan2(FLOAT1, FLOAT4);
		}
		stopTimer();
	}

	// TODO: math sqrt
	@SuppressWarnings("unused")
	public void testMathSqrt()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
			result = Math.sqrt(FLOAT1);
		}
	}

	@SuppressWarnings("unused")
	public void testMathCeil()
	{
		log(" ");
		log("TestMathCeil");
		log("Math.ceil");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
			result = Math.ceil(FLOAT1);
		}
		stopTimer();
		
		log("FlxU.ceil");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
			result = FlxU.ceil(FLOAT1);
		}
		stopTimer();
		
		log("Gdx.ceil");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
			result = MathUtils.ceil(FLOAT1);
		}
		stopTimer();
	}

	@SuppressWarnings("unused")
	public void testMathRound()
	{
		log(" ");
		log("TestMathRound");
		log("Math.round");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
			result = Math.round(FLOAT1);
		}
		stopTimer();
		
		log("FlxU.round");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
			result = FlxU.round(FLOAT1);
		}
		stopTimer();
		
		log("Gdx.round");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
			result = MathUtils.round(FLOAT1);
		}
		stopTimer();
	}

	@SuppressWarnings("unused")
	public void testMathFloor()
	{
		log(" ");
		log("TestMathFloor");
		log("Math.floor");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
			result = Math.floor(FLOAT1);
		}
		stopTimer();
		
		log("FlxU.floor");		
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
			result = FlxU.floor(FLOAT1);
		}
		stopTimer();
		
		log("Gdx.floor");		
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
			result = MathUtils.floor(FLOAT1);
		}
		stopTimer();
	}

	@SuppressWarnings("unused")
	public void testMathPow()
	{
		log(" ");
		log("TestMathPow");
		log("Math.pow");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
			result = Math.pow(FLOAT1, FLOAT2);
		}
		stopTimer();
		
		startTimer();
		log("FlxU.pow");
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
			result = FlxU.pow(FLOAT1, FLOAT2);
		}
		stopTimer();
	}

	@SuppressWarnings("unused")
	public void testMathMax()
	{
		log(" ");
		log("TestMathMax");
		log("Math.max");
		double result;		
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
			result = Math.max(FLOAT1, FLOAT2);
		}
		stopTimer();
		
		log("FlxU.max");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
			result = FlxU.max(FLOAT1, FLOAT2);
		}
		stopTimer();		
	}

	@SuppressWarnings("unused")
	public void testMathMin()
	{
		log(" ");
		log("TestMathMin");
		log("Math.min");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
			result = Math.min(FLOAT1, FLOAT2);
		}
		stopTimer();
		
		log("FlxU.min");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
			result = FlxU.min(FLOAT1, FLOAT2);
		}
		stopTimer();
	}

	@SuppressWarnings("unused")
	public void testMathRandom()
	{
		log(" ");
		log("TestMathRandom");
		log("Math.random");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
			result = Math.random();
		}
		stopTimer();
		
		log("FlxG.random");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
			result = FlxG.random();
		}
		stopTimer();
		
		log("Gdx.random");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
			result = MathUtils.random();
		}
		stopTimer();
	}

	// TODO: Math IEEERemainder, not supported by GWT
	/*@SuppressWarnings("unused")
	public void testMathIEEERemainder()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
			result = Math.IEEEremainder(FLOAT1, FLOAT2);
		}
	}*/

	// Not tested, but it's obvious that 180 / PI is faster.
	@SuppressWarnings("unused")
	public void testMathToDegrees()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
			result = Math.toDegrees(FLOAT1);
		}
	}

	// Not tested, but it's obviously that PI / 180 is faster.
	@SuppressWarnings("unused")
	public void testMathToRadians()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
			result = Math.toRadians(FLOAT1);
		}
	}

}
