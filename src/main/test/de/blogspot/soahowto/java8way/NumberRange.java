package de.blogspot.soahowto.java8way;

import java.util.Iterator;

public abstract class NumberRange<N extends Number & Comparable<N>> implements Iterable<N> {

    public abstract static class NumberRangeIterator<N extends Number & Comparable<N>> implements Iterator<N> {

        protected N currentIndex;

        private N maxIndex;

        @Override
        public boolean hasNext() {
            return currentIndex.compareTo(maxIndex) == -1;
        }

    }

    public static class LongRangeIterator extends NumberRangeIterator<Long> {

        @Override
        public Long next() {
            return currentIndex++;
        }

    }
}
