import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private final static List<Float> RED = List.of(1f, 0f, 0f);
    private final static List<Float> ORANGE = List.of(1f, .5f, 0f);
    private final static List<Float> BLUE = List.of(0f, 0f, 1f);
    private final static List<Float> GREEN = List.of(0f, 1f, 0f);
    private final static List<Float> WHITE = List.of(1f, 1f, 1f);
    private final static List<Float> YELLOW = List.of(1f, 1f, 0f);
    private final static List<Float> BLACK = List.of(0f, 0f, 0f);

    private final GLU glu = new GLU();
    public float rotX = 0f;
    public float rotY = 0f;
    public float rotZ = 0f;
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

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    gl.glPushMatrix();
                    gl.glTranslatef(x * spacing, y * spacing, z * spacing);

                    List<Float> left = (x == -1) ? Renderer.BLUE : Renderer.BLACK;
                    List<Float> right = (x == 1) ? Renderer.GREEN : Renderer.BLACK;
                    List<Float> bottom = (y == -1) ? Renderer.WHITE : Renderer.BLACK;
                    List<Float> top = (y == 1) ? Renderer.YELLOW : Renderer.BLACK;
                    List<Float> front = (z == 1) ? Renderer.RED : Renderer.BLACK;
                    List<Float> back = (z == -1) ? Renderer.ORANGE : Renderer.BLACK;

                    drawCube(gl, front, back, left, right, top, bottom);
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
        gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        //Back
        gl.glColor3f(backColor.get(0), backColor.get(1), backColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glVertex3f( 1.0f, -1.0f, -1.0f);

        //Left
        gl.glColor3f(leftColor.get(0), leftColor.get(1), leftColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glVertex3f(-1.0f,  1.0f, -1.0f);

        //Right
        gl.glColor3f(rightColor.get(0), rightColor.get(1), rightColor.get(2));
        gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glVertex3f( 1.0f, -1.0f,  1.0f);

        //Top
        gl.glColor3f(topColor.get(0), topColor.get(1), topColor.get(2));
        gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glVertex3f( 1.0f,  1.0f, -1.0f);

        //Bottom
        gl.glColor3f(bottomColor.get(0), bottomColor.get(1), bottomColor.get(2));
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glVertex3f(-1.0f, -1.0f,  1.0f);

        gl.glEnd();
    }

}


