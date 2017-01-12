package com.codemash.caching;

import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.core.Hazelcast;

import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;

/**
 * Created by vikgamov on 1/12/17.
 */
public class ClusterMember {
    public static class MyListener implements javax.cache.event.CacheEntryCreatedListener<String, String> {
        @Override
        public void onCreated(Iterable<javax.cache.event.CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents) throws javax.cache.event.CacheEntryListenerException {
            cacheEntryEvents.forEach(e -> {
                System.out.println("key: " + e.getKey() + ", value: " + e.getValue());
            });
        }
    }

    public static void main(String[] args) {
        final MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        final Factory<MyListener> tFactory = FactoryBuilder.factoryOf(MyListener.class);
        MutableCacheEntryListenerConfiguration celc =
                new MutableCacheEntryListenerConfiguration(tFactory, null, false, false);

        configuration.addCacheEntryListenerConfiguration(celc);
        HazelcastServerCachingProvider.createCachingProvider(Hazelcast.newHazelcastInstance())
                .getCacheManager().createCache("city", configuration);
    }
}
