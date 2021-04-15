/*
 * Copyright (C) 2021 oliver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author oliver
 */
public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();

//    private Product product;
//    private Review[] reviews = new Review[5];
    private Locale locale;
    private ResourceBundle resources;

    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);

    }

    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    public void printProductReport() {

        for (Product product : products.keySet()) {
            printProductReport(product);
        }
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);

        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRatingString(),
                dateFormat.format(product.getBestBefore())
        ));
        txt.append('\n');
        if (reviews.isEmpty()) {
            txt.append(resources.getString("no.reviews"));
            txt.append('\n');
        } else {
            Collections.sort(reviews);
            for (Review review : reviews) {
                txt.append(MessageFormat.format(resources.getString("review"),
                        review.getRaiting().getStars(),
                        review.getComments()
                ));
                txt.append('\n');
            }
        }
        System.out.println(txt);
    }

    public Product createProduct(LocalDate bestBefore, int id, String name, BigDecimal price, Rating rating) {
        Product product = new Food(bestBefore, id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<Review>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<Review>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price) {
        Product product = new Drink(id, name, price);
        products.putIfAbsent(product, new ArrayList<Review>());
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {

        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        int sum = 0;

        for (Review rev : reviews) {
            sum += rev.getRaiting().ordinal();
        }

        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);

        return product;

    }

    public Product reviewProduct(int idProduct, Rating rating, String comments) {
        return reviewProduct(findProduct(idProduct), rating, comments);
    }

    public Product findProduct(int id) {
        Product result = null;
        for (Product product : products.keySet()) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }

        return result;
    }
}
