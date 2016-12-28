package com.ashsha.bss.ts.entity.db.core;

/**
 * The Class Enums.
 */
public class Enums
{

    /**
     * The Enum EntityStatus.
     */
    public enum EntityStatus
    {

        /** The Passive. */
        Passive(0),
        /** The Active. */
        Active(1);

        private final int id;

        /**
         * Instantiates a new entity status.
         *
         * @param value the value
         */
        EntityStatus(int value)
        {
            id = value;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId()
        {
            return id;
        }
    }

    /**
     * The Enum SortDirection.
     */
    public enum SortDirection
    {

        /** The asc. */
        ASC(0),
        /** The desc. */
        DESC(1);

        private final int id;

        /**
         * Instantiates a new sort direction.
         *
         * @param value the value
         */
        SortDirection(int value)
        {
            id = value;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId()
        {
            return id;
        }
    }

    /**
     * The Enum DurationUnit.
     */
    public enum DurationUnit
    {

        Minute(1, "Minute"), Hour(2, "Hour"), Day(3, "Day"), Month(4, "Month"), Year(5, "Year");

        private final int id;
        private final String label;

        /**
         * Instantiates a new duration unit.
         *
         * @param value the value
         * @param s the s
         */
        DurationUnit(int value, String s)
        {
            id = value;
            label = s;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId()
        {
            return id;
        }

        /**
         * Gets the label.
         *
         * @return the label
         */
        public String getLabel()
        {
            return label;
        }

        /**
         * Find.
         *
         * @param s the s
         * @return the duration unit
         */
        public static DurationUnit find(String s)
        {
            for (DurationUnit unit : DurationUnit.values())
            {
                if (unit.getLabel().equals(s))
                {
                    return unit;
                }
            }
            return null;
        }
    }

    /**
     * The Enum PaymentType.
     */
    public enum PaymentType
    {

        /** The Cash. */
        Cash(0),
        /** The Credit card. */
        CreditCard(1),
        /** The Cheque. */
        Cheque(2),
        /** The Direct debit. */
        DirectDebit(3);

        private final int id;

        /**
         * Instantiates a new payment type.
         *
         * @param value the value
         */
        PaymentType(int value)
        {
            id = value;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId()
        {
            return id;
        }
    }
}

