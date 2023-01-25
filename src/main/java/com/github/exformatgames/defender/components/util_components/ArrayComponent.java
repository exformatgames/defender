package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class ArrayComponent implements Component {

    public final Array<ArrayComponent> array = new Array<>();
}
