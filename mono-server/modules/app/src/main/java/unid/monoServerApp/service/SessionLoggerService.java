package unid.monoServerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import unid.monoServerApp.model.SessionLogger;

import javax.annotation.Resource;

@Component
public class SessionLoggerService {
    @Resource
    private ApplicationEventPublisher publisher;

    public void async(final SessionLogger.OpEvent event) {
        SessionLogger opEvent = new SessionLogger(this, event);
        publisher.publishEvent(opEvent);
    }
}
