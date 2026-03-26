package utcluj.aut.dp.behavioral.iterator;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

class BitSetIterator implements Iterator<Boolean> {
    private final BitSet bitset;
    private int index;

    public BitSetIterator(BitSet bitset) {
        this.bitset = bitset;
    }

    public boolean hasNext() {
        return index < bitset.length();
    }

    public Boolean next() {
        if (index >= bitset.length()) {
            throw new NoSuchElementException();
        }
        boolean b = bitset.get(index++);
        return b;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

public class TestClientBitSet {
    public static void main(String[] args) {
        BitSet bitset = new BitSet();
        bitset.set(1);
        bitset.set(19);
        bitset.set(20);
        bitset.set(47);
        BitSetIterator iter = new BitSetIterator(bitset);
        while (iter.hasNext()) {
            Boolean b = iter.next();
            String tf = (b ? "T" : "F");
            System.out.print(tf);
        }
        System.out.println();
    }
}
