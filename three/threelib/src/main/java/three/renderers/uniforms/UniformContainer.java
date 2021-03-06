package three.renderers.uniforms;

import java.util.ArrayList;
import java.util.HashMap;

import three.math.Matrix3;
import three.math.Matrix4;
import three.math.Vector2;
import three.math.Vector3;
import three.math.Vector4;
import three.renderers.GLRenderer;

public class UniformContainer extends AbstractUniform{
    public ArrayList<AbstractUniform> seq = new ArrayList<>();
    public HashMap<String, AbstractUniform> map = new HashMap<>();

    private static ArrayList<float[]> arrayCacheF32 = new ArrayList<>();

    public UniformContainer(String id){
        super(id);
    }


    protected static float[] flattenV2(ArrayList<Vector2> array, int nBlocks, int blockSize){
        Vector2 firstElem = array.get(0);
        int n = nBlocks * blockSize;
        float[] r = arrayCacheF32.get(n);

        if ( r == null ) {
            r = new float[n];
            arrayCacheF32.add(n, r);
        }

        if ( nBlocks != 0 ) {
            firstElem.toArray( r, 0 );
            for ( int i = 1, offset = 0; i != nBlocks; ++ i ) {
                offset += blockSize;
                array.get(i).toArray( r, offset );
            }
        }
        return r;
    }

    protected static float[] flattenV3(ArrayList<Vector3> array, int nBlocks, int blockSize){
        Vector3 firstElem = array.get(0);
        int n = nBlocks * blockSize;
        float[] r = arrayCacheF32.get(n);

        if ( r == null ) {
            r = new float[n];
            arrayCacheF32.set(n, r);
        }

        if ( nBlocks != 0 ) {
            firstElem.toArray( r, 0 );
            for ( int i = 1, offset = 0; i != nBlocks; ++ i ) {
                offset += blockSize;
                array.get(i).toArray( r, offset );
            }
        }

        return r;
    }

    protected static float[] flattenV4(ArrayList<Vector4> array, int nBlocks, int blockSize){
        Vector4 firstElem = array.get(0);
        int n = nBlocks * blockSize;
        float[] r = arrayCacheF32.get(n);

        if ( r == null ) {
            r = new float[n];
            arrayCacheF32.set(n, r);
        }

        if ( nBlocks != 0 ) {
            firstElem.toArray( r, 0 );
            for ( int i = 1, offset = 0; i != nBlocks; ++ i ) {
                offset += blockSize;
                array.get(i).toArray( r, offset );
            }
        }

        return r;
    }

    protected static float[] flattenM3(ArrayList<Matrix3> array, int nBlocks, int blockSize){
        Matrix3 firstElem = array.get(0);
        int n = nBlocks * blockSize;
        float[] r = arrayCacheF32.get(n);

        if ( r == null ) {
            r = new float[n];
            arrayCacheF32.set(n, r);
        }

        if ( nBlocks != 0 ) {
            firstElem.toArray( r, 0 );
            for ( int i = 1, offset = 0; i != nBlocks; ++ i ) {
                offset += blockSize;
                array.get(i).toArray( r, offset );
            }
        }

        return r;
    }

    protected static float[] flattenM4(ArrayList<Matrix4> array, int nBlocks, int blockSize){
        Matrix4 firstElem = array.get(0);
        int n = nBlocks * blockSize;
        float[] r = arrayCacheF32.get(n);

        if ( r == null ) {
            r = new float[n];
            arrayCacheF32.set(n, r);
        }

        if ( nBlocks != 0 ) {
            firstElem.toArray( r, 0 );
            for ( int i = 1, offset = 0; i != nBlocks; ++ i ) {
                offset += blockSize;
                array.get(i).toArray( r, offset );
            }
        }

        return r;
    }

    protected static boolean arraysEqual(ArrayList a, int[] b){
        if ( a.size() != b.length ) return false;

        for ( int i = 0, l = a.size(); i < l; i ++ ) {
            if ( (int)a.get(i) != b[i] ) return false;
        }

        return true;
    }

    public static boolean arraysEqual(ArrayList a, float[] b){
        if ( a.size() != b.length ) return false;

        for ( int i = 0, l = a.size(); i < l; i ++ ) {
            if ( (float)a.get(i) != b[i] ) return false;
        }

        return true;
    }

    protected static void copyArray(ArrayList a, int[] b){
        for ( int i = 0, l = b.length; i < l; i ++ ) {
            a.add(i, b[i]);
        }
    }

    protected static void copyArray(ArrayList a, float[] b){
        for ( int i = 0, l = b.length; i < l; i ++ ) {
            a.add(i, b[i]);
        }
    }

    protected static void allocTexUnits(GLRenderer renderer, int n){

    }



}
