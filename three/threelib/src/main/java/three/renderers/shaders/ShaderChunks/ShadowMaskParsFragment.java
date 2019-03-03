package three.renderers.shaders.ShaderChunks;

public class ShadowMaskParsFragment {
    public static final String code =
            "float getShadowMask() {\n" +
            "\n" +
            "\tfloat shadow = 1.0;\n" +
            "\n" +
            "\t#ifdef USE_SHADOWMAP\n" +
            "\n" +
            "\t#if NUM_DIR_LIGHTS > 0\n" +
            "\n" +
            "\tDirectionalLight directionalLight;\n" +
            "\n" +
            "\t#pragma unroll_loop\n" +
            "\tfor ( int i = 0; i < NUM_DIR_LIGHTS; i ++ ) {\n" +
            "\n" +
            "\t\tdirectionalLight = directionalLights[ i ];\n" +
            "\t\tshadow *= bool( directionalLight.shadow ) ? getShadow( directionalShadowMap[ i ], directionalLight.shadowMapSize, directionalLight.shadowBias, directionalLight.shadowRadius, vDirectionalShadowCoord[ i ] ) : 1.0;\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\t#endif\n" +
            "\n" +
            "\t#if NUM_SPOT_LIGHTS > 0\n" +
            "\n" +
            "\tSpotLight spotLight;\n" +
            "\n" +
            "\t#pragma unroll_loop\n" +
            "\tfor ( int i = 0; i < NUM_SPOT_LIGHTS; i ++ ) {\n" +
            "\n" +
            "\t\tspotLight = spotLights[ i ];\n" +
            "\t\tshadow *= bool( spotLight.shadow ) ? getShadow( spotShadowMap[ i ], spotLight.shadowMapSize, spotLight.shadowBias, spotLight.shadowRadius, vSpotShadowCoord[ i ] ) : 1.0;\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\t#endif\n" +
            "\n" +
            "\t#if NUM_POINT_LIGHTS > 0\n" +
            "\n" +
            "\tPointLight pointLight;\n" +
            "\n" +
            "\t#pragma unroll_loop\n" +
            "\tfor ( int i = 0; i < NUM_POINT_LIGHTS; i ++ ) {\n" +
            "\n" +
            "\t\tpointLight = pointLights[ i ];\n" +
            "\t\tshadow *= bool( pointLight.shadow ) ? getPointShadow( pointShadowMap[ i ], pointLight.shadowMapSize, pointLight.shadowBias, pointLight.shadowRadius, vPointShadowCoord[ i ], pointLight.shadowCameraNear, pointLight.shadowCameraFar ) : 1.0;\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\t#endif\n" +
            "\n" +
            "\t/*\n" +
            "\t#if NUM_RECT_AREA_LIGHTS > 0\n" +
            "\n" +
            "\t\t// TODO (abelnation): update shadow for Area light\n" +
            "\n" +
            "\t#endif\n" +
            "\t*/\n" +
            "\n" +
            "\t#endif\n" +
            "\n" +
            "\treturn shadow;\n" +
            "\n" +
            "}\n";
}
