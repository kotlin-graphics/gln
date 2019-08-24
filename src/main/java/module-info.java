module com.github.kotlin_graphics.gln {

    requires java.desktop;

    requires kotlin.stdlib;
    requires kotlin.reflect;

    requires org.lwjgl;
    requires org.lwjgl.opengl;
    requires org.lwjgl.opengles;


    requires com.github.kotlin_graphics.gli;
    requires com.github.kotlin_graphics.glm;
    requires com.github.kotlin_graphics.kool;
    requires com.github.kotlin_graphics.kotlin_unsigned;

    exports gln;
    exports gln.buffer;
    exports gln.cap;
    exports gln.draw;
    exports gln.framebuffer;
    exports gln.glf;
    exports gln.identifiers;
    exports gln.misc;
    exports gln.msaa;
    exports gln.program;
    exports gln.renderbuffer;
    exports gln.sampler;
    exports gln.texture;
    exports gln.transformFeedback;
    exports gln.uniform;
    exports gln.uniformBlock;
    exports gln.vertexArray;
}