package com.github.exformatgames.defender;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.*;
import com.github.exformatgames.defender.systems.debug.*;
import com.github.exformatgames.defender.systems.util_system.*;
import com.github.exformatgames.defender.entities.Box2DEntityBuilder;
import com.github.exformatgames.defender.systems.audio_systems.MusicSystem;
import com.github.exformatgames.defender.systems.audio_systems.PointSoundSystem;
import com.github.exformatgames.defender.systems.audio_systems.SoundSystem;
import com.github.exformatgames.defender.systems.box2d_systems.*;
import com.github.exformatgames.defender.systems.input_systems.GestureInputSystem;
import com.github.exformatgames.defender.systems.input_systems.KeyboardInputSystem;
import com.github.exformatgames.defender.systems.input_systems.MouseInputSystem;
import com.github.exformatgames.defender.systems.input_systems.ResetGestureInputSystem;
import com.github.exformatgames.defender.systems.rendering_systems.*;
import com.github.exformatgames.defender.systems.rendering_systems.ui.TextRenderSystem;
import com.github.exformatgames.defender.systems.transform_systems.*;
import com.github.exformatgames.defender.utils.B2DContactListener;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public abstract class Core {
	
	protected OrthographicCamera worldCamera;
	protected OrthographicCamera uiCamera;

	protected Viewport worldViewport;
	protected Viewport uiViewport;

	protected World box2DWorld = null;
	protected SpriteBatch spriteBatch;
	protected InputMultiplexer inputMultiplexer;
	protected TextureAtlas textureAtlas;
	protected AssetManager assetManager;

	private final PooledEngine engine;

	private boolean debug = false;
	private ShapeRenderer debugShapeRenderer = new ShapeRenderer();
	private DebugRayCastSystem debugRayCastSystem;
	private DebugPhysicsSystem debugPhysicsSystem;
	private DebugShapesSystem debugShapesSystem;
	private DebugSpriteSystem debugSpriteSystem;
	private DebugPrintEngineInfoSystem debugPrintEngineInfoSystem;

	public Core(Vector2 worldViewportSize, Vector2 uiViewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		worldViewport = new ExtendViewport(worldViewportSize.x, worldViewportSize.y);
		worldViewport.apply(true);

		uiViewport = new ExtendViewport(uiViewportSize.x, uiViewportSize.y);
		uiViewport.apply(true);

		this.worldCamera = (OrthographicCamera) worldViewport.getCamera();
		this.uiCamera = (OrthographicCamera) uiViewport.getCamera();

		if (gravity != null) {
			this.box2DWorld = new World(gravity, true);
		}
		this.spriteBatch = new SpriteBatch();
		this.inputMultiplexer = inputMultiplexer;
		this.textureAtlas = textureAtlas;
		this.assetManager = assetManager;

		Gdx.input.setInputProcessor(inputMultiplexer);

		engine = new PooledEngine(50, 500, 50, 5000);
	}

	public Core(Vector2 viewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		this(viewportSize, viewportSize, gravity, inputMultiplexer, textureAtlas, assetManager);
	}

	public Core(Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		this(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), gravity, inputMultiplexer, textureAtlas, assetManager);
	}

	public Core(Vector2 gravity, TextureAtlas textureAtlas, AssetManager assetManager) {
		this(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), gravity, new InputMultiplexer(), textureAtlas, assetManager);
	}

	public Core(TextureAtlas textureAtlas, AssetManager assetManager) {
		this(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), null, new InputMultiplexer(), textureAtlas, assetManager);
	}

	protected abstract void initEntities();
	protected abstract void initGameSystems();
	
	public void update(float deltaTime){
		engine.update(deltaTime);
	}
	
	public final void create(boolean isDebug){
		Configurations.VIEWPORTS_RATIO = Configurations.UI_HEIGHT / Configurations.WORLD_HEIGHT;

		EntityBuilder.init(box2DWorld, engine, textureAtlas, worldCamera, assetManager);
		BodyBuilder.init(box2DWorld);

		initEntities();//abstract
		
		initInputSystems();

		if(box2DWorld != null){
			initBox2DSystems();
		}

		initGameSystems();//abstract

		initAudioSystems();
		addSystem(new VibrationSystem());
		initAnimationEffectSystems();
		initTransformSystems();
		initParticleSystems();

		initRenderSystems();
		initUtilsSystems();
		
		if(isDebug) {
			this.debug = true;
			addDebugSystems();
		}

		addSystem(new ResetGestureInputSystem());
		addSystem(new ExitSystem());
		addSystem(new RemoveEntitySystem(box2DWorld));
		addSystem(new RemoveAllEntitiesSystem(box2DWorld));
	}
	
	private void initInputSystems(){
		engine.addSystem(new GestureInputSystem(inputMultiplexer, worldCamera));
		engine.addSystem(new KeyboardInputSystem());
		engine.addSystem(new MouseInputSystem(worldCamera));
	}
	
	private void initBox2DSystems(){
		new Box2DEntityBuilder().create();

		addSystem(new TransformBodySystem());
		addSystem(new LinearVelocityBodySystem());
		addSystem(new ForceSystem());
		addSystem(new AngularImpulseSystem());
		addSystem(new LinearImpulseSystem());
		addSystem(new RemoveBodySystem(box2DWorld));
		addSystem(new RayCastSystem(box2DWorld));
		addSystem(new UpdateTransformSystem());
		addSystem(new CollisionClearSystem());
		addSystem(new UpdateWorldSystem());
		addSystem(new AABBQuerySystem(box2DWorld));

		box2DWorld.setContactListener(new B2DContactListener());
	}
	
	private void initAudioSystems(){
		addSystem(new SoundSystem());
		addSystem(new PointSoundSystem(worldCamera));
		addSystem(new MusicSystem());
	}
	
	private void initAnimationEffectSystems(){
		addSystem(new AnimationSpriteSystem());
		addSystem(new ParallaxSystem(worldCamera));
		addSystem(new ScrollSystem(worldCamera));
		addSystem(new AngularVelocitySystem());
		addSystem(new LinearVelocitySystem());
	}
	
	private void initTransformSystems(){
		addSystem(new RotationSystem());
		addSystem(new RotateSystem());
		addSystem(new ScaleLoopSystem());
		addSystem(new ScaleSpriteSystem());
		addSystem(new NewPositionSystem());
		addSystem(new TransformLightComponentSystem());
		addSystem(new TranslateSystem());
	}
	
	private void initRenderSystems(){
		addSystem(new CullingSystem(worldCamera));
		addSystem(new OrthogonalMapRenderSystem(worldCamera));
		addSystem(new SortedRenderSystem(worldViewport, spriteBatch));
		addSystem(new TextRenderSystem(uiViewport, spriteBatch));
	}
	
	private void initParticleSystems(){
		addSystem(new ParticlesSystem());
		if (box2DWorld != null) addSystem(new B2DParticleEmitterUpateSystem());
	}
	
	private void initUtilsSystems(){
		engine.addSystem(new CreateEntitySystem());
		engine.addSystem(new SetPreferencesSystem());
		engine.addSystem(new GetPreferencesSystem());
	}



	private void addDebugSystems(){

		if(box2DWorld != null){
			if (debugRayCastSystem == null)
				debugRayCastSystem = new DebugRayCastSystem(worldCamera, debugShapeRenderer);

			if (debugPhysicsSystem == null)
				debugPhysicsSystem = new DebugPhysicsSystem(worldCamera);

			addSystem(debugRayCastSystem);
			addSystem(debugPhysicsSystem);
		}

		if (debugSpriteSystem == null)
			debugSpriteSystem = new DebugSpriteSystem(worldViewport, uiViewport, spriteBatch, debugShapeRenderer);
		if (debugShapesSystem == null)
			debugShapesSystem = new DebugShapesSystem(worldCamera, debugShapeRenderer);
		if (debugPrintEngineInfoSystem == null)
			debugPrintEngineInfoSystem = new DebugPrintEngineInfoSystem(spriteBatch, uiViewport);

		addSystem(debugSpriteSystem);
		addSystem(debugShapesSystem);
		addSystem(debugPrintEngineInfoSystem);
	}

	public PooledEngine getEngine() {
		return engine;
	}

	public Core addSystem(EntitySystem system){
		engine.addSystem(system);
		return this;
	}

	public void addEntity(Entity entity) {
		engine.addEntity(entity);
	}

	public Core addSystems(EntitySystem... systems){
		for (EntitySystem system: systems) {
			addSystem(system);
		}
		return this;
	}

	public InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void resize(int width, int height) {
		if (worldViewport != null && uiViewport != null){
			worldViewport.update(width, height, true);
			uiViewport.update(width, height, true);
		}
	}

	public void pause() {}

	public void hide() {}

	public void resume() {}

	public void dispose(){
		textureAtlas.dispose();
		if (box2DWorld != null) {
			box2DWorld.dispose();
		}
		assetManager.dispose();
	}

	public Core debugOn() {
		if ( !debug) {
			addDebugSystems();
		}
		return this;
	}

	public Core debugOff() {
		if (debug) {
			if(box2DWorld != null){
				if (debugRayCastSystem != null)
					engine.removeSystem(debugRayCastSystem);

				if (debugPhysicsSystem != null)
					engine.removeSystem(debugPhysicsSystem);
			}

			if (debugSpriteSystem != null)
				engine.removeSystem(debugSpriteSystem);
			if (debugShapesSystem != null)
				engine.removeSystem(debugShapesSystem);
			if (debugPrintEngineInfoSystem != null)
				engine.removeSystem(debugPrintEngineInfoSystem);
		}
		return this;
	}

	public boolean isDebug() {
		return debug;
	}
}
