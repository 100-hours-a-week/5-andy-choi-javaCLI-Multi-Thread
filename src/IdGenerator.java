public class IdGenerator {
    private static Long id = 0L;

    public static Long setId() {
        id += 1L;
        return IdGenerator.id;
    }
}
