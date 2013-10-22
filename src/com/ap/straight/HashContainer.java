/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.util.HashMap;

public class HashContainer
{
    static HashMap databases = new HashMap();

    public static void add(HashDatabase db)
    {
        databases.put(db.getName().toLowerCase(), db);
    }

    public static void clear()
    {
        databases.clear();
    }

    public static HashDatabase get(String dbname)
    {
        return (HashDatabase) databases.get(dbname.toLowerCase());
    }
}
