package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Uniform{
	public int id;
	public int location;
	public String name;
	public float value = 0;
	public Vector2 vec2 = null;
	public Vector3 vec3 = null;
	public Color color = null;

	public Uniform(String name) {
		this.name = name;
	}
}
