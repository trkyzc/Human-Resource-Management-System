package com.example.hrms.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheableConfig {
	
	enum CacheTarget{
		MEMORY,
		SHARED,
		MEMORY_AND_SHARED
	}
	
	CacheTarget cacheTarget() default CacheTarget.MEMORY_AND_SHARED;

}
