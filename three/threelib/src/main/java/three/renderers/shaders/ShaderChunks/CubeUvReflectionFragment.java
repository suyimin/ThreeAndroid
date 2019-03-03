package three.renderers.shaders.ShaderChunks;

public class CubeUvReflectionFragment {
    public static final String code =
            "#ifdef ENVMAP_TYPE_CUBE_UV\n" +
            "\n" +
            "#define cubeUV_textureSize (1024.0)\n" +
            "\n" +
            "int getFaceFromDirection(vec3 direction) {\n" +
            "\tvec3 absDirection = abs(direction);\n" +
            "\tint face = -1;\n" +
            "\tif( absDirection.x > absDirection.z ) {\n" +
            "\t\tif(absDirection.x > absDirection.y )\n" +
            "\t\t\tface = direction.x > 0.0 ? 0 : 3;\n" +
            "\t\telse\n" +
            "\t\t\tface = direction.y > 0.0 ? 1 : 4;\n" +
            "\t}\n" +
            "\telse {\n" +
            "\t\tif(absDirection.z > absDirection.y )\n" +
            "\t\t\tface = direction.z > 0.0 ? 2 : 5;\n" +
            "\t\telse\n" +
            "\t\t\tface = direction.y > 0.0 ? 1 : 4;\n" +
            "\t}\n" +
            "\treturn face;\n" +
            "}\n" +
            "#define cubeUV_maxLods1  (log2(cubeUV_textureSize*0.25) - 1.0)\n" +
            "#define cubeUV_rangeClamp (exp2((6.0 - 1.0) * 2.0))\n" +
            "\n" +
            "vec2 MipLevelInfo( vec3 vec, float roughnessLevel, float roughness ) {\n" +
            "\tfloat scale = exp2(cubeUV_maxLods1 - roughnessLevel);\n" +
            "\tfloat dxRoughness = dFdx(roughness);\n" +
            "\tfloat dyRoughness = dFdy(roughness);\n" +
            "\tvec3 dx = dFdx( vec * scale * dxRoughness );\n" +
            "\tvec3 dy = dFdy( vec * scale * dyRoughness );\n" +
            "\tfloat d = max( dot( dx, dx ), dot( dy, dy ) );\n" +
            "\t// Clamp the value to the max mip level counts. hard coded to 6 mips\n" +
            "\td = clamp(d, 1.0, cubeUV_rangeClamp);\n" +
            "\tfloat mipLevel = 0.5 * log2(d);\n" +
            "\treturn vec2(floor(mipLevel), fract(mipLevel));\n" +
            "}\n" +
            "\n" +
            "#define cubeUV_maxLods2 (log2(cubeUV_textureSize*0.25) - 2.0)\n" +
            "#define cubeUV_rcpTextureSize (1.0 / cubeUV_textureSize)\n" +
            "\n" +
            "vec2 getCubeUV(vec3 direction, float roughnessLevel, float mipLevel) {\n" +
            "\tmipLevel = roughnessLevel > cubeUV_maxLods2 - 3.0 ? 0.0 : mipLevel;\n" +
            "\tfloat a = 16.0 * cubeUV_rcpTextureSize;\n" +
            "\n" +
            "\tvec2 exp2_packed = exp2( vec2( roughnessLevel, mipLevel ) );\n" +
            "\tvec2 rcp_exp2_packed = vec2( 1.0 ) / exp2_packed;\n" +
            "\t// float powScale = exp2(roughnessLevel + mipLevel);\n" +
            "\tfloat powScale = exp2_packed.x * exp2_packed.y;\n" +
            "\t// float scale =  1.0 / exp2(roughnessLevel + 2.0 + mipLevel);\n" +
            "\tfloat scale = rcp_exp2_packed.x * rcp_exp2_packed.y * 0.25;\n" +
            "\t// float mipOffset = 0.75*(1.0 - 1.0/exp2(mipLevel))/exp2(roughnessLevel);\n" +
            "\tfloat mipOffset = 0.75*(1.0 - rcp_exp2_packed.y) * rcp_exp2_packed.x;\n" +
            "\n" +
            "\tbool bRes = mipLevel == 0.0;\n" +
            "\tscale =  bRes && (scale < a) ? a : scale;\n" +
            "\n" +
            "\tvec3 r;\n" +
            "\tvec2 offset;\n" +
            "\tint face = getFaceFromDirection(direction);\n" +
            "\n" +
            "\tfloat rcpPowScale = 1.0 / powScale;\n" +
            "\n" +
            "\tif( face == 0) {\n" +
            "\t\tr = vec3(direction.x, -direction.z, direction.y);\n" +
            "\t\toffset = vec2(0.0+mipOffset,0.75 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? a : offset.y;\n" +
            "\t}\n" +
            "\telse if( face == 1) {\n" +
            "\t\tr = vec3(direction.y, direction.x, direction.z);\n" +
            "\t\toffset = vec2(scale+mipOffset, 0.75 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? a : offset.y;\n" +
            "\t}\n" +
            "\telse if( face == 2) {\n" +
            "\t\tr = vec3(direction.z, direction.x, direction.y);\n" +
            "\t\toffset = vec2(2.0*scale+mipOffset, 0.75 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? a : offset.y;\n" +
            "\t}\n" +
            "\telse if( face == 3) {\n" +
            "\t\tr = vec3(direction.x, direction.z, direction.y);\n" +
            "\t\toffset = vec2(0.0+mipOffset,0.5 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? 0.0 : offset.y;\n" +
            "\t}\n" +
            "\telse if( face == 4) {\n" +
            "\t\tr = vec3(direction.y, direction.x, -direction.z);\n" +
            "\t\toffset = vec2(scale+mipOffset, 0.5 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? 0.0 : offset.y;\n" +
            "\t}\n" +
            "\telse {\n" +
            "\t\tr = vec3(direction.z, -direction.x, direction.y);\n" +
            "\t\toffset = vec2(2.0*scale+mipOffset, 0.5 * rcpPowScale);\n" +
            "\t\toffset.y = bRes && (offset.y < 2.0*a) ? 0.0 : offset.y;\n" +
            "\t}\n" +
            "\tr = normalize(r);\n" +
            "\tfloat texelOffset = 0.5 * cubeUV_rcpTextureSize;\n" +
            "\tvec2 s = ( r.yz / abs( r.x ) + vec2( 1.0 ) ) * 0.5;\n" +
            "\tvec2 base = offset + vec2( texelOffset );\n" +
            "\treturn base + s * ( scale - 2.0 * texelOffset );\n" +
            "}\n" +
            "\n" +
            "#define cubeUV_maxLods3 (log2(cubeUV_textureSize*0.25) - 3.0)\n" +
            "\n" +
            "vec4 textureCubeUV( sampler2D envMap, vec3 reflectedDirection, float roughness ) {\n" +
            "\tfloat roughnessVal = roughness* cubeUV_maxLods3;\n" +
            "\tfloat r1 = floor(roughnessVal);\n" +
            "\tfloat r2 = r1 + 1.0;\n" +
            "\tfloat t = fract(roughnessVal);\n" +
            "\tvec2 mipInfo = MipLevelInfo(reflectedDirection, r1, roughness);\n" +
            "\tfloat s = mipInfo.y;\n" +
            "\tfloat level0 = mipInfo.x;\n" +
            "\tfloat level1 = level0 + 1.0;\n" +
            "\tlevel1 = level1 > 5.0 ? 5.0 : level1;\n" +
            "\n" +
            "\t// round to nearest mipmap if we are not interpolating.\n" +
            "\tlevel0 += min( floor( s + 0.5 ), 5.0 );\n" +
            "\n" +
            "\t// Tri linear interpolation.\n" +
            "\tvec2 uv_10 = getCubeUV(reflectedDirection, r1, level0);\n" +
            "\tvec4 color10 = envMapTexelToLinear(texture2D(envMap, uv_10));\n" +
            "\n" +
            "\tvec2 uv_20 = getCubeUV(reflectedDirection, r2, level0);\n" +
            "\tvec4 color20 = envMapTexelToLinear(texture2D(envMap, uv_20));\n" +
            "\n" +
            "\tvec4 result = mix(color10, color20, t);\n" +
            "\n" +
            "\treturn vec4(result.rgb, 1.0);\n" +
            "}\n" +
            "\n" +
            "#endif\n";
}
