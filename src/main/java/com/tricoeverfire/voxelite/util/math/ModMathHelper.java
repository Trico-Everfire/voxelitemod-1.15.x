package com.tricoeverfire.voxelite.util.math;

import java.awt.Color;
import java.util.Random;

public class ModMathHelper {

	
	
	public static float GetRandomFloatBetween(float high, float low) {
		
		Random r = new Random();
		return (float) r.nextInt((int) (high-low)) + low;
	}
	
	public static float[] ParticleColorFromHex(int hex)
	{
		
		Color color = new Color(hex);
		float[] Colors = color.getColorComponents(null);
		
		float r = Colors[0];
		float g = Colors[1];
		float b = Colors[2];


		return new float[] {r,g,b};
	}
	
	public static float[] ParticleColorFromRGB(float Red, float Green, float Blue)
	{

		float r = Red / 255;
		float g = Green / 255;
		float b = Blue / 255;


		return new float[] {r,g,b};
	}
	

	
	
	
}
