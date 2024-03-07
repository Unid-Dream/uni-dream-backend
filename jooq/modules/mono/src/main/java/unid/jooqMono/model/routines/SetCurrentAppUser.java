/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.routines;


import javax.annotation.processing.Generated;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

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
public class SetCurrentAppUser extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>public.set_current_app_user.cu</code>.
     */
    public static final Parameter<String> CU = Internal.createParameter("cu", SQLDataType.CLOB, false, false);

    /**
     * Create a new routine call instance
     */
    public SetCurrentAppUser() {
        super("set_current_app_user", Public.PUBLIC);

        addInParameter(CU);
    }

    /**
     * Set the <code>cu</code> parameter IN value to the routine
     */
    public void setCu(String value) {
        setValue(CU, value);
    }
}
