package pwh.coreStarter.acl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class ACL<Result> {
    private final String defaultUser = String.format("%s-%s", ACL.class.getName(), "____defaultNoUser____");
    private HashMap<String, Function<Result, Result>> resultMap = new HashMap<>();
    private Result inputResult;
    private Supplier<Object> currentUserRole;

    public ACL(Result result, Supplier<Object> currentUserRole) {
        this.inputResult = result;
        this.currentUserRole = currentUserRole;
    }

    public static <Result> ACL<Result> of(
            Result result,
            Supplier<Object> currentUserRole
    ) {
        return new ACL<>(result, currentUserRole);
    }

    public NotUserStep onDefault(Function<Result, Result> modifiedResult) {
        resultMap.put(defaultUser, modifiedResult);
        return new NotUserStep();
    }

    public class NotUserStep {
        public UserStep onNotUser(Function<Result, Result> modifiedResult) {
            resultMap.put(null, modifiedResult);
            return new UserStep();
        }
    }

    public class UserStep {
        public UserStep on(Object userRole, Function<Result, Result> modifiedResult) {
            if (ObjectUtils.isEmpty(userRole) || StringUtils.isBlank(userRole.toString())) {
                throw new RuntimeException("Invalid User Role: <empty>");
            }
            resultMap.put(userRole.toString(), modifiedResult);
            return this;
        }

        public Result get() {
            var currentUser = currentUserRole.get();
            log.info("Current User: {}", currentUser);
            if (currentUser == null) {
                return resultMap.get(null).apply(inputResult);
            }
            return resultMap
                    .getOrDefault(
                            currentUser.toString(),
                            resultMap.get(defaultUser)
                    )
                    .apply(inputResult);
        }
    }
}
