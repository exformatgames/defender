Defender. A small add-on for ECS Ashley.

Implements the minimum required to be set of components and systems for developing 2D games.

In asynchronous mode, it runs on a new thread, rendering redirects to the rendering thread.
In debug mode, draws the contours of sprites, bodies, etc.

render systems.

Transform systems.

Box2D systems.

audio systems.

input systems.

EntityBuilder
BodyBuilder.

add in your project:

repositories { 
    mavenCentral()
    maven { url "https://jitpack.io" } 
}
dependencies {
    implementation 'com.github.exformatgames:defender:0.1.3-bgfx'
}
   
