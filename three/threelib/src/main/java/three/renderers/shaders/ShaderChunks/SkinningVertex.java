package three.renderers.shaders.ShaderChunks;

public class SkinningVertex {
    public static final String code =
            "#ifdef USE_SKINNING\n" +
            "\n" +
            "\tvec4 skinVertex = bindMatrix * vec4( transformed, 1.0 );\n" +
            "\n" +
            "\tvec4 skinned = vec4( 0.0 );\n" +
            "\tskinned += boneMatX * skinVertex * skinWeight.x;\n" +
            "\tskinned += boneMatY * skinVertex * skinWeight.y;\n" +
            "\tskinned += boneMatZ * skinVertex * skinWeight.z;\n" +
            "\tskinned += boneMatW * skinVertex * skinWeight.w;\n" +
            "\n" +
            "\ttransformed = ( bindMatrixInverse * skinned ).xyz;\n" +
            "\n" +
            "#endif\n";
}
