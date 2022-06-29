package ar.edu.unq.desapp.grupoM.backenddesappapi.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

@Aspect
@Slf4j
@Component
public class LogExecutionTimeAspectAnnotation {


    @Pointcut("within( ar.edu.unq.desapp.grupoM.backenddesappapi.controller.*)")
    public void controller() { }

    @Pointcut("execution(* *.*(..))")
    protected void allMethods() { }

    @Around("controller() && allMethods()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        // HTTP request info
        HttpServletRequest httpRequest = null;
        String httpMethod = null;
        String URI = null;
        try {
            httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            httpMethod = httpRequest.getMethod();
            URI = httpRequest.getRequestURI();
        }catch (Exception e){

        }
        // Date execution info
        Timestamp date = Timestamp.from(Instant.now());

        // Method MS time execution
         Long startTime = System.currentTimeMillis();
         Object LogObject = joinPoint.proceed();
         Long executionTime = System.currentTimeMillis() - startTime;

         // Request args
         Signature signature = joinPoint.getSignature();
         String args = new ObjectMapper().writeValueAsString(joinPoint.getArgs());

        methodInfoAndTime(date, executionTime, httpMethod, URI, signature, args);

        return LogObject;
    }

    private void methodInfoAndTime(Timestamp date, Long executionTime, String httpMethod, String URI, Signature signature, String args) {
        log.info(" ------------- LOG START ------------- ");
        log.info("Date: " + date);
        log.info("Request: " + httpMethod + " " + URI);
        log.info("Method name: "  + signature);
        log.info("Params: " + args );
        log.info("Finished method in " + executionTime + " ms");
        log.info(" ------------- LOG FINISH ------------- ");
    }


}
