package com.gamesbykevin.connect.opengl;

import android.opengl.GLES20;

import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.shape.CustomShape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import static com.gamesbykevin.connect.game.GameHelper.getEntity;

/**
 * Created by Kevin on 8/13/2017.
 */
public class Square {

    public static float vertices[];
    public static short indices[];
    public static float uvs[];
    public FloatBuffer vertexBuffer;
    public ShortBuffer drawListBuffer;
    public FloatBuffer uvBuffer;

    public Square() {
        setupImage();
        setupTriangle();
    }

    private void setupImage() {
        if (this.uvs == null) {
            //create our UV coordinates meaning we are going to render the entire texture
            uvs = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
            };
        }

        setupImage(uvs);
    }

    private void setupImage(float[] uvsTmp) {

        // The texture buffer
        ByteBuffer bb = ByteBuffer.allocateDirect(uvsTmp.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uvsTmp);
        uvBuffer.position(0);
    }

    private void setupTriangle() {

        // The order of vertex rendering for a quad
        indices = new short[] {0, 1, 2, 0, 2, 3};

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(indices);
        drawListBuffer.position(0);
    }

    private void setupVertices(float[] vertices) {

        //assign array
        this.vertices = vertices;

        // The vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(this.vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(this.vertices);
        vertexBuffer.position(0);
    }

    private void update(CustomShape shape) {

        //if rotating update vertices
        if (shape.hasRotate())
            shape.updateVertices();

        //assign the texture atlas coordinates
        setupImage(shape.getTextureCoordinates());

        //return cached array to improve performance
        setupVertices(shape.getVertices());
    }

    private void update(Entity entity) {

        //assign default texture atlas coordinates
        setupImage();

        //calculate vertices
        setupVertices(entity.getTransformedVertices());
    }

    public void render(final CustomShape shape, final float[] m) {
        update(shape);
        render(m);
    }

    public void render(final Entity entity, final float[] m) {
        update(entity);
        render(m);
    }

    private void render(final float[] m) {

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_Image, "vPosition");

        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        // Get handle to texture coordinates location
        int mTexCoordLoc = GLES20.glGetAttribLocation(riGraphicTools.sp_Image, "a_texCoord" );

        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mTexCoordLoc);

        // Prepare the texture coordinates
        GLES20.glVertexAttribPointer(mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, uvBuffer);

        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_Image, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        // Get handle to textures locations
        int mSamplerLoc = GLES20.glGetUniformLocation(riGraphicTools.sp_Image, "s_texture");

        // Set the sampler texture unit to 0, where we have saved the texture.
        GLES20.glUniform1i(mSamplerLoc, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordLoc);
    }
}