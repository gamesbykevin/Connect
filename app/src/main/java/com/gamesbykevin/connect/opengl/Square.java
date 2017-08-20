package com.gamesbykevin.connect.opengl;

import android.opengl.GLES20;

import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.board.BoardHelper;
import com.gamesbykevin.connect.entity.Entity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Kevin on 8/13/2017.
 */
public class Square {

    private float[] vertices;
    private short[] indices;
    private float[] uvs;
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    private FloatBuffer uvBuffer;

    //indices and uvs coordinates for a single entity
    private short[] tmpIndices;
    private float[] tmpUvs;


    public Square() {
        //default constructor
    }

    private void setupImage() {

        //create our UV coordinates meaning we are going to render the entire texture
        if (this.tmpUvs == null)
            this.tmpUvs = new float[] { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };

        setupImage(this.tmpUvs);
    }

    private void setupImage(float[] tmp) {

        this.uvs = tmp;

        //the texture buffer
        ByteBuffer bb = ByteBuffer.allocateDirect(tmp.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(tmp);
        uvBuffer.position(0);
    }

    private void setupTriangle() {

        //create indices meaning we are only rendering one quad texture (2 triangles)
        if (this.tmpIndices == null)
            this.tmpIndices = new short[] { 0, 1, 2, 0, 2, 3 };

        setupTriangle(this.tmpIndices);
    }

    private void setupTriangle(short[] tmp) {

        this.indices = tmp;

        //initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(tmp.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(tmp);
        drawListBuffer.position(0);
    }

    private void setupVertices(float[] tmp) {

        this.vertices = tmp;

        //the vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(tmp.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(tmp);
        vertexBuffer.position(0);
    }

    public void render(final Board board, final float[] m) {

        //assign the texture atlas coordinates
        setupImage(board.getUvs());

        //setup the indices to render all the shapes
        setupTriangle(board.getIndices());

        //return cached array to improve performance
        setupVertices(board.getVertices());

        //render
        render(m);
    }

    public void render(final Entity entity, final float[] m) {

        //assign default texture atlas coordinates
        setupImage();

        //setup the indices to render this single entity
        setupTriangle();

        //calculate vertices
        setupVertices(entity.getTransformedVertices());

        //render
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

        //enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mTexCoordLoc);

        //prepare the texture coordinates
        GLES20.glVertexAttribPointer(mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, uvBuffer);

        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_Image, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        // Get handle to textures locations
        int mSamplerLoc = GLES20.glGetUniformLocation(riGraphicTools.sp_Image, "s_texture");

        // Set the sampler texture unit to 0, where we have saved the texture.
        GLES20.glUniform1i(mSamplerLoc, 0);

        //draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        //disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordLoc);
    }
}