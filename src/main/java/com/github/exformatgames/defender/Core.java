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
import com.github.exformatgames.defender.utils.Box2DEntityBuilder;
import com.github.exformatgames.defender.systems.audio_systems.MusicSystem;
import com.github.exformatgames.defender.systems.audio_systems.PointSoundSystem;
import com.github.exformatgames.defender.systems.audio_systems.SoundSystem;
import com.github.exformatgames.defender.systems.box2d_systems.*;
import com.github.exformatgames.defender.systems.debug.DebugPhysicsSystem;
import com.github.exformatgames.defender.systems.debug.DebugRayCastSystem;
import com.github.exformatgames.defender.systems.debug.DebugShapesSystem;
import com.github.exformatgames.defender.systems.debug.DebugSpriteSystem;
import com.github.exformatgames.defender.systems.input_systems.GestureInputSystem;
import com.github.exformatgames.defender.systems.input_systems.KeyboardInputSystem;
import com.github.exformatgames.defender.systems.input_systems.MouseInputSystem;
import com.github.exformatgames.defender.systems.input_systems.ResetGestureInputSystem;
import com.github.exformatgames.defender.systems.rendering_systems.*;
import com.github.exformatgames.defender.systems.rendering_systems.ui.TextRenderSystem;
import com.github.exformatgames.defender.systems.transform_systems.*;
import com.github.exformatgames.defender.systems.util_system.CreateEntitySystem;
import com.github.exformatgames.defender.systems.util_system.ExitSystem;
import com.github.exformatgames.defender.systems.util_system.RemoveEntitySystem;
import com.github.exformatgames.defender.systems.util_system.VibrationSystem;
import com.github.exformatgames.defender.utils.B2DContactListener;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public abstract class Core {
	
	protected OrthographicCamera worldCamera;
	protected OrthographicCamera uiCamera;

	protected Viewport worldViewport;
	protected Viewport uiViewport;

	protected World box2DWorld;
	protected SpriteBatch spriteBatch;
	protected InputMultiplexer inputMultiplexer;
	protected TextureAtlas textureAtlas;
	protected AssetManager assetManager;

	private final PooledEngine engine;

	private boolean stopEngine = false;
	private float engineDeltaTime = 0;

	public Core(OrthographicCamera gameCamera, OrthographicCamera uiCamera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		this.worldCamera = gameCamera;
		this.uiCamera = uiCamera;
		this.box2DWorld = box2DWorld;
		this.spriteBatch = spriteBatch;
		this.inputMultiplexer = inputMultiplexer;
		this.textureAtlas = textureAtlas;
		this.assetManager = assetManager;
		
		engine = new PooledEngine(50, 500, 50, 5000);
	}

	public Core(Vector2 viewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		this(viewportSize, viewportSize, gravity, inputMultiplexer, textureAtlas, assetManager);
	}

	public Core(Vector2 viewportSize, Vector2 uiViewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas textureAtlas, AssetManager assetManager) {
		worldViewport = new ExtendViewport(viewportSize.x, viewportSize.y);
		worldViewport.apply(true);

		uiViewport = new ExtendViewport(uiViewportSize.x, uiViewportSize.y);
		uiViewport.apply(true);

		this.worldCamera = (OrthographicCamera) worldViewport.getCamera();
		this.uiCamera = (OrthographicCamera) uiViewport.getCamera();
		this.box2DWorld = new World(gravity, true);
		this.spriteBatch = new SpriteBatch();
		this.inputMultiplexer = inputMultiplexer;
		this.textureAtlas = textureAtlas;
		this.assetManager = assetManager;
		
		engine = new PooledEngine(50, 500, 50, 5000);
	}

	protected abstract void initEntities();
	protected abstract void initGameSystems();
	
	public void update(float deltaTime){
		engine.update(deltaTime);
	}
	
	public final void create(boolean isDebug, boolean asyncEngine){
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

		initRenderSystems(asyncEngine);

		initUtilsSystems();
		
		if(isDebug && ! asyncEngine)
			initDebugSystems();
		
		addSystem(new ResetGestureInputSystem());
		addSystem(new ExitSystem());
		addSystem(new RemoveEntitySystem(box2DWorld));

		if (asyncEngine){
			new Thread(() -> {
				while (!stopEngine){
					long start = System.nanoTime();
					update(engineDeltaTime);
					engineDeltaTime = (System.nanoTime() - start) / 1000_000_000f;
				}
			}).start();
		}
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
	
	private void initRenderSystems(boolean asyncRender){
		addSystem(new OrthogonalMapRenderSystem(worldCamera));
		addSystem(new CullingSystem(worldCamera));

		if (asyncRender) {
			addSystem(new AsyncSortRenderSystem(worldViewport, spriteBatch));
		}
		else {
			addSystem(new SortedRenderSystem(worldViewport, spriteBatch));
			addSystem(new TextRenderSystem(uiViewport, spriteBatch));
		}
	}
	
	private void initParticleSystems(){
		addSystem(new ParticlesSystem());
		if (box2DWorld != null) addSystem(new B2DParticleEmitterUpateSystem());
	}
	
	private void initUtilsSystems(){
		engine.addSystem(new CreateEntitySystem());
		//engine.addSystem(new PreferencesSystem()); //TODO not work!!!
	}
	
	private void initDebugSystems(){
		ShapeRenderer shapeRenderer = new ShapeRenderer();

		if(box2DWorld != null){
			addSystem(new DebugRayCastSystem(worldCamera, shapeRenderer));
			addSystem(new DebugPhysicsSystem(worldCamera));
		}
		addSystem(new DebugSpriteSystem(worldCamera, shapeRenderer));
		addSystem(new DebugShapesSystem(worldCamera, shapeRenderer));
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

	public void resize(int width, int height) {
		if (worldViewport != null && uiViewport != null){
			worldViewport.update(width, height, true);
			uiViewport.update(width, height, true);
		}
	}

	public void dispose(){
		stopEngine = true;

		textureAtlas.dispose();
		if (box2DWorld != null) {
			box2DWorld.dispose();
		}
		assetManager.dispose();
	}
}
