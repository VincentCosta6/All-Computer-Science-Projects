
package area;
public class Area {
    public static void main(String[] args) 
    {
       int side = 4;
       int area = areaofsquare(side);
       System.out.println("area of triangle with side = " + side + " is " + side); 
       area = areaofrectangle(5,8);
       System.out.println("area of triangle with side = " + side + " is " + area);
       int base = 4;
       double areaTri = areaoftriangle(base,6);
       System.out.println("area of triangle with side = " + side + " is " + areaTri);
    }

    public static int areaofsquare(int area);
    {
                int side = side * 2;
                return (side);
                
   }
   public static int areaofrectangle(int width, int length)
   {
                int areaofrectangle = width * length;
                return (areaofrectangle);
   }
   public static double areaoftriangle(int base, int height)
   {
               double areaTri = base * height / 2.0;
               return (areaTri);
   }
   public static int areaoftrapezoid(int base3, int base4,int height1)
   {
                int base = 4;
                int base2 = 6;
                int height = 2;
                int areaoftrapezoid = (base + base2) * height/2;
                System.out.println(areaoftrapezoid);
                       
   }
 }
