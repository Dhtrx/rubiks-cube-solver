import com.jogamp.opengl.*;

public class Renderer {
    public void init(GLAutoDrawable d) {
        GL2 gl2 = d.getGL().getGL2();
        gl2.glClearColor(0f, 0f, 0f, 0f);
    }
    public void resize(GLAutoDrawable d, int width, int height) {
        GL2 gl = d.getGL().getGL2(); // get the OpenGL 2 graphics context
        gl.glViewport(0, 0, width, height);
    }
    public void display(GLAutoDrawable d) {
        GL2 gl = d.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glVertex3f( 0.5f, -0.5f, 0.0f);
        gl.glVertex3f( 0.0f,  0.5f, 0.0f);
        gl.glEnd();
    }
    public void dispose(GLAutoDrawable d) {
    }

}


