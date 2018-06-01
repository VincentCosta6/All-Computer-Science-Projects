package shapes;

public class Shapes {

    public static void main(String[] args) {
        Polygon objects[] = new Polygon[3];
        objects[0] = new Rectangle(4,5,2);
        objects[1] = new Triangle(3,8,4);
        objects[2] = new Square(4,7);
        printInfo(objects);
    }
    public static void printInfo(Polygon objects[])
    {
        for (Polygon obj : objects)
        {
            System.out.println("Area of " + obj.getName() + " is " + obj.getArea());
        }
    }
}

abstract class Polygon
{
    private String name;
    private double width;
    private int sides;
    Polygon(int _sides,double _width)
    {
        sides = _sides;
        width = _width;
    }
    abstract double getArea();
    public double getWidth()
    {
        return(width);
    }
    public int getSides()
    {
        return(sides);
    }
    public String getName()
    {
        return(name);
    }
    public void setName(String _name)
    {
        name = _name;
    }
}
class Rectangle extends Polygon
{
    private double height;
    Rectangle(int _sides,int _width,int _height)
    {
        super(_sides,_width);
        height = _height;
        setName("Rectangle");
    }
    public double getArea()
    {
        return(height * getWidth());
    }
}
class Square extends Polygon
{
    Square(int _sides,int _width)
    {
        super(_sides,_width);
        setName("Square");
    }
    public double getArea()
    {
        return(getWidth() * getWidth());
    }
}
class Triangle extends Polygon
{
    private double height;
    Triangle(int _sides,int _width,int _height)
    {
        super(_sides,_width);
        height = _height;
        setName("Triangle");
    }
    public double getArea()
    {
        return(height * getWidth() * .5);
    }
}
