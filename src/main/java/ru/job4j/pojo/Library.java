package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book clean = new Book("Clean code", 431);
        Book shildt = new Book("Java: The complete Reference", 1882);
        Book head = new Book("Head First Java", 688);
        Book horstmann = new Book("Core Java Vol.1", 1588);
        Book[] books = new Book[4];
        books[0] = clean;
        books[1] = shildt;
        books[2] = head;
        books[3] = horstmann;
        for (Book book : books) {
            System.out.println(book.getName() + " - " + book.getCount());
        }
        System.out.println();
        System.out.println("The books after replace:");
        books[0] = horstmann;
        books[3] = clean;
        for (Book book : books) {
            System.out.println(book.getName() + " - " + book.getCount());
        }
        System.out.println();
        System.out.println("Show only with \"Clean code\"");
        for (Book book : books) {
            if (book.getName().equals("Clean code")) {
                System.out.println(book.getName() + " - " + book.getCount());
            }
        }
    }
}
