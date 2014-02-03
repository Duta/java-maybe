public class Maybe<T> {
    private final T data;
    private final boolean none;

    private Maybe() {
        this.data = null;
        this.none = true;
    }

    private Maybe(T data) {
        this.data = data;
        this.none = false;
    }

    public static <T> Maybe<T> some(T data) {
        return new Maybe<T>(data);
    }

    public static <T> Maybe<T> none() {
        return new Maybe<T>();
    }

    public boolean isSome() {
        return !none;
    }

    public boolean isNone() {
        return none;
    }

    public T get() {
        if(none) {
            throw new MaybeException(
                "Can't get from none");
        }
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Maybe)) {
            return false;
        }
        Maybe that = (Maybe)o;
        return this.none == that.none
            && this.data == that.data;
    }

    @Override
    public String toString() {
        return none
            ? "None"
            : "Some(" + data + ")";
    }
}