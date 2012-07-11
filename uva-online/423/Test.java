public class Test {
    public static void main (String[] args) {
        int n = 200;
        if (args.length == 1)
            n = Integer.parseInt(args[0]);
            
        System.out.print(n);

        for (int i=0;i<n;i++)  {
            if ( i> 0) 
                System.out.println();
            for (int j=0;j<i;j++) {
                String val = (j % 11 == 0 ? "x" : (i + j) + "");
                System.out.print(val + " ");
            }
        }

    }
}