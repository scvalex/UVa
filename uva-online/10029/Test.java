public class Test {
    public static void main (String[] args) {
	int n = 25000;
	if (args.length > 0)
	    n = Integer.parseInt(args[0]);
	for (int i=0;i<25000;i++){
	    System.out.print("abcdefghabcdefg");
	    if (i%2==0)
		System.out.println();
	    else 
		System.out.println("h");
	}
    }
	
}