#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>
#moj_import <minecraft:globals.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;

uniform sampler2D Sampler0;
uniform sampler2D Sampler2;

out float sphericalVertexDistance;
out float cylindricalVertexDistance;
out vec4 vertexColor;
out vec2 texCoord0;
flat out int isRadar;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    sphericalVertexDistance = fog_spherical_distance(Position);
    cylindricalVertexDistance = fog_cylindrical_distance(Position);
    vertexColor = Color * texelFetch(Sampler2, UV2 / 16, 0);
    texCoord0 = UV0;

    isRadar = 0;
    ivec4 ltColor = ivec4(round(texelFetch(Sampler0, ivec2(0), 0) * 255.0));

    if (ltColor == ivec4(1, 2, 3, 4)) {
        float x = Color.r;
        float y = Color.g;
        float scale = Color.b * 255.0 * 2.0;

        vec3 ndcPos = gl_Position.xyz / gl_Position.w;

        float pixelSizeX = 1.0 / ScreenSize.x;
        float pixelSizeY = 1.0 / ScreenSize.y;

        ndcPos.x += x * pixelSizeX * scale;
        ndcPos.y += y * pixelSizeY * scale;

        gl_Position.xyz = ndcPos * gl_Position.w;
        isRadar = 1;
    }
}