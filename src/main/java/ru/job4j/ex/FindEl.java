package ru.job4j.ex;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFound {
        int rsl = -1;
        for (int index = 0; index < value.length; index++) {
            if (key.equals(value[index])) {
                rsl = index;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFound("Element not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            int response = indexOf(new String[]{"1", "2", "3"}, "4");
            System.out.println(response);
        } catch (ElementNotFound exc) {
            exc.printStackTrace();
        }
    }
}
