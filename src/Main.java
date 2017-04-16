import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void test1() {
        naive_vebtree tree = new naive_vebtree(20);
        tree.add(5);
        tree.add(11);
        tree.add(10);
        System.out.println(tree.next(5));
        tree.remove(10);
        System.out.println(tree.next(5));
    }

    public static boolean test2() {
        final int K = 10000;
        final int M = 10000000;
        VEBTree vb = new VEBTree(20);
        Set<Long> s = new TreeSet<>();
        for (int i = 0; i < K; i++) {
            Double d = Math.random() * M;
            Long ld = d.longValue();
            vb.add(ld);
            s.add(ld);
        }
        for (long i = 0; i < M; i++) {
            if (s.contains(i) != vb.contains(i)) return false;
        }
        return true;
    }

    public static boolean test3() {
        VEBTree vb = new VEBTree(3);
        vb.add(7);
        vb.add (6);
        vb.add (2);
        vb.remove (2);
        vb.add (3);
        vb.remove (6);
        return !vb.contains(6);
    }

    public static boolean test4() {
        final int K = 100000;
        final int M = 1000;
        VEBTree vb = new VEBTree(10);
        Set<Long> s = new TreeSet<>();
        for (int i = 0; i < K; i++) {
            Double d = Math.random() * M;
            Long ld = d.longValue();
            Double flagd = Math.random() * M;
            Boolean flag = flagd.intValue() % 2 == 0;
            if (flag) {
                //System.out.print(" add " + ld);
                vb.add(ld);
                s.add(ld);
            } else {
                //System.out.print(" remove " + ld);
                vb.remove(ld);
                s.remove(ld);
            }
            if (s.contains(ld) != vb.contains(ld)) {
                System.out.println();
                System.out.println(s.contains(ld));
                System.out.println(vb.contains(ld));
                System.out.println(ld);
                return false;
            }
        }
        for (long i = 0; i < M; i++) {
            if (s.contains(i) != vb.contains(i)) {
                System.out.println(s.contains(i));
                System.out.println(vb.contains(i));
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static void test5() {
        VEBTree vb = new VEBTree(20);
        for (int i= 0; i < 10; i++) {
            vb.add(i);
        }
        System.out.println(vb.next(0));
    }

    public static boolean test6() {
        final int K = 10000;
        final int M = 1000000;
        VEBTree vb = new VEBTree(20);
        Set<Long> s = new TreeSet<>();
        for (int i = 0; i < K; i++) {
            Double d = Math.random() * M;
            Long ld = d.longValue();
            vb.add(ld);
            s.add(ld);
        }
        for (long i = 0; i < M; i++) {
            long x = vb.next(i);
            if (s.contains(x) != vb.contains(x)) return false;
        }
        return true;
    }

    public static void test7() {
        VEBTree vb = new VEBTree(20);
        for (int i= 0; i < 10; i++) {
            vb.add(i);
        }
        System.out.println(vb.prev(9));
    }

    public static boolean test8() {
        final int K = 10000;
        final int M = 1000000;
        VEBTree vb = new VEBTree(20);
        Set<Long> s = new TreeSet<>();
        for (int i = 0; i < K; i++) {
            Double d = Math.random() * M;
            Long ld = d.longValue();
            vb.add(ld);
            s.add(ld);
        }
        for (long i = 0; i < M; i++) {
            long x = vb.prev(i);
            if (s.contains(x) != vb.contains(x)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(test2());
        System.out.println(test4());
        System.out.println(test6());
        System.out.println(test8());
    }
}
