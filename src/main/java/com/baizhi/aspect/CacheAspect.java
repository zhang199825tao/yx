package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect  {



    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "execution(* com.baizhi.service.*Impl.query*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取类的全路径
        String classname = joinPoint.getTarget().getClass().getName();
        String methodname = joinPoint.getSignature().getName();
        Object[] argsname = joinPoint.getArgs();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(classname).append(methodname);
        for (Object o : argsname) {
            append.append(o);
        }
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        Object result =null;
        if(redisTemplate.opsForHash().hasKey(classname, stringBuffer.toString())){
            result = redisTemplate.opsForHash().get(classname, stringBuffer.toString());
        }else {
            result = joinPoint.proceed();//方法的返回值
            redisTemplate.opsForHash().put(classname, stringBuffer.toString(),result);
        }
        return result;
    }



    @After("@annotation(com.baizhi.annotation.CherryAnnotation)")//代表只要有这个注解的方法，就会进入这个方法
    public void afterAdvice(JoinPoint joinPoint){
        String classname = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(classname);
    }



}
