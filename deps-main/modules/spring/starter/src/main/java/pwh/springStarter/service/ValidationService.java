package pwh.springStarter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

import javax.validation.ValidationException;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ValidationService {
    private final SmartValidator validator;

    public BindingResult validateWithResult(Object target) throws ValidationException {
        if (target instanceof Collection) {
            for (var obj : (Collection<?>) target) {
                validate(obj);
            }
        }
        var binder = new DataBinder(target, target.getClass().getSimpleName());
        binder.setValidator(validator);
        binder.validate();
        return binder.getBindingResult();
    }

    public void validate(Object target) throws ValidationException {
        handleBindingResult(validateWithResult(target));
    }

    public void handleBindingResult(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().get(0).toString());
        }
    }
}
