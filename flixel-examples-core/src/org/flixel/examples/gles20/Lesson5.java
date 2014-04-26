package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Lesson 5: Blurs
 * https://github.com/mattdesl/lwjgl-basics/wiki/ShaderLesson5
 * @author Ka Wing Chin
 */
public class Lesson5 extends FlxState
{
	private final String VERTEX = "attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
			"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			
			"uniform mat4 u_projTrans;\n" + 
			" \n" + 
			"varying vec4 vColor;\n" +
			"varying vec2 vTexCoord;\n" +
			
			"void main() {\n" +  
			"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
			"}";
	private final String FRAGMENT = "#ifdef GL_ES\n" + 
			"#define LOWP lowp\n" + 
			"precision mediump float;\n" + 
			"#else\n" + 
			"#define LOWP \n" + 
			"#endif\n" + 
			"varying LOWP vec4 vColor;\n" + 
			"varying vec2 vTexCoord;\n" + 
			"\n" + 
			"uniform sampler2D u_texture;\n" + 
			"uniform float resolution;\n" + 
			"uniform float radius;\n" + 
			"uniform vec2 dir;\n" + 
			"\n" + 
			"void main() {\n" + 
			"	vec4 sum = vec4(0.0);\n" + 
			"	vec2 tc = vTexCoord;\n" + 
			"	float blur = radius/resolution; \n" + 
			"    \n" + 
			"    float hstep = dir.x;\n" + 
			"    float vstep = dir.y;\n" + 
			"    \n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) * 0.05;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) * 0.09;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) * 0.12;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 1.0*blur*hstep, tc.y - 1.0*blur*vstep)) * 0.15;\n" + 
			"	\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.16;\n" + 
			"	\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 1.0*blur*hstep, tc.y + 1.0*blur*vstep)) * 0.15;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 2.0*blur*hstep, tc.y + 2.0*blur*vstep)) * 0.12;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y + 3.0*blur*vstep)) * 0.09;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y + 4.0*blur*vstep)) * 0.05;\n" + 
			"\n" + 
			"	gl_FragColor = vColor * vec4(sum.rgb, 1.0);\n" + 
			"}";
	private final String ImgSlider = "examples/gles20/lesson5/slider.png";
	private final String ImgTiles = "examples/gles20/lesson5/ptsans_00.png";
	private final int FBO_SIZE = 1024;
	private final float MAX_BLUR = 2f;

	private ShaderProgram blurShader;
	private FrameBuffer blurTargetA, blurTargetB;
	private FlxSprite slider, text;
	private TextureRegion fboRegion;

	@Override
	public void create()
	{
		FlxG.setBgColor(0xFFFFFFFF);

		slider = new FlxSprite(0, 0, ImgSlider);
		slider.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		add(slider);
		text = new FlxSprite(0, 0, ImgTiles);
		text.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		add(text);

		ShaderProgram.pedantic = false;

		blurShader = new ShaderProgram(VERTEX, FRAGMENT);
		FlxG.isShaderCompiled(blurShader);

		// setup uniforms for our shader
		blurShader.begin();
		blurShader.setUniformf("dir", 0f, 0f);
		blurShader.setUniformf("resolution", FBO_SIZE);
		blurShader.setUniformf("radius", 1f);
		blurShader.end();

		// create our FBOs
		blurTargetA = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		blurTargetB = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		fboRegion = new TextureRegion(blurTargetA.getColorBufferTexture());
		fboRegion.flip(false, true);
	}

	void resizeBatch(int width, int height)
	{
		FlxG.camera.getCamera().setToOrtho(false, width, height);
		FlxG.batch.setProjectionMatrix(FlxG.camera.getCamera().combined);
	}

	void renderEntities()
	{
		slider.draw();
		text.draw();
	}

	@Override
	public void draw()
	{
		//Start rendering to an offscreen color buffer
		blurTargetA.begin();
		
		//Clear the offscreen buffer with an opaque background
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//before rendering, ensure we are using the default shader
		FlxG.batch.setShader(null);
		
		//resize the batch projection matrix before drawing with it
		resizeBatch(FBO_SIZE, FBO_SIZE);
		
		//now we can start drawing...
		//FlxG.batch.begin();
		
		//draw our scene here
//		renderEntities();
		FlxG.batch.draw(slider.getTexture(), 0, 0);
		FlxG.batch.draw(text.getTexture(), slider.getTexture().getWidth()+5, 30);
		
		//finish rendering to the offscreen buffer
		FlxG.batch.flush();
		
		//finish rendering to the offscreen buffer
		blurTargetA.end();
		
		//now let's start blurring the offscreen image
		FlxG.batch.setShader(blurShader);
		
		//since we never called batch.end(), we should still be drawing
		//which means are blurShader should now be in use
		
		//ensure the direction is along the X-axis only
		blurShader.setUniformf("dir", 1f, 0f);
		
		//update blur amount based on touch input
		float mouseXAmt = Gdx.input.getX() / (float)Gdx.graphics.getWidth();
		blurShader.setUniformf("radius", mouseXAmt * MAX_BLUR);
		
		//our first blur pass goes to target B
		blurTargetB.begin();
		
		//we want to render FBO target A into target B
		fboRegion.setTexture(blurTargetA.getColorBufferTexture());
		
		//draw the scene to target B with a horizontal blur effect
		FlxG.batch.draw(fboRegion, 0, 0);
		
		//flush the batch before ending the FBO
		FlxG.batch.flush();
		
		//finish rendering target B
		blurTargetB.end();
		
		//now we can render to the screen using the vertical blur shader
		
		//update our projection matrix with the screen size
		resizeBatch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//update the blur only along Y-axis
		blurShader.setUniformf("dir", 0f, 1f);
		
		//update the Y-axis blur radius
		float mouseYAmt = Gdx.input.getY() / (float)Gdx.graphics.getHeight();
		blurShader.setUniformf("radius", mouseYAmt * MAX_BLUR);
		
		//draw target B to the screen with a vertical blur effect 
		fboRegion.setTexture(blurTargetB.getColorBufferTexture());
		FlxG.batch.draw(fboRegion, 0, 0);
		
		//reset to default shader without blurs 
		FlxG.batch.setShader(null);
		
		//draw FPS
		//fps.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 5, Gdx.graphics.getHeight()-5);
		
		//finally, end the batch since we have reached the end of the frame
		//FlxG.batch.end();
	}
}
