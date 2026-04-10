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

    int ltAlpha = int(round(texelFetch(Sampler0, ivec2(0), 0).a * 255.0));
    int vertexId = gl_VertexID % 4;

    if (ltAlpha == 4) {
        float x = Color.r;
        float y = Color.g;
        float scale = Color.b * 255.0 * 2.0;

        vec3 ndcPos = gl_Position.xyz / gl_Position.w;
        ndcPos.xy += (vec2(x, y) * scale / ScreenSize.xy) * 2.0;
        gl_Position.xyz = ndcPos * gl_Position.w;
        isRadar = 1;
    } else if (ltAlpha == 5) {
        float x = Color.r * 464.0 - 232.0;
        float y = Color.g * 464.0 - 232.0;
        float theta = radians(Color.b * 360.0);

        float c = cos(theta);
        float s = sin(theta);
        mat2 rotMat = mat2(-c, -s, s, -c);

        const vec2[4] faceCoords = vec2[](
            vec2(-1.0,  1.0),
            vec2(-1.0, -1.0),
            vec2( 1.0, -1.0),
            vec2( 1.0,  1.0)
        );
        vec2 fc = faceCoords[vertexId];

        vec2 halfQuad = vec2(textureSize(Sampler0, 0)) * 0.5;

        vec3 ndcPos = gl_Position.xyz / gl_Position.w;
        ndcPos.xy += (vec2(x, y) / ScreenSize.xy) * 2.0;

        vec2 pixelPos = ndcPos.xy * ScreenSize.xy * 0.5;
        vec2 pivot = pixelPos - (fc * halfQuad + halfQuad);
        
        pixelPos = pivot + rotMat * (fc * halfQuad - halfQuad);
        pixelPos += vec2(x, y) * 2.0;

        ndcPos.xy = pixelPos / (ScreenSize.xy * 0.5);
        gl_Position.xyz = ndcPos * gl_Position.w;
        isRadar = 1;
    }
}
