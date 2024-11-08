import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                float offset = 2.5f;
                if (e.isControlDown()) offset = -2.5f;
                switch (e.getKeyCode()) {
                    case 0x31 -> renderer.rotZ += offset;
                    case 0x32 -> renderer.rotY += offset;
                    case 0x33 -> renderer.rotX += offset;
                    case 0x30 -> {
                        renderer.rotX = 0;
                        renderer.rotY = 0;
                        renderer.rotZ = 0;
                    }
                }
            }
        });

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
