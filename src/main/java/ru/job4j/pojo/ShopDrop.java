package ru.job4j.pojo;

public class ShopDrop {

    public static Product[] delete(Product[] products, int index) {
        products[index] =  null;
        for (int in = index; in < products.length - 1; in++) {
            products[in] = products[in + 1];
        }
        products[products.length - 1] = null;
        return products;
    }
}
