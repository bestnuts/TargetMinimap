package me.bestnuts.api.minimap.shape;

public enum Shape {
    Circle(new CircleShape()),
    Square(new SquareShape());

    private final MinimapShape shape;

    Shape(MinimapShape shape) {
        this.shape = shape;
    }

    public MinimapShape getShape() {
        return shape;
    }
}
