package view;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import model.cubes.Cube;
import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class Renderer {
    private ThreeCube cube;
    public volatile boolean rotatingFront = false;
    public volatile boolean rotatingBack = false;
    public volatile boolean rotatingTop = false;
    public volatile boolean rotatingBottom = false;
    public volatile boolean rotatingRight = false;
    public volatile boolean rotatingLeft = false;
    public volatile boolean rotating = false;
    private int backRotationAngle = 0;
    private int topRotationAngle = 0;
    private int bottomRotationAngle = 0;
    private int frontRotationAngle = 0;
    private int rightRotationAngle = 0;
    private int leftRotationAngle = 0;

    private final GLU glu = new GLU();
    public float rotX = 0f;
    public float rotY = 0f;
    public float rotZ = 0f;

    public int animationAngleOffset;

    public Renderer(ThreeCube cube) {
        this.cube = cube;
    }

    public void init(GLAutoDrawable d) {
        GL2 gl2 = d.getGL().getGL2();
        gl2.glClearColor(0f, 0f, 0f, 0f);
    }

    public void resize(GLAutoDrawable d, int width, int height) {
        GL2 gl = d.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }

        float aspect = (float) width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        //GLU glu = new GLU();
        glu.gluPerspective(45f, aspect, 1f, 100f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable d) {
        GL2 gl = d.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        glu.gluLookAt(10f, 10f, 5f,
                0f, 0f, 0f,
                0f, 1f, 0f);

        gl.glRotatef(rotX, 1f, 0f, 0f);
        gl.glRotatef(rotY, 0f, 1f, 0f);
        gl.glRotatef(rotZ, 0f, 0f, 1f);

        float spacing = 2.1f;
        Color[][] colors = cube.toArrayForAnimation();

        int i = 0;
        int j = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++, i++) {
                    gl.glPushMatrix();

                    if (z == 1 && rotatingFront) {
                        gl.glTranslatef(0f, 0f, spacing);
                        gl.glRotatef(frontRotationAngle, 0f, 0f, 1f);
                        gl.glTranslatef(0f, 0f, -spacing);
                    }
                    if (z == -1 && rotatingBack) {
                        gl.glTranslatef(0f, 0f, spacing);
                        gl.glRotatef(backRotationAngle, 0f, 0f, 1f);
                        gl.glTranslatef(0f, 0f, -spacing);
                    }
                    if (y == 1 && rotatingTop) {
                        gl.glTranslatef(0f, spacing, 0f);
                        gl.glRotatef(topRotationAngle, 0f, 1f, 0f);
                        gl.glTranslatef(0f, -spacing, 0f);
                    }
                    if (y == -1 && rotatingBottom) {
                        gl.glTranslatef(0f, spacing, 0f);
                        gl.glRotatef(bottomRotationAngle, 0f, 1f, 0f);
                        gl.glTranslatef(0f, -spacing, 0f);
                    }
                    if (x == 1 && rotatingRight) {
                        gl.glTranslatef(spacing, 0f, 0f);
                        gl.glRotatef(rightRotationAngle, 1f, 0f, 0f);
                        gl.glTranslatef(-spacing, 0f, 0f);
                    }
                    if (x == -1 && rotatingLeft) {
                        gl.glTranslatef(spacing, 0f, 0f);
                        gl.glRotatef(leftRotationAngle, 1f, 0f, 0f);
                        gl.glTranslatef(-spacing, 0f, 0f);
                    }

                    gl.glTranslatef(x * spacing, y * spacing, z * spacing);
                    drawCube(gl, colors[i][j++].rgb, colors[i][j++].rgb, colors[i][j++].rgb, colors[i][j++].rgb, colors[i][j++].rgb, colors[i][j].rgb);
                    j = 0;
                    gl.glPopMatrix();
                }
            }
        }
    }

    public void dispose(GLAutoDrawable d) {
    }

    private void drawCube(GL2 gl, List<Float> frontColor, List<Float> backColor, List<Float> leftColor,
                          List<Float> rightColor, List<Float> topColor, List<Float> bottomColor) {
        gl.glBegin(GL2.GL_QUADS);

        //Front
        gl.glColor3f(frontColor.get(0), frontColor.get(1), frontColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        //Back
        gl.glColor3f(backColor.get(0), backColor.get(1), backColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        //Left
        gl.glColor3f(leftColor.get(0), leftColor.get(1), leftColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);

        //Right
        gl.glColor3f(rightColor.get(0), rightColor.get(1), rightColor.get(2));
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        //Top
        gl.glColor3f(topColor.get(0), topColor.get(1), topColor.get(2));
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);

        //Bottom
        gl.glColor3f(bottomColor.get(0), bottomColor.get(1), bottomColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        gl.glEnd();
    }

    public void solveCube(GLCanvas canvas, List<Move> moves) {
        Thread thread = new Thread(() -> {
            for (Move move : moves) {
                while (rotating); //do nothing
                startAnimation(canvas, move);
            }
        });
        thread.start();
    }
    public void startAnimation(GLCanvas canvas, Move move) {
        rotating = true;
        int animationOffset = 5;
        switch (move) {
            case TR -> {
                animationAngleOffset = -animationOffset;
                startTopAnimation(canvas);
            }
            case TL -> {
                animationAngleOffset = animationOffset;
                startTopAnimation(canvas);
            }
            case DR -> {
                animationAngleOffset = animationOffset;
                startBottomAnimation(canvas);
            }
            case DL -> {
                animationAngleOffset = -animationOffset;
                startBottomAnimation(canvas);
            }
            case FR -> {
                animationAngleOffset = -animationOffset;
                startFrontAnimation(canvas);
            }
            case FL -> {
                animationAngleOffset = animationOffset;
                startFrontAnimation(canvas);
            }
            case BL -> {
                animationAngleOffset = -animationOffset;
                startBackAnimation(canvas);
            }
            case BR -> {
                animationAngleOffset = animationOffset;
                startBackAnimation(canvas);
            }
            case RU -> {
                animationAngleOffset = -animationOffset;
                startRightAnimation(canvas);
            }
            case RD -> {
                animationAngleOffset = animationOffset;
                startRightAnimation(canvas);
            }
            case LU -> {
                animationAngleOffset = -animationOffset;
                startLeftAnimation(canvas);
            }
            case LD -> {
                animationAngleOffset = animationOffset;
                startLeftAnimation(canvas);
            }
        }
        canvas.display();
    }

    public void startFrontAnimation(GLCanvas canvas) {
        if (!rotatingFront) {
            rotatingFront = true;
            frontRotationAngle = 0;
            animateFrontRotation(canvas);
        }
    }

    public void startBackAnimation(GLCanvas canvas) {
        if (!rotatingBack) {
            rotatingBack = true;
            backRotationAngle = 0;
            animateBackRotation(canvas);
        }
    }

    public void startTopAnimation(GLCanvas canvas) {
        if (!rotatingTop) {
            rotatingTop = true;
            topRotationAngle = 0;
            animateTopRotation(canvas);
        }
    }

    public void startBottomAnimation(GLCanvas canvas) {
        if (!rotatingBottom) {
            rotatingBottom = true;
            bottomRotationAngle = 0;
            animateBottomRotation(canvas);
        }
    }

    public void startRightAnimation(GLCanvas canvas) {
        if (!rotatingRight) {
            rotatingRight = true;
            rightRotationAngle = 0;
            animateRightRotation(canvas);
        }
    }

    public void startLeftAnimation(GLCanvas canvas) {
        if (!rotatingLeft) {
            rotatingLeft = true;
            leftRotationAngle = 0;
            animateLeftRotation(canvas);
        }
    }

    private void animateLeftRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            leftRotationAngle += animationAngleOffset;

            if (leftRotationAngle <= -90 ^ leftRotationAngle >= 90) {
                leftRotationAngle = leftRotationAngle >= 90? 90 : -90;
                rotatingLeft = false;
                ((Timer) e.getSource()).stop();
                Move move = leftRotationAngle == 90? Move.LD : Move.LU;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });

        timer.start();
    }

    private void animateRightRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            rightRotationAngle += animationAngleOffset;

            if (rightRotationAngle <= -90 ^ rightRotationAngle >= 90) {
                rightRotationAngle = rightRotationAngle >= 90? 90 : -90;
                rotatingRight = false;
                ((Timer) e.getSource()).stop();
                Move move = rightRotationAngle == 90? Move.RD : Move.RU;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });

        timer.start();
    }

    private void animateFrontRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            frontRotationAngle += animationAngleOffset;

            if (frontRotationAngle <= -90 ^ frontRotationAngle >= 90) {
                frontRotationAngle = frontRotationAngle >= 90? 90 : -90;
                rotatingFront = false;
                ((Timer) e.getSource()).stop();
                Move move = frontRotationAngle == 90? Move.FL : Move.FR;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });

        timer.start();

    }

    private void animateBackRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            backRotationAngle += animationAngleOffset;

            if (backRotationAngle <= -90 ^ backRotationAngle >= 90) {
                backRotationAngle = backRotationAngle >= 90 ? 90 : -90;
                rotatingBack = false;
                ((Timer) e.getSource()).stop();
                Move move = backRotationAngle == 90? Move.BR : Move.BL;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });
        timer.start();
    }

    private void animateTopRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            topRotationAngle += animationAngleOffset;
            if (topRotationAngle <= -90 ^ topRotationAngle >= 90) {
                topRotationAngle = topRotationAngle >= 90 ? 90 : -90;
                rotatingTop = false;
                ((Timer) e.getSource()).stop();
                Move move = topRotationAngle == 90? Move.TL : Move.TR;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });
        timer.start();
    }

    private void animateBottomRotation(GLCanvas canvas) {
        Timer timer = new Timer(16, e -> {
            bottomRotationAngle += animationAngleOffset;

            if (bottomRotationAngle <= -90 ^ bottomRotationAngle >= 90) {
                bottomRotationAngle = bottomRotationAngle >= 90 ? 90 : -90;
                rotatingBottom = false;
                ((Timer) e.getSource()).stop();
                Move move = bottomRotationAngle == 90? Move.DR : Move.DL;
                cube.rotate(move);
                rotating = false;
            }

            canvas.display();
        });
        timer.start();
    }

    public ThreeCube getCube() {
        return cube;
    }
}


