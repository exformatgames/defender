package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.github.exformatgames.defender.Configurations;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

public class SpriteComponent implements Component {
	
	public Texture texture;

	public final Array<SpriteComponent> spriteComponentArray = new Array<>();

	static final int VERTEX_SIZE = 2 + 1 + 2;	
	public static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

	private float[] vertices = new float[SPRITE_SIZE];
	public final Color color = new Color(1, 1, 1, 1);	
	public float x, y;
	public float width, height;
	public float offsetX, offsetY;
	public float originX, originY;	
	public float rotation = 0;
	public float offsetRotation = 0;

	public float scaleX = 1, scaleY = 1;	

	public boolean dirty = true;
	
	private Rectangle bounds;

	public SpriteComponent init(TextureRegion region, float scl) {
		texture = region.getTexture();
		setUV(region);
		setColor(1, 1, 1, 1);		
		setSize((float) region.getRegionWidth() * scl, (float) region.getRegionHeight() * scl);
		setOrigin(width / 2, height / 2);
		setOffsetXY(0, 0);
		return this;
	}

	public SpriteComponent init(TextureRegion region) {
		return init(region, Configurations.SCL);
	}

	public SpriteComponent init(TextureRegion region, float scl, float offsetX, float offsetY) {
		texture = region.getTexture();
		setUV(region);
		setColor(1, 1, 1, 1);
		setSize(region.getRegionWidth() * scl, region.getRegionHeight() * scl);
		setOrigin(width / 2, height / 2);

		this.offsetX = offsetX;
		this.offsetY = offsetY;

		offsetRotation = 0;
		return this;
	}

	public SpriteComponent setSize(float width, float height) {
		this.width = width;		
		this.height = height; 		

		if (dirty) return this;

		if (rotation != 0 || scaleX != 1 || scaleY != 1) {			
			dirty = true;			
			return this;
		} 		

		float x2 = x + width;		
		float y2 = y + height;		
		float[] vertices = this.vertices;		

		vertices[X1] = x;		
		vertices[Y1] = y; 		
		vertices[X2] = x;		
		vertices[Y2] = y2; 		
		vertices[X3] = x2;		
		vertices[Y3] = y2; 		
		vertices[X4] = x2;		
		vertices[Y4] = y;

		return this;
	}

	public SpriteComponent setColor(float r, float g, float b, float a) {
		color.set(r, g, b, a);		
		float color = this.color.toFloatBits();		
		float[] vertices = this.vertices;		
		vertices[C1] = color;		
		vertices[C2] = color;		
		vertices[C3] = color;		
		vertices[C4] = color;

		return this;
	}

	public SpriteComponent setOrigin(float originX, float originY) {
		this.originX = originX;		
		this.originY = originY;		
		dirty = true;

		return this;
	}

	public SpriteComponent setOffsetXY(float offsetX, float offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;

		return this;
	}
	public SpriteComponent setUV(TextureRegion region){
		float[] vertices = SpriteComponent.this.vertices;		
		vertices[U1] = region.getU();		
		vertices[V1] = region.getV2(); 		
		vertices[U2] = region.getU();		
		vertices[V2] = region.getV(); 		
		vertices[U3] = region.getU2();		
		vertices[V3] = region.getV(); 		
		vertices[U4] = region.getU2();		
		vertices[V4] = region.getV2();

		return this;
	}

	public SpriteComponent setPosition(float X, float Y) {
		x = X + offsetX;
		y = Y + offsetY;

		if (dirty){
			return this;
		}
		if (rotation != 0 || scaleX != 1 || scaleY != 1) {
			dirty = true;
			return this;
		}

		float x2 = X + width;
		float y2 = Y + height;

		vertices[X1] = X;
		vertices[Y1] = Y;
		vertices[X2] = X;
		vertices[Y2] = y2;
		vertices[X3] = x2;
		vertices[Y3] = y2;
		vertices[X4] = x2;
		vertices[Y4] = Y;
		dirty = true;

		return this;
	}

	public SpriteComponent rotation(float degres){
		this.rotation = degres;
		dirty = true;
		return this;
	}

	public SpriteComponent rotate(float degres){
		this.rotation += degres;
		dirty = true;
		return this;
	}

	public float[] getVertices() {
		if (dirty) {			
			dirty = false; 			
			float[] vertices = this.vertices;
			float localX = -originX;
			float localY = -originY;
			float localX2 = localX + width;
			float localY2 = localY + height;
			float worldOriginX = this.x - localX + offsetX;
			float worldOriginY = this.y - localY + offsetY;

			if (scaleX != 1 || scaleY != 1) {
				localX *= scaleX;
				localY *= scaleY;
				localX2 *= scaleX;
				localY2 *= scaleY;
			}

			if (rotation + offsetRotation != 0) {
				final float cos = MathUtils.cosDeg(rotation + offsetRotation);
				final float sin = MathUtils.sinDeg(rotation + offsetRotation);
				final float localXCos = localX * cos;				
				final float localXSin = localX * sin;				
				final float localYCos = localY * cos;				
				final float localYSin = localY * sin;				
				final float localX2Cos = localX2 * cos;				
				final float localX2Sin = localX2 * sin;				
				final float localY2Cos = localY2 * cos;				
				final float localY2Sin = localY2 * sin; 				
				final float x1 = localXCos - localYSin + worldOriginX;
				final float y1 = localYCos + localXSin + worldOriginY;
				vertices[X1] = x1;
				vertices[Y1] = y1;
				final float x2 = localXCos - localY2Sin + worldOriginX;
				final float y2 = localY2Cos + localXSin + worldOriginY;
				vertices[X2] = x2;
				vertices[Y2] = y2;
				final float x3 = localX2Cos - localY2Sin + worldOriginX;
				final float y3 = localY2Cos + localX2Sin + worldOriginY;
				vertices[X3] = x3;
				vertices[Y3] = y3;
				vertices[X4] = x1 + (x3 - x2);
				vertices[Y4] = y3 - (y2 - y1);
			} else {				
				final float x1 = localX + worldOriginX;
				final float y1 = localY + worldOriginY;
				final float x2 = localX2 + worldOriginX;
				final float y2 = localY2 + worldOriginY;
				vertices[X1] = x1;
				vertices[Y1] = y1;
				vertices[X2] = x1;
				vertices[Y2] = y2;
				vertices[X3] = x2;
				vertices[Y3] = y2;
				vertices[X4] = x2;
				vertices[Y4] = y1;
			}		
		}
		return vertices;	
	}

	public SpriteComponent setVertices(float[] vertices) {
		this.vertices = vertices;
		return this;
	}

	public Rectangle getBoundingRectangle () {
		final float[] vertices = getVertices(); 		
		
		float minx = vertices[X1];		
		float miny = vertices[Y1];		
		float maxx = vertices[X1];		
		float maxy = vertices[Y1]; 		
		
		minx = Math.min(minx, vertices[X2]);
		minx = Math.min(minx, vertices[X3]);
		minx = Math.min(minx, vertices[X4]);
		maxx = Math.max(maxx, vertices[X2]);
		maxx = Math.max(maxx, vertices[X3]);
		maxx = Math.max(maxx, vertices[X4]);
		miny = Math.min(miny, vertices[Y2]);
		miny = Math.min(miny, vertices[Y3]);
		miny = Math.min(miny, vertices[Y4]);
		maxy = Math.max(maxy, vertices[Y2]);
		maxy = Math.max(maxy, vertices[Y3]);
		maxy = Math.max(maxy, vertices[Y4]);
		
		if (bounds == null) bounds = new Rectangle();		
		
		bounds.x = minx;
		bounds.y = miny;
		bounds.width = maxx - minx;		
		bounds.height = maxy - miny;		
			
		return bounds;	
	}
	
	private final static ComponentMapper<SpriteComponent> mapper = ComponentMapper.getFor(SpriteComponent.class);

	public static SpriteComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
