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
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.Objects;

/**
 * {@code Product} class represents properties and behaiviours of product
 * objects in the Product Management System <br>
 * Each product has an id, name, and price <br>
 * Each product can have a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 *
 * @version 4.0
 * @author oliver
 */
public abstract class Product implements Rateable{


    private int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;
    /**
     * A constant that defines a {@link java.math.BigDecimal BigDecimal} value
     * of the discount rate <br>
     * Discount rate is 10%;
     */
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * Calculates discount based on a product price and
     * {@link DISCOUNT_RATE discount rate}
     *
     * @return a {@link java.math.BigDecimal BigDecimal} value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract Product applyRating(Rating newRating);
    
    
     Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    
     Product(int id, String name, BigDecimal price) {
        this(id,name,price,Rating.NOT_RATED);
        
    }
     public String getRatingString() {
        return rating.getStars();
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " name: " + this.name + " price: " + this.price + " discount: " + this.getDiscount()+" rating: "+this.getRatingString() + " before " + getBestBefore();
    }
    public String toData() {
        return toString();
    }

    /**
     * Get the value of bestBefore
     *
     * @return the value of bestBefore
     */
    public LocalDate getBestBefore() {
        return LocalDate.now();
    }
}
