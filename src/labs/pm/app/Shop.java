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
package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import labs.pm.data.Drink;
import labs.pm.data.Food;
import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

/**
 * {@code Product}
 *
 * @author oliver
 */
public final class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        

        String[] names = {"Mary", "Jane", "Elizabeth", "Jo"};
        Arrays.sort(names, new Compare());
        for (String name : names) {
            System.out.println(name);
        }

        /*
        String[] names = {"Mary", "Jane", "Ann", "Tom"};
        Arrays.sort(names);
        int x = Arrays.binarySearch(names, "Ann");
        System.out.println(x);
         */
        ProductManager pm = new ProductManager(Locale.US);

        Product p2 = pm.createProduct(102, "meat", BigDecimal.valueOf(1.99), Rating.FIVE_STAR);

        //pm.printProductReport(p2);
        p2 = pm.reviewProduct(p2, Rating.FIVE_STAR, "best cup of tea1");

        p2 = pm.reviewProduct(p2, Rating.FOUR_STAR, "best cup of tea2");
        //pm.printProductReport(p2);
        p2 = pm.reviewProduct(p2, Rating.ONE_STAR, "best cup of tea3");

        p2 = pm.reviewProduct(p2, Rating.ONE_STAR, "best cup of tea4");
        //pm.printProductReport(p2);
        p2 = pm.reviewProduct(p2, Rating.FIVE_STAR, "best cup of tea5");
        p2 = pm.reviewProduct(p2, Rating.FIVE_STAR, "best cup of tea6");
        p2 = pm.reviewProduct(p2, Rating.FIVE_STAR, "best cup of tea7");
        //pm.printProductReport(p2);

        Product p1 = pm.createProduct(101, "tea", BigDecimal.valueOf(1.99));

        //pm.printProductReport(p1);
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "best cup of tea1");

        p1 = pm.reviewProduct(p1, Rating.FOUR_STAR, "best cup of tea2");
        //pm.printProductReport(p1);
        p1 = pm.reviewProduct(p1, Rating.ONE_STAR, "best cup of tea3");

        p1 = pm.reviewProduct(p1, Rating.ONE_STAR, "best cup of tea4");
        //pm.printProductReport(p1);
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "best cup of tea5");
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "best cup of tea6");
        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "best cup of tea7");
        //pm.printProductReport(p1);

        
        pm.changeLocale("es-GT");
        pm.printProductReport();
        /*
        Product p2 = pm.createProduct(LocalDate.now(),102,"cofee",BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        Product p3 = pm.createProduct(LocalDate.now(),103,"cake",BigDecimal.valueOf(1.99), Rating.FIVE_STAR);
        Product p4 = p1.applyRating(Rating.TWO_STAR);
        Object p6 = pm.createProduct(LocalDate.now(),114,"cake",BigDecimal.valueOf(1.99), Rating.FIVE_STAR);
        Product p7 = (Product) p6;
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p6.toString());
        System.out.println(p7.toData());
         */

    }

}
