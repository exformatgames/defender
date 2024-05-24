package com.github.exformatgames.defender;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.assets.Assets;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.entities.Box2DEntityBuilder;
import com.github.exformatgames.defender.systems.audio_systems.MusicSystem;
import com.github.exformatgames.defender.systems.audio_systems.PointSoundSystem;
import com.github.exformatgames.defender.systems.audio_systems.SoundSystem;
import com.github.exformatgames.defender.systems.box2d_systems.*;
import com.github.exformatgames.defender.systems.debug.*;
import com.github.exformatgames.defender.systems.input_systems.GestureInputSystem;
import com.github.exformatgames.defender.systems.input_systems.KeyboardInputSystem;
import com.github.exformatgames.defender.systems.input_systems.MouseInputSystem;
import com.github.exformatgames.defender.systems.input_systems.ResetGestureInputSystem;
import com.github.exformatgames.defender.systems.rendering_systems.CullingSystem;
import com.github.exformatgames.defender.systems.rendering_systems.OrthogonalMapRenderSystem;
import com.github.exformatgames.defender.systems.rendering_systems.ParticlesSystem;
import com.github.exformatgames.defender.systems.rendering_systems.SortedRenderSystem;
import com.github.exformatgames.defender.systems.rendering_systems.ui.TextRenderSystem;
import com.github.exformatgames.defender.systems.transform_systems.*;
import com.github.exformatgames.defender.systems.util_system.*;
import com.github.exformatgames.defender.utils.B2DContactListener;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public abstract class Core {

    protected boolean pause = false;

	protected OrthographicCamera worldCamera;
	protected OrthographicCamera uiCamera;

	protected Viewport worldViewport;
	protected Viewport uiViewport;

	protected World box2DWorld = null;

	protected SpriteBatch spriteBatch;
	protected InputMultiplexer inputMultiplexer;
	public static Assets ASSETS;
	protected AssetManager assetManager;

	private final PooledEngine engine;

	private boolean debug = false;
	private ShapeRenderer debugShapeRenderer;
	private DebugRayCastSystem debugRayCastSystem;
	private DebugPhysicsSystem debugPhysicsSystem;
	private DebugShapesSystem debugShapesSystem;
	private DebugSpriteSystem debugSpriteSystem;
	private DebugPrintEngineInfoSystem debugPrintEngineInfoSystem;


    public Core(Viewport worldViewport, Vector2 gravity, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, final Assets assets) {
        ASSETS = assets;

        this.spriteBatch = spriteBatch;

        this.worldViewport = worldViewport;
        uiViewport = new ExtendViewport(Configurations.UI_WIDTH, Configurations.UI_HEIGHT, 10000, 10000);

        this.worldCamera = (OrthographicCamera) worldViewport.getCamera();
        this.uiCamera = (OrthographicCamera) uiViewport.getCamera();

        if (gravity != null) {
            this.box2DWorld = new World(gravity, true);
        }

        this.inputMultiplexer = inputMultiplexer;

        this.assetManager = assets.getAssetManager();

        engine = new PooledEngine(50, 500, 50, 5000);

    }

    public Core(Vector2 worldViewportSize, Vector2 uiViewportSize, Vector2 gravity, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, final Assets assets) {
        ASSETS = assets;

        this.spriteBatch = spriteBatch;

        worldViewport = new ScalingViewport(Scaling.fill, worldViewportSize.x, worldViewportSize.y);
        uiViewport = new ExtendViewport(uiViewportSize.x, uiViewportSize.y, 10000, 10000);

        this.worldCamera = (OrthographicCamera) worldViewport.getCamera();
        this.uiCamera = (OrthographicCamera) uiViewport.getCamera();

        if (gravity != null) {
            this.box2DWorld = new World(gravity, true);
        }

        this.inputMultiplexer = inputMultiplexer;

        this.assetManager = assets.getAssetManager();

        engine = new PooledEngine(50, 500, 50, 5000);

    }

    public Core(Vector2 worldViewportSize, Vector2 uiViewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, final Assets assets) {
        this(worldViewportSize, uiViewportSize, gravity, new SpriteBatch(), inputMultiplexer, assets);
	}

	public Core(Vector2 viewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, Assets assets) {
		this(viewportSize, viewportSize, gravity, inputMultiplexer, assets);
	}

	public Core(Vector2 gravity, InputMultiplexer inputMultiplexer, Assets assets) {
		this(
            new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT),
            new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT),
            gravity,
            inputMultiplexer,
            assets);
	}

    public Core(Vector2 gravity, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, Assets assets) {
        this(
            new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT),
            new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT),
            gravity,
            spriteBatch,
            inputMultiplexer,
            assets);
    }

	public Core(SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, Assets assets) {
		this(
				new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT),
				new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT),
				Configurations.GRAVITY,
				spriteBatch,
				inputMultiplexer,
				assets);
	}

	public Core(Vector2 gravity, Assets assets) {
		this(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), gravity, new InputMultiplexer(), assets);
	}

	public Core(Assets assets) {
		this(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT), new Vector2(Configurations.UI_WIDTH, Configurations.UI_HEIGHT), null, new InputMultiplexer(), assets);
	}

	protected abstract void initEntities();
	protected abstract void initGameSystems();
	protected abstract void initPostRenderSystems();

	public void update(float deltaTime){
        if (! pause) {
            engine.update(deltaTime);
        }
	}

	public final void create(boolean isDebug){
		Configurations.VIEWPORTS_RATIO = Configurations.UI_HEIGHT / Configurations.WORLD_HEIGHT;

		EntityBuilder.init(box2DWorld, engine, ASSETS, worldViewport);
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
		initPostRenderSystems();//abstract
		initUtilsSystems();

		if(isDebug) {
			this.debug = true;
			debugShapeRenderer = new ShapeRenderer();
			addDebugSystems();
		}

		addSystem(new ResetGestureInputSystem());
		addSystem(new ExitSystem());
		addSystem(new RemoveEntitySystem(box2DWorld));
		addSystem(new RemoveAllEntitiesSystem(box2DWorld));
	}

	private void initInputSystems(){
		engine.addSystem(new GestureInputSystem(inputMultiplexer, worldViewport));
		engine.addSystem(new KeyboardInputSystem(inputMultiplexer));
		engine.addSystem(new MouseInputSystem(inputMultiplexer, worldViewport));
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
		addSystem(new CullingSystem(worldViewport));
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
				debugPhysicsSystem = new DebugPhysicsSystem(worldViewport, worldCamera);

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
			worldViewport.update(width, height);
			uiViewport.update(width, height, true);
		}
	}

	public void pause() {
        pause = true;
    }

	public void hide() {
        pause = true;
    }

	public void resume() {
        pause = false;
    }

	public void dispose(){
		if (box2DWorld != null) {
            for (Entity entity: engine.getEntitiesFor(Family.all(BodyComponent.class).get())) {
                BodyComponent bodyComponent = BodyComponent.getComponent(entity);
                for (Fixture fixture : bodyComponent.body.getFixtureList()) {
                    bodyComponent.body.destroyFixture(fixture);
                }
                box2DWorld.destroyBody(bodyComponent.body);
            }
			box2DWorld.dispose();

            EntityBuilder.world = null;
        }
        engine.removeAllEntities();
        spriteBatch.dispose();
        ASSETS.dispose();
		ASSETS = null;
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

	public Viewport getWorldViewport() {
		return worldViewport;
	}

	public void setWorldViewport(Viewport worldViewport) {
		this.worldViewport = worldViewport;
		worldCamera = (OrthographicCamera) worldViewport.getCamera();
	}

	public Viewport getUiViewport() {
		return uiViewport;
	}

	public void setUiViewport(Viewport uiViewport) {
		this.uiViewport = uiViewport;
		uiCamera = (OrthographicCamera) uiViewport.getCamera();
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}
}
