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

/**
 *
 * @author oliver
 */
public class Review implements Comparable<Review>{
    private Rating raiting;
    private String comments;

    public Review(Rating raiting, String comments) {
        this.raiting = raiting;
        this.comments = comments;
    }

    public Rating getRaiting() {
        return raiting;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Review{" + "raiting=" + raiting + ", comments=" + comments + '}';
    }

    @Override
    public int compareTo(Review t) {
        return  this.raiting.ordinal() -  t.getRaiting().ordinal();
    }
    
    
}
