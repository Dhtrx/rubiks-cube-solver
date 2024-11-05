import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Gui extends JFrame implements GLEventListener {

    private Renderer renderer;
    public void create() {
        setTitle("First OpenGL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GLProfile profile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        setSize(320, 320);
        getContentPane().add(canvas);
        renderer = new Renderer();

        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);

        canvas.addGLEventListener(this);
        setVisible(true);

        animator.start();
    }
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        renderer.init(glAutoDrawable);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
        renderer.dispose(glAutoDrawable);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        renderer.display(glAutoDrawable);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        renderer.resize(glAutoDrawable, i2, i3);
    }
}
