public class MaybeExample {
    public static void main(String[] args) {
        Maybe<Integer> three = Maybe.some(3);
        // Prints "3"
        print(three.isSome() ? three.get() : "None");
        // Prints "Some(3)"
        print(three);

        Maybe<Integer> none = Maybe.none();
        // Prints "None"
        print(none.isSome() ? none.get() : "None");
        // Prints "None"
        print(none);

        // If you want to be explicit, say:
        // Maybe.<Integer>none()
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}