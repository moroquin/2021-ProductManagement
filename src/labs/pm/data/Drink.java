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
import java.time.LocalTime;

/**
 *
 * @author oliver
 */
public class Drink extends Product {
    
     Drink(int id, String name, BigDecimal price) {
        super(id, name, price);
    }
    
    Drink(int id, String name, BigDecimal price, Rating newRating) {
        super(id, name, price, newRating);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();
        return (now.isAfter(LocalTime.of(17, 30))&& now.isBefore(LocalTime.of(18,30)))?super.getDiscount():BigDecimal.ZERO;
        
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(), getName(), getPrice(),newRating);
    }
    
}
