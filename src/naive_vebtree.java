
public class naive_vebtree implements IntegerSet {
    boolean[] a;

    public naive_vebtree(int s) {
        a = new boolean[1 << s];
    }

    @Override
    public void add(long x) {
        a[(int) x] = true;
    }

    @Override
    public void remove(long x) {
        a[(int) x] = false;
    }

    @Override
    public long next(long x) {
        int current = (int) (x + 1);
        while (current < a.length && !a[current]) {
            current++;
        }
        return current < a.length ? current : NO;
    }

    @Override
    public long prev(long x) {
        int current = (int) (x - 1);
        while (current >= 0 && !a[current]) {
            --current;
        }
        return current >= 0 ? current : NO;
    }

    @Override
    public long getMin() {
        int current = 0;
        while (current < a.length && !a[current]) {
            ++current;
        }
        return current < a.length ? current : NO;
    }

    @Override
    public long getMax() {
        int current = a.length - 1;
        while (current >= 0 && !a[current]) {
            ++current;
        }
        return current < a.length ? current : NO;
    }

}
