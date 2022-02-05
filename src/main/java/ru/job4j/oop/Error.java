package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Active: " + active);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
        System.out.println();
    }

    public static void main(String[] args) {
        Error err1 = new Error();
        Error err2 = new Error(true, 2, "Error2");
        Error err3 = new Error(false, 3, "Error3");
        Error err4 = new Error(true, 4, "Error4");
        err1.printInfo();
        err2.printInfo();
        err3.printInfo();
        err4.printInfo();

    }
}
