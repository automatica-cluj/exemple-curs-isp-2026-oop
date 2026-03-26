package utcluj.isp.curs3.oop.interfaces4;

interface Shape {
    double getArea();
    double getPerimeter();
}

interface Drawable {
    void draw();
}

class Circle implements Shape, Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

class Rectangle implements Shape, Drawable {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with length " + length + " and width " + width);
    }
}

class DrawingBoard{
    private Drawable[] shapes;

    public DrawingBoard(Drawable[] shapes) {
        this.shapes = shapes;
    }

    public void drawShapes() {
        for (Drawable shape : shapes) {
            Drawable drawable = (Drawable) shape;
            drawable.draw();
        }
    }
}

public class InterfaceExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(3, 4);

        Drawable drawable1 = (Drawable) circle;
        Drawable drawable2 = (Drawable) rectangle;

        circle.getArea();
        circle.getPerimeter();
        drawable1.draw();

        rectangle.getArea();
        rectangle.getPerimeter();
        drawable2.draw();

        Drawable[] shapes = new Drawable[10];
        for (int i = 0; i < shapes.length; i++) {
            if (i % 2 == 0) {
                shapes[i] = new Circle(i + 1);
            } else {
                shapes[i] = new Rectangle(i + 1, i + 2);
            }
        }

        DrawingBoard board = new DrawingBoard(shapes);
        board.drawShapes();
    }
}
