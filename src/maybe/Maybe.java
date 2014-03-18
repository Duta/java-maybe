package maybe;

/**
 * Represents an <a href="http://bit.ly/1bkU5sM">option type</a>.
 * It comes in two forms, Some and None.
 */
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

    /**
     * Constructs a Some with the given data.
     *
     * @param data the data to store in the Some
     * @return a Some containing the given data
     * @throws MaybeException if the given data is null
     */
    public static <T> Maybe<T> some(T data) {
        if(data == null) {
            throw new MaybeException(
                "Can't have Some(null)");
        }
        return new Maybe<T>(data);
    }

    /**
     * If the given data is null, returns a None.
     * Otherwise, returns a Some with the given data.
     *
     * @param data the data to store
     * @return a Some containing the given data
     *         if it is non-null, a None otherwise
     */
    public static <T> Maybe<T> perhaps(T data) {
        return data != null
            ? Maybe.some(data)
            : Maybe.<T>none();
    }

    /**
     * Constructs a None.
     *
     * @return a None
     */
    public static <T> Maybe<T> none() {
        return new Maybe<T>();
    }

    /**
     * "Or"s the Maybe.
     * If this is a None, returns a Some
     * containing <code>alternateData</code>.
     * Otherwise, just returns this.
     *
     * @param alternateData the data to use is this is a None
     * @return a Some containing alternateData
     *         if this is a None, this otherwise
     */
    public Maybe<T> or(T alternateData) {
        return none
            ? Maybe.some(alternateData)
            : this;
    }

    /**
     * "Or"s the Maybe.
     * If this is a None, returns <code>alternateMaybe</code>.
     * Otherwise, just returns this.
     *
     * @param alternateMaybe the Maybe to use is this is a None
     * @return alternateMaybe if this is a None, this otherwise
     */
    public Maybe<T> or(Maybe<T> alternateMaybe) {
        return none ? alternateMaybe : this;
    }

    /**
     * Returns <code>true</code> if this is a Some.
     *
     * @return <code>true</code> if this is a Some
     * @see #isNone()
     */
    public boolean isSome() {
        return !none;
    }

    /**
     * Returns <code>true</code> if this is a None.
     *
     * @return <code>true</code> if this is a None
     * @see #isSome()
     */
    public boolean isNone() {
        return none;
    }

    /**
     * Returns this Some's data.
     *
     * @return the data contained in this Some
     * @throws MaybeException if this is None
     */
    public T get() {
        if(none) {
            throw new MaybeException(
                "Can't get from None");
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