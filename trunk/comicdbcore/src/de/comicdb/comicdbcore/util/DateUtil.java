/*
 * ComicDB
 *
 * Copyright (C) 2005-2006 Daniel Moos
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package de.comicdb.comicdbcore.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author <a href="mailto:Reiner.Kroemer@link-up.de">R. Kroemer</a>
 */
public class DateUtil {
    private static DateFormat formatDDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");
    
    /** Creates a new instance of DateUtil */
    public DateUtil() {
    }
    
    public static Date getNewDate() {
        return getFormattedDate(formatDDMMYYYY.format(new Date()));
    }
    
    public static Date getNewDate(String rollday) {
        try {
            int i = Integer.parseInt(rollday);
            return getNewDate(i);
        } catch(Exception e)  {
            //ignore
        }
        return getNewDate();
    }
    
    public static Date getNewDate(int rollday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getNewDate());
        cal.roll(Calendar.DAY_OF_YEAR, rollday);
        return cal.getTime();
    }
    
    public static String getFormattedDate(Date date) {
        String retValue = "";
        if (date != null)
            retValue = formatDDMMYYYY.format(date);
        return retValue;
    }
    
    public static Date getFormattedDate(String date) {
        Date retValue = null;
        if (date == null)
            return null;
        try {
            retValue = formatDDMMYYYY.parse(date);
        } catch (ParseException e) {
            //ignore
        }
        return retValue;
    }
}
