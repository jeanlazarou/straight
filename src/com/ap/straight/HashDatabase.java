/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.util.HashMap;

public class HashDatabase
{
    String name;

    HashMap tables = new HashMap();

    public void add(HashTable table)
    {
        tables.put(table.getName().toLowerCase(), table);
    }

    public HashTable get(String tablename)
    {
        return (HashTable) tables.get(tablename.toLowerCase());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
