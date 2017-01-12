package com.codemash.caching;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;

/**
 * Created by vikgamov on 1/12/17.
 */
public class ClusterMember {
    public static void main(String[] args) {
        final HazelcastInstance hazelcastInstance = newHazelcastInstance();
        final IMap<String, String> city = hazelcastInstance.getMap("city");
        city.putIfAbsent("OH", "Sandusky");
        city.putIfAbsent("test", "dunno");
    }
}
