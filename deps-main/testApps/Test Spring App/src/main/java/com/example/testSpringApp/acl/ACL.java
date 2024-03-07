package com.example.testSpringApp.acl;

import com.example.testSpringApp.data.UserRole;

public class ACL {

    // to globally get current session user
    public static <Value> pwh.coreStarter.acl.ACL<Value> of(Value value) {
        return pwh.coreStarter.acl.ACL.of(value, () -> UserRole.ADMIN);
    }
}
