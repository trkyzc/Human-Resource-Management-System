//package com.example.hrms.cache;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.interceptor.AbstractCacheResolver;
//import org.springframework.cache.interceptor.CacheOperationInvocationContext;
//
//import com.example.hrms.cache.CacheableConfig.CacheTarget;
//
//@Slf4j
//public class CustomCacheResolver extends AbstractCacheResolver {
//
//  private final CacheManager memoryCacheManager;
//
//  private final CacheManager remoteCacheManager;
//
//  public CustomCacheResolver(CacheManager memoryCacheManager,CacheManager remoteCacheManager) {
//    super(memoryCacheManager);
//    this.memoryCacheManager = memoryCacheManager;
//    this.remoteCacheManager = remoteCacheManager;
//  }
//
//  @Override
//  public Collection<? extends Cache> resolveCaches(  // kullanılan cache listesini döndürür
//      CacheOperationInvocationContext<?> context) {
//    Collection<Cache> caches = new ArrayList<>();
//    CacheableConfig cacheableConfig = context.getMethod().getAnnotation(CacheableConfig.class);  
//
//    boolean hasMemory = cacheableConfig == null
//        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY
//        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY_AND_SHARED;
//
//    boolean hasShared = cacheableConfig == null
//        || cacheableConfig.cacheTarget() == CacheTarget.SHARED
//        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY_AND_SHARED;
//
//    Collection<String> cacheNames = getCacheNames(context);
//    for (String cacheName: cacheNames) {
//      if (hasMemory) {
//        Optional.ofNullable(memoryCacheManager.getCache(cacheName))
//            .ifPresent(caches::add);  //cache -> caches.add(cache) = caches::add
//      }
//      if (hasShared) {
//        Optional.ofNullable(remoteCacheManager.getCache(cacheName))
//            .ifPresent(caches::add);
//      }
//    }
//
//    log.info("Cache resolved count: {}" , caches.size());
//    log.info("Cache configuration: MEMORY = {}, SHARED = {}" , hasMemory, hasShared);
//    return caches;
//  }
//
//  @Override
//  protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
//    return context.getOperation().getCacheNames();
//  }
//}





package com.example.hrms.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;

import com.example.hrms.cache.CacheableConfig.CacheTarget;

@Slf4j
public class CustomCacheResolver extends AbstractCacheResolver {

  private final CacheManager memoryCacheManager;
  private final CacheManager remoteCacheManager;

  public CustomCacheResolver(CacheManager memoryCacheManager, CacheManager remoteCacheManager) {
    super(memoryCacheManager);
    this.memoryCacheManager = memoryCacheManager;
    this.remoteCacheManager = remoteCacheManager;
  }

  @Override
  public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
    Collection<Cache> caches = new ArrayList<>();
    CacheableConfig cacheableConfig = context.getMethod().getAnnotation(CacheableConfig.class);

    boolean hasMemory = cacheableConfig == null
        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY
        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY_AND_SHARED;

    boolean hasShared = cacheableConfig == null
        || cacheableConfig.cacheTarget() == CacheTarget.SHARED
        || cacheableConfig.cacheTarget() == CacheTarget.MEMORY_AND_SHARED;

    Collection<String> cacheNames = getCacheNames(context);
    for (String cacheName : cacheNames) {
      if (hasMemory) {
        Optional<Cache> memoryCache = Optional.ofNullable(memoryCacheManager.getCache(cacheName));
        memoryCache.ifPresent(cache -> {
          caches.add(cache);
          logCacheSource(cacheName, "MEMORY");
        });
      }
      if (hasShared) {
        Optional<Cache> remoteCache = Optional.ofNullable(remoteCacheManager.getCache(cacheName));
        remoteCache.ifPresent(cache -> {
          caches.add(cache);
          logCacheSource(cacheName, "SHARED");
        });
      }
    }

    log.info("Cache resolved count: {}", caches.size());
    log.info("Cache configuration: MEMORY = {}, SHARED = {}", hasMemory, hasShared);
    return caches;
  }

  @Override
  protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
    return context.getOperation().getCacheNames();
  }

  /**
   * Cache'in hangi kaynaktan geldiğini loglar.
   * 
   * @param cacheName Cache adı
   * @param cacheSource Hangi kaynaktan geldiği (MEMORY veya SHARED)
   */
  private void logCacheSource(String cacheName, String cacheSource) {
    log.info("Cache '{}' resolved from {}.", cacheName, cacheSource);
  }
}



