[![](https://jitpack.io/v/exformatgames/defender.svg)](https://jitpack.io/#exformatgames/defender)


Defender. A small add-on for ECS Ashley.

Implements the minimum required to be set of components and systems for developing 2D games.

In debug mode, draws the contours of sprites, bodies, etc.

Render systems.
Transform systems.
Box2D systems.
Audio systems.
Input systems.

EntityBuilder
BodyBuilder.

add in your project:

repositories { 
    mavenCentral()
    maven { url "https://jitpack.io" } 
}
dependencies {
    implementation 'com.github.exformatgames:defender:0.2.2'
}
   
