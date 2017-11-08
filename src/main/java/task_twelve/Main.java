package task_twelve;

public class Main {
    public static void main(String[] args) {
        boolean flag = false;
        if(Boolean.parseBoolean(args[0]))
            flag = true;
        new PostgresTest().process(flag);
    }
}