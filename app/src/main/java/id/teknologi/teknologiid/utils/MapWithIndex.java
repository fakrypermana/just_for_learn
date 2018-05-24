package id.teknologi.teknologiid.utils;

import java.util.Iterator;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;

/**
 * Created by galihgasur on 10/9/17.
 */

public class MapWithIndex<T> implements ObservableTransformer<T, MapWithIndex.Indexed<T>> {
    @Override
    public ObservableSource<Indexed<T>> apply(@NonNull Observable<T> upstream) {
        return upstream.zipWith(NaturalNumbers.instance(), Indexed::new);
    }

    private static class Holder {
        static final MapWithIndex<?> INSTANCE = new MapWithIndex<Object>();
    }

    @SuppressWarnings("unchecked")
    public static <T> MapWithIndex<T> instance() {
        return (MapWithIndex<T>) Holder.INSTANCE;
    }


    public static final class Indexed<T> {
        private final long index;
        private final T value;

        public Indexed(T value, long index) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return index + "->" + value;
        }

        public long index() {
            return index;
        }

        public T value() {
            return value;
        }

    }

    private static final class NaturalNumbers implements Iterable<Long> {

        private static class Holder {
            static final NaturalNumbers INSTANCE = new NaturalNumbers();
        }

        static NaturalNumbers instance() {
            return Holder.INSTANCE;
        }

        @Override
        public Iterator<Long> iterator() {
            return new Iterator<Long>() {

                private long n = 0;

                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public Long next() {
                    return n++;
                }

                @Override
                public void remove() {
                    throw new RuntimeException("not supported");
                }
            };
        }

    }
}
