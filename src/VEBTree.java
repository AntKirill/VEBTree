import java.util.Map;
import java.util.TreeMap;

public class VEBTree implements IntegerSet {

    private Map<Long, VEBTree> ch;
    private VEBTree aux;
    private Long min;
    private Long max;
    private final int S;

    public VEBTree(int S) {
        int pow = 0;
        int i = 0;
        while (pow < S) {
            pow = (1 << i);
            ++i;
        }
        this.S = pow;
        ch = new TreeMap<>();
        aux = null;
        min = null;
    }

    private VEBTree() {
        S = 1;
    }

    public boolean empty() {
        return min == null;
    }

    public boolean contains(long x) {
        return !empty() && (min.equals(x) || max.equals(x) || ch.containsKey(hi(x)) && ch.get(hi(x)).contains(lo(x)));
    }

    private long hi(final long x) {
        return x >> (S / 2);
    }

    private long lo(final long x) {
        return x & (((long) 1 << (S / 2)) - 1);
    }

    private void addToAux(long x) {
        if (aux == null) aux = new VEBTree(S / 2);
        aux.add(x);
    }

    private void addChildren(long high, long low) {
        if (!ch.containsKey(high)) {
            ch.put(high, new VEBTree(S / 2));
        }
        ch.get(high).add(low);
    }

    private VEBTree getChildren(long high) {
        if (!ch.containsKey(high)) return new VEBTree();
        return ch.get(high);
    }

    @Override
    public void add(long x) {
        if (empty()) {
            min = x;
            max = x;
        } else {
            if (min.equals(max)) {
                if (min < x) max = x;
                else min = x;
            } else {
                if (min > x) {
                    long tmp = min;
                    min = x;
                    x = tmp;
                }
                if (x == min) return;
                if (max < x) {
                    long tmp = max;
                    max = x;
                    x = tmp;
                }
                if (x == max) return;
                if (S != 1) {
                    if (getChildren(hi(x)).empty()) {
                        addToAux(hi(x));
                    }
                    addChildren(hi(x), lo(x));
                }
            }
        }
    }

    private long merge(final long high, final long low) {
        return (high << (S / 2)) | (low);
    }

    @Override
    public void remove(long x) {
        if (empty()) return;
        if (min == x && max == x) {
            min = null;
            return;
        }
        if (min == x) {
            if (aux == null || aux.empty()) {
                min = max;
                return;
            }
            x = merge(aux.min,
                    getChildren(aux.min).min);

            min = x;
        }
        if (max == x) {
            if (aux == null || aux.empty()) {
                max = min;
                return;
            }
            x = merge(aux.max,
                    getChildren(aux.max).max);
            max = x;
        }
        if (aux == null || aux.empty()) {
            return;
        }
        getChildren(hi(x)).remove(lo(x));
        if (getChildren(hi(x)).empty()) {
            aux.remove(hi(x));
        }
    }

    @Override
    public long next(long x) {
        if (empty() || max <= x) {
            return NO;
        }
        if (min > x) {
            return min;
        }
        if (aux == null || aux.empty()) {
            return max;
        } else {
            if (!getChildren(hi(x)).empty() && getChildren(hi(x)).max > lo(x)) {
                return merge(hi(x), getChildren(hi(x)).next(lo(x)));
            } else {
                long nextHigh = aux.next(hi(x));
                if (nextHigh == NO) {
                    return max;
                } else {
                    return merge(nextHigh, getChildren(nextHigh).min);
                }
            }
        }
    }

    @Override
    public long prev(long x) {
        if (empty() || min >= x) {
            return NO;
        }
        if (max < x) {
            return max;
        }
        if (aux == null || aux.empty()) {
            return min;
        } else {
            if (!getChildren(hi(x)).empty() && getChildren(hi(x)).min < lo(x)) {
                return merge(hi(x), getChildren(hi(x)).prev(lo(x)));
            } else {
                long prevHigh = aux.prev(hi(x));
                if (prevHigh == NO) {
                    return min;
                } else {
                    return merge(prevHigh, getChildren(prevHigh).max);
                }
            }
        }
    }

    @Override
    public long getMin() {
        return empty() ? NO : min;
    }

    @Override
    public long getMax() {
        return empty() ? NO : max;
    }
}