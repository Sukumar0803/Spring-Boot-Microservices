package com.microservices.customer.audit;

import com.microservices.customer.entity.BaseEntity;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Sukumar0803
 *
 * This is Listner class that calls Before INSERt OR Update the Document
 */
@Aspect
@Component
public class MongoTemplateAuditingAspect {

    Logger LOGGER = LogManager.getLogger(MongoTemplateAuditingAspect.class);

    @Before("execution(* org.springframework.data.mongodb.core.MongoTemplate.insert(..)) && args(object)")
    public void beforeInsert(Object object) {
        LOGGER.info("AOP Invoked for insert() Method::");
        if (object instanceof BaseEntity entity) {
            applyAuditing(entity);
        }
    }

    @Before("execution(* org.springframework.data.mongodb.core.MongoTemplate.save(..)) && args(object)")
    public void beforeSave(Object object) {
        LOGGER.info("AOP Invoked for save() Method::");
        if (object instanceof BaseEntity entity) {
            applyAuditing(entity);
        }
    }

    @Before("execution(* org.springframework.data.mongodb.core.MongoTemplate.insertAll(..)) && args(object)")
    public void beforeInsertAll(Object object) {
        LOGGER.info("AOP Invoked for insertAll() Method::");
        if (object instanceof BaseEntity entity) {
            applyAuditing(entity);
        }
    }

    private void applyAuditing(@NotNull BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        String userId = ThreadContext.get("X-castId");

        if (entity.getCreatedTimestamp() == null) {
            entity.setCreatedBy(userId);
            entity.setCreatedTimestamp(now);
        }
        entity.setModifiedBy(userId);
        entity.setModifiedTimestamp(now);
    }
}
