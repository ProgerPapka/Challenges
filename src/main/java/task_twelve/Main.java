package task_twelve;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            boolean flag = false;
            if (Boolean.parseBoolean(args[0])) {
                flag = true;
            }
            new PostgresTest().process(flag);
        }
    }
}