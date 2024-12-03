package view;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;
import model.cubes.threeXThreeCube.solving.kociemba.Solve;

import javax.swing.*;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gui extends JFrame implements GLEventListener {

    private view.Renderer renderer;
    private final JButton solveButton = new JButton("Solve");

    public void create() {
        renderer = new Renderer(new ThreeCube(
                new Color[][]{
                        {Color.RED, Color.YELLOW, Color.ORANGE},
                        {Color.ORANGE, Color.WHITE, Color.GREEN},
                        {Color.BLUE, Color.YELLOW, Color.YELLOW}
                },
                new Color[][]{
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.GREEN, Color.YELLOW, Color.BLUE},
                        {Color.WHITE, Color.ORANGE, Color.BLUE}
                },
                new Color[][]{
                        {Color.RED, Color.RED, Color.BLUE},
                        {Color.BLUE, Color.GREEN, Color.YELLOW},
                        {Color.YELLOW, Color.WHITE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.RED, Color.ORANGE},
                        {Color.RED, Color.BLUE, Color.YELLOW},
                        {Color.ORANGE, Color.GREEN, Color.GREEN}
                },
                new Color[][]{
                        {Color.GREEN, Color.WHITE, Color.WHITE},
                        {Color.RED, Color.RED, Color.WHITE},
                        {Color.WHITE, Color.ORANGE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.BLUE, Color.RED},
                        {Color.BLUE, Color.ORANGE, Color.WHITE},
                        {Color.RED, Color.GREEN, Color.WHITE}
                }));

        setTitle("First OpenGL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        GLProfile profile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        setSize(320, 320);
        getContentPane().add(canvas);

        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);

        canvas.addGLEventListener(this);
        setVisible(true);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                float offset = 2.5f;
                int animationOffset = 5;
                if (e.isControlDown()) {
                    offset = -2.5f;
                    animationOffset = -5;
                }
                switch (e.getKeyCode()) {
                    case 0x31 -> renderer.rotZ += offset;
                    case 0x32 -> renderer.rotY += offset;
                    case 0x33 -> renderer.rotX += offset;
                    case 0x30 -> {
                        renderer.rotX = 0;
                        renderer.rotY = 0;
                        renderer.rotZ = 0;
                    }
                    case 0x34 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startFrontAnimation(canvas);
                    }
                    case 0x35 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startBackAnimation(canvas);
                    }
                    case 0x36 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startTopAnimation(canvas);
                    }
                    case 0x37 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startBottomAnimation(canvas);
                    }
                    case 0x38 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startLeftAnimation(canvas);
                    }
                    case 0x39 -> {
                        renderer.animationAngleOffset = animationOffset;
                        renderer.startRightAnimation(canvas);
                    }
                }
            }
        });

        solveButton.addActionListener(e -> {
            List<Move> solveMoves = Solve.solveThreeCube(renderer.getCube());
            assert solveMoves != null;
            for (Move move: solveMoves) {
                renderer.startAnimation(canvas, move);
                while (renderer.rotating);
            }
        });
        add(solveButton, BorderLayout.SOUTH);

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
