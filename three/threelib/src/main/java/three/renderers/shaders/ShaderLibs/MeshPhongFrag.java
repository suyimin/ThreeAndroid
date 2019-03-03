package three.renderers.shaders.ShaderLibs;

public class MeshPhongFrag {
    public static final String code =
            "#define PHONG\n" +
            "\n" +
            "uniform vec3 diffuse;\n" +
            "uniform vec3 emissive;\n" +
            "uniform vec3 specular;\n" +
            "uniform float shininess;\n" +
            "uniform float opacity;\n" +
            "\n" +
            "#include <common>\n" +
            "#include <packing>\n" +
            "#include <dithering_pars_fragment>\n" +
            "#include <color_pars_fragment>\n" +
            "#include <uv_pars_fragment>\n" +
            "#include <uv2_pars_fragment>\n" +
            "#include <map_pars_fragment>\n" +
            "#include <alphamap_pars_fragment>\n" +
            "#include <aomap_pars_fragment>\n" +
            "#include <lightmap_pars_fragment>\n" +
            "#include <emissivemap_pars_fragment>\n" +
            "#include <envmap_pars_fragment>\n" +
            "#include <gradientmap_pars_fragment>\n" +
            "#include <fog_pars_fragment>\n" +
            "#include <bsdfs>\n" +
            "#include <lights_pars_begin>\n" +
            "#include <lights_phong_pars_fragment>\n" +
            "#include <shadowmap_pars_fragment>\n" +
            "#include <bumpmap_pars_fragment>\n" +
            "#include <normalmap_pars_fragment>\n" +
            "#include <specularmap_pars_fragment>\n" +
            "#include <logdepthbuf_pars_fragment>\n" +
            "#include <clipping_planes_pars_fragment>\n" +
            "\n" +
            "void main() {\n" +
            "\n" +
            "\t#include <clipping_planes_fragment>\n" +
            "\n" +
            "\tvec4 diffuseColor = vec4( diffuse, opacity );\n" +
            "\tReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );\n" +
            "\tvec3 totalEmissiveRadiance = emissive;\n" +
            "\n" +
            "\t#include <logdepthbuf_fragment>\n" +
            "\t#include <map_fragment>\n" +
            "\t#include <color_fragment>\n" +
            "\t#include <alphamap_fragment>\n" +
            "\t#include <alphatest_fragment>\n" +
            "\t#include <specularmap_fragment>\n" +
            "\t#include <normal_fragment_begin>\n" +
            "\t#include <normal_fragment_maps>\n" +
            "\t#include <emissivemap_fragment>\n" +
            "\n" +
            "\t// accumulation\n" +
            "\t#include <lights_phong_fragment>\n" +
            "\t#include <lights_fragment_begin>\n" +
            "\t#include <lights_fragment_maps>\n" +
            "\t#include <lights_fragment_end>\n" +
            "\n" +
            "\t// modulation\n" +
            "\t#include <aomap_fragment>\n" +
            "\n" +
            "\tvec3 outgoingLight = reflectedLight.directDiffuse + reflectedLight.indirectDiffuse + reflectedLight.directSpecular + reflectedLight.indirectSpecular + totalEmissiveRadiance;\n" +
            "\n" +
            "\t#include <envmap_fragment>\n" +
            "\n" +
            "\tgl_FragColor = vec4( outgoingLight, diffuseColor.a );\n" +
            "\n" +
            "\t#include <tonemapping_fragment>\n" +
            "\t#include <encodings_fragment>\n" +
            "\t#include <fog_fragment>\n" +
            "\t#include <premultiplied_alpha_fragment>\n" +
            "\t#include <dithering_fragment>\n" +
            "\n" +
            "}";
}
