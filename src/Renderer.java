import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

public class Renderer {
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
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        glu.gluLookAt(3f, 3f, 5f,
                0f, 0f, 0f,
                0f, 1f, 0f);

        //Rotate round Z
        gl.glRotatef(rotZ, 0f, 0f, 1f);
        //Rotate round Y
        gl.glRotatef(rotY, 0f, 1f, 0f);
        //Rotate round X
        gl.glRotatef(rotX, 1f, 0f, 0f);

        drawCube(gl);
    }
    public void dispose(GLAutoDrawable d) {
    }

    private void drawCube(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);

        //Front
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // V0
        gl.glVertex3f( 1.0f, -1.0f, 1.0f); // V1
        gl.glVertex3f( 1.0f,  1.0f, 1.0f); // V2
        gl.glVertex3f(-1.0f,  1.0f, 1.0f); // V3

        //Back
        gl.glColor3f(1f, .5f, 0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // V4
        gl.glVertex3f( 1.0f, -1.0f, -1.0f); // V5
        gl.glVertex3f( 1.0f,  1.0f, -1.0f); // V6
        gl.glVertex3f(-1.0f,  1.0f, -1.0f); // V7

        //Left
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(-1.0f, -1.0f,  1.0f); // V0
        gl.glVertex3f(-1.0f,  1.0f,  1.0f); // V3
        gl.glVertex3f(-1.0f,  1.0f, -1.0f); // V7
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // V4

        //Right
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex3f(1.0f, -1.0f,  1.0f); // V1
        gl.glVertex3f(1.0f,  1.0f,  1.0f); // V2
        gl.glVertex3f(1.0f,  1.0f, -1.0f); // V6
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // V5

        //Top
        gl.glColor3f(1f, 1f, 0f);
        gl.glVertex3f(-1.0f,  1.0f,  1.0f); // V3
        gl.glVertex3f( 1.0f,  1.0f,  1.0f); // V2
        gl.glVertex3f( 1.0f,  1.0f, -1.0f); // V6
        gl.glVertex3f(-1.0f,  1.0f, -1.0f); // V7

        //Bottom
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex3f(-1.0f, -1.0f,  1.0f); // V0
        gl.glVertex3f( 1.0f, -1.0f,  1.0f); // V1
        gl.glVertex3f( 1.0f, -1.0f, -1.0f); // V5
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // V4

        gl.glEnd();
    }

}


