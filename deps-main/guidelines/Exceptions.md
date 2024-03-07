# Practice on Project Exceptions 

## UnifiedException

- `UnifiedException` is a unified exception that will handle most of the common exception cases for the projects
  - HTTP response
  - generic errors
  - others

## Defined Global Exception Storage to centralise the exceptions

- Defined a global storage for all the exceptions
 - PROS:
   - Easy to manage error codes
   - centralised
 
```java
public class Exceptions {
    public static UnifiedException PermissionDenied(Throwable cause) {
        return new UnifiedException("TA_E001", "Permission Denied", HttpStatus.FORBIDDEN.value(), cause);
    }

    public static UnifiedException InsufficientInput(Throwable cause, String ...input) {
        return new UnifiedException("TA_E002", String.format("Permission Denied For Input: %s", StringUtils.join(input, ", ")), HttpStatus.BAD_REQUEST.value(), cause);
    }
}
```

