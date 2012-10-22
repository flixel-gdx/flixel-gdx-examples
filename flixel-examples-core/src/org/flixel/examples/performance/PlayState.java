package org.flixel.examples.performance;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxU;

import com.badlogic.gdx.math.MathUtils;

public class PlayState extends FlxState
{
	public static final int ITERATIONS = 10000000;
	public static final float FLOAT1 = -2450.50f;
	public static final float FLOAT2 = -500f;
	public static final float FLOAT3 = 500f;
	public static final float FLOAT4 = -2450.50f;
	public static final int sInt = 90;
	public long start;
	public long elapsed;

	@Override
	public void create()
	{
		FlxG.log("ITERATIONS " + ITERATIONS);
		FlxG.log("==================================");
		
		testMathAbs();
		testMathSin();
		testMathCos();
		testMathASin();
		testMathACos();
		testMathATan2();
		testMathLog();
		testMathFloor();
		testMathRound();
		testMathExp();
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
		FlxG.log("elapsed time = " + elapsed + "ms");
	    FlxG.log((elapsed * 1000.0) / 1000000 + " ms per execution");
	    FlxG.log("----------------------------------");
	}

	public void testMathAbs()
	{
		FlxG.log(" ");
		FlxG.log("TestMathAbs");
		FlxG.log("Math.abs");
		float result;
		startTimer();
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
		stopTimer();
		
		FlxG.log("FlxU.abs");
		startTimer();
	    for(int i = ITERATIONS - 1; i >= 0; i--)
		{	    	
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
			result = FlxU.abs(FLOAT3);
		}
	    stopTimer();
	}

	public void testMathSin()
	{
		FlxG.log(" ");
		FlxG.log("TestMathSin");
		FlxG.log("Math.sin");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
			result = Math.sin(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("FlxU.sin");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
			result = FlxU.sin(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("Gdx.sin");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
			result = MathUtils.sin(FLOAT1);
		}
		stopTimer();
	}

	public void testMathCos()
	{
		FlxG.log(" ");
		FlxG.log("TestMathCos");
		FlxG.log("Math.cos");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
			result = Math.cos(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("FlxU.cos");		
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
			result = FlxU.cos(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("Gdx.cos");		
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
			result = MathUtils.cos(FLOAT1);
		}
		stopTimer();
	}

	// TODO: math tan
	public void testMathTan()
	{
		double result;
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
			result = Math.tan(FLOAT1);
		}
	}

	public void testMathASin()
	{
		FlxG.log(" ");
		FlxG.log("TestMathASin");
		FlxG.log("Math.asin");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
			result = Math.asin(FLOAT4);
		}
		stopTimer();
		
		FlxG.log("FlxU.asin");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
			result = FlxU.asin(FLOAT4);
		}
		stopTimer();
	}

	public void testMathACos()
	{
		FlxG.log(" ");
		FlxG.log("TestMathACos");
		FlxG.log("Math.acos");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
			result = Math.acos(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("FlxU.acos");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
			result = FlxU.acos(FLOAT1);
		}
		stopTimer();
	}
	
	// TODO: math atan
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
	
	public void testMathATan2()
	{
		FlxG.log(" ");
		FlxG.log("TestMathATan2");
		FlxG.log("Math.atan2");
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
		
		FlxG.log("Gdx.atan2");
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

	public void testMathLog()
	{
		FlxG.log(" ");
		FlxG.log("TestMathLog");
		FlxG.log("Math.log");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
			result = Math.log(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("FlxU.log");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
			result = FlxU.log(FLOAT1);
		}
		stopTimer();
	}

	// TODO: math sqrt
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

	public void testMathCeil()
	{
		FlxG.log(" ");
		FlxG.log("TestMathCeil");
		FlxG.log("Math.ceil");
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
		
		FlxG.log("FlxU.ceil");
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
		
		FlxG.log("Gdx.ceil");
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

	public void testMathRound()
	{
		FlxG.log(" ");
		FlxG.log("TestMathRound");
		FlxG.log("Math.round");
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
		
		FlxG.log("FlxU.round");
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
		
		FlxG.log("Gdx.round");
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

	public void testMathFloor()
	{
		FlxG.log(" ");
		FlxG.log("TestMathFloor");
		FlxG.log("Math.floor");
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
		
		FlxG.log("FlxU.floor");		
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
		
		FlxG.log("Gdx.floor");		
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

	public void testMathExp()
	{
		FlxG.log(" ");
		FlxG.log("TestMathExp");
		FlxG.log("Math.exp");
		double result;
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
			result = Math.exp(FLOAT1);
		}
		stopTimer();
		
		FlxG.log("FlxU.exp");
		startTimer();
		for(int i = ITERATIONS - 1; i >= 0; i--)
		{
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
			result = FlxU.exp(FLOAT1);
		}
		stopTimer();
	}

	public void testMathPow()
	{
		FlxG.log(" ");
		FlxG.log("TestMathPow");
		FlxG.log("Math.pow");
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
		FlxG.log("FlxU.pow");
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

	public void testMathMax()
	{
		FlxG.log(" ");
		FlxG.log("TestMathMax");
		FlxG.log("Math.max");
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
		
		FlxG.log("FlxU.max");
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

	public void testMathMin()
	{
		FlxG.log(" ");
		FlxG.log("TestMathMin");
		FlxG.log("Math.min");
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
		
		FlxG.log("FlxU.min");
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

	public void testMathRandom()
	{
		FlxG.log(" ");
		FlxG.log("TestMathRandom");
		FlxG.log("Math.random");
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
		
		FlxG.log("FlxG.random");
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
		
		FlxG.log("Gdx.random");
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

	// TODO: Math IEEERemainder
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
	}

	// Not tested, but it's obviously that 180 / PI is faster.
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
