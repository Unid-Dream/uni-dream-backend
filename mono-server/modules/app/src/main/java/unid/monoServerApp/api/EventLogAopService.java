package unid.monoServerApp.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static unid.monoServerMeta.model.EventType.AVAILABLE_SESSION;

@Aspect
@Component
public class EventLogAopService {


    @Before("@annotation(loggable)")
    public void eventLogMethodCall(JoinPoint joinPoint, EventLoggable loggable) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Executing method: " + methodName);
        // 可以扩展为更复杂的日志记录逻辑
//        switch (loggable.eventType()){
//            case AVAILABLE_SESSION:
//                //设置时间段
//                break;
//        }
    }




}
