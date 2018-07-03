
public class testerclasss2 extends Super{
	static int y = 40;
	
public static void main(String[] args)
{
	int i = 0 ;       boolean bool1 = true ;       boolean bool2 = false;       boolean bool  = false;       bool = ( bool2 &  method1(i++) );       bool = ( bool2 && method1(i++) );        bool = ( bool1 |  method1(i++) );       bool = ( bool1 || method1(i++) );      System.out.println(i);    }    public static boolean method1(int i){        return i>0 ? true : false;    }
}
	


