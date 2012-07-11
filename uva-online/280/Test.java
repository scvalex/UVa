public class Test {
    public static void main (String[] args) {
        int n = 100;
        if (args.length == 1)
            n = Integer.parseInt(args[0]);
        System.out.println(n);
        for (int i=1;i<=n;i++) {
            System.out.print(i + " ");
            for (int j=1;j<=n;j++)
                if (i!=j) {
                    System.out.print(j + " ");
                }
            System.out.println(0);
                    
        }

        System.out.println(0);
        System.out.print((n * 100) + " ");
        for (int k=0;k<100;k++) {
            for (int i=1;i<=n;i++) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.print(0);
    }
}