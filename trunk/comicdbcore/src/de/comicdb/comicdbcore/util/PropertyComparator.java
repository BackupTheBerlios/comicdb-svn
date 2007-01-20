/**
 * ComicDB - overview you comics
 * Copyright (C) 2006  Daniel Moos
 *
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation; either version 2 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin 
 * St, Fifth Floor, Boston, MA 02110, USA
 */

package de.comicdb.comicdbcore.util;

import de.comicdb.comicdbcore.sort.Sort;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 *
 * @author <a href="mailto:daniel.moos@link-up.de">Daniel Moos</a>
 * eckardt & braun GmbH
 */
public class PropertyComparator implements Comparator {
    
    private String property;
    private int order = 1; // 1 = ASC, 1 = DESC
    
    public PropertyComparator(Sort sort) {
        this.property = sort.getProperty();
        this.order = sort.getOrder();
    }

    /** Creates a new instance of FieldComparable */
    public PropertyComparator(String property) {
        this.property = property;
    }
    
    public int compare(Object o1, Object o2) {
        try {
            Object field1 = getFieldObject(o1);
            Object field2 = getFieldObject(o2);
            
            if (!(field1 instanceof Comparable))
                throw new ClassCastException("Property '" + property + "' from Object 1 with Class '" + o1.getClass() + "' isn't a Comparable");

            if (!(field2 instanceof Comparable))
                throw new ClassCastException("Property '" + property + "' from Object 2 with Class '" + o2.getClass() + "' isn't a Comparable");
            int ret;
            // special for String 
            if (field1 instanceof String && field2 instanceof String)
                ret = ((String)field1).toLowerCase().compareTo(((String)field2).toLowerCase()) * order;
            else 
                ret = ((Comparable)field1).compareTo(field2) * order;
            
            if (ret == 0)
                ret = 1;
            return ret;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
    private Object getFieldObject(Object o) throws Exception {
        String methodName = "get" + firstCharToUpperCase(property);
        Method m = o.getClass().getMethod(methodName, new Class[] {});
        return m.invoke(o, new Object[] {} );
    }
    
    
    private String firstCharToUpperCase(String str) {
        String firstChar = str.substring(0,1);
        firstChar = firstChar.toUpperCase();
        return firstChar + str.substring(1);
    }
}
