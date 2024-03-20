/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.enums;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

import unid.jooqMono.model.Public;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.15.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum BookingStatusEnum implements EnumType {

    AVAILABLE("AVAILABLE"),

    RESERVED("RESERVED"),

    PENDING("PENDING"),

    ACCEPTED("ACCEPTED"),

    REJECTED("REJECTED"),

    CANCELLED("CANCELLED"),

    VOID("VOID"),

    FINISHED("FINISHED"),

    UNFINISHED("UNFINISHED"),

    ATTEND("ATTEND"),

    ABSENT("ABSENT");

    private final String literal;

    private BookingStatusEnum(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "booking_status";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
