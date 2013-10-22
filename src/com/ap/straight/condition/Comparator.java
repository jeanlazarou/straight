/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight.condition;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Comparator
{
    public abstract boolean accept(Object a, Object b);

    public static Comparator getComparator(String op, Class type)
    {
        if (op.length() == 1)
        {
            char c = op.charAt(0);

            if (c == '<')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() < ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) < 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) < 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) < 0;
                        }
                    };
                }
            }
            else if (c == '>')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() > ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) > 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) > 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) > 0;
                        }
                    };
                }
            }
            else if (c == '=')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() == ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) == 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) == 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) == 0;
                        }
                    };
                }
            }
        }
        else if (op.length() == 2)
        {
            char c1 = op.charAt(0);
            char c2 = op.charAt(1);

            if (c1 == '<' && c2 == '=')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() <= ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) <= 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) <= 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) <= 0;
                        }
                    };
                }
            }
            else if (c1 == '>' && c2 == '=')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() >= ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) >= 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) >= 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) >= 0;
                        }
                    };
                }
            }
            else if (c1 == '<' && c2 == '>')
            {
                if (type == Integer.class || type == int.class)
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((Integer) a).intValue() != ((Integer) b).intValue();
                        }
                    };
                }
				else if (type == Date.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((Date) a).compareTo((Date) b) != 0;
						}
					};
				}
				else if (type == BigDecimal.class)
				{
					return new Comparator() {
						public boolean accept(Object a, Object b)
						{
							return ((BigDecimal) a).compareTo((BigDecimal) b) != 0;
						}
					};
				}
                else
                {
                    return new Comparator() {
                        public boolean accept(Object a, Object b)
                        {
                            return ((String) a).compareTo((String) b) != 0;
                        }
                    };
                }
            }
        }

        return null;
    }
}
