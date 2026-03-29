package chapter_09.interface_methods;

interface Validator {

    // 1. abstract — quem implementa é obrigado
    boolean isValid(String value);

    // 2. default — quem implementa pode sobrescrever ou não
    default void printResult(String value) {
        if (checkNotNull(value)) {          // chama o private
            System.out.println("Valid: " + isValid(value));
        }
    }

    // 3. static — pertence à interface, chama via Validator.create()
    static Validator create() {
        return value -> value != null && !value.isEmpty();
    }

    // 4. private — uso interno, reutiliza lógica entre default methods
    private boolean checkNotNull(String value) {
        return value != null;
    }

    // 5. private static — uso interno estático
    private static String sanitize(String value) {
        return value.trim().toLowerCase();
    }
}