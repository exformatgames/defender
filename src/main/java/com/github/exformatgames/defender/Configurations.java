package com.github.exformatgames.defender;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

public class Configurations {

    public static String GAME_NAME = "default_name";

	public static final Array<Integer> TARGET_KEYS = new Array<>();
	public static final Array<Integer> TARGET_BUTTONS = new Array<>();

	public static final Array<Integer> DEFAULT_KEYS = new Array<>(new Integer[]{
			Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D,
			Input.Keys.SPACE, Input.Keys.Q, Input.Keys.E, Input.Keys.R,
			Input.Keys.F, Input.Keys.G, Input.Keys.SHIFT_LEFT, Input.Keys.CONTROL_LEFT
	});

	public static final Array<Integer> DEFAULT_BUTTONS = new Array<>(new Integer[]{
			Input.Buttons.LEFT, Input.Buttons.RIGHT, Input.Buttons.MIDDLE,
			Input.Buttons.FORWARD, Input.Buttons.BACK
	});

	public static float GLOBAL_SOUND_VOLUME = 1; //TODO 0-1
	public static float GLOBAL_MUSIC_VOLUME = 1; //TODO 0-1

	public static float DIVIDER = 100;
	public static float SCL = 0.01f;

	public static float VIEWPORTS_RATIO = 100;

	public static String PREFERENCES_NAME = "defender_default_pref_name";

	public static float WORLD_WIDTH = 10.8f;
	public static float WORLD_HEIGHT = 19.2f;

	public static float UI_WIDTH = 1080;
	public static float UI_HEIGHT = 1920;

	public static float ZOOM_BASIS = 0.135f;


    public static float BOX2D_TIME_STEP = 0.016f;
    public static float TARGET_FPS = 60;

}
