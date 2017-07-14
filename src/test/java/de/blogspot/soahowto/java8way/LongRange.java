package de.blogspot.soahowto.java8way;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LongRange implements Iterable<Long> {

    private long from;

    private long to;

    public LongRange(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public static class LongRangeIterator implements Iterator<Long> {

        private long currentIndex;

        private long maxIndex;

        public LongRangeIterator(long from, long to) {
            currentIndex = from;
            maxIndex = to;
        }

        @Override
        public boolean hasNext() {
            return currentIndex != maxIndex;
        }

        @Override
        public Long next() {
            if (hasNext()) {
                return currentIndex++;
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public Iterator<Long> iterator() {
        return new LongRangeIterator(from, to);
    }

}
