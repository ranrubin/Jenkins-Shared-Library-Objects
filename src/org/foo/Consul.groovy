package org.foo


/**
 *
 * https://www.consul.io/api/kv.html
 * https://www.consul.io/api/catalog.html
 *
 * Interact with a consul
 *
 */

/** A map that holds all constants and data members that can be override when constructing  */
consulEnv

/** An HttpRest object that handles http requests */
httpHandler

/**
 * Imitates a constructor with object composition
 * Defines an instance of Consul object. All according to api
 *  * https://www.consul.io/api/kv.html
 *  * https://www.consul.io/api/catalog.html
 * @param httpObj - an HttpRest object
 * @return
 */
def construct(httpObj){

    consulEnv = [
            ip : '',
            port: '',
            base_url: '',
            debug: false // default value that can be overwritten

    ]

    httpHandler = httpObj

}
def getConsulEnv(){
    return consulEnv
}


/**
 * Merges otherMap key-values into baseMap. That means that:
 * 1. For every key that belongs to baseMap and otherMap. baseMap[key]=otherMap[key]
 * 2. For every key that belongs to baseMap and NOT to otherMap, baseMap don't change.
 * 3. For every key that belongs to otherMap and NOT to baseMap, the key and corresponding value are added to baseMap
 * @param baseMap - A Map
 * @param otherMap -  A Map
 * @return - No need to return as it's not deep copied.
 */
def mergeMaps(Map baseMap, Map otherMap){
    if (!baseMap || !otherMap){
        return
    }
    otherMap.each{ key, value ->
        baseMap[key] = value
    }
}

/**
 * Use this to override constants and add data members.
 * @param values - Map of values user wants to override/add to object
 * @return stdout
 */
def overrideConstants(Map values){
    mergeMaps(consulEnv, values) // overriding parameters
    if (!values.base_url) { // if did not get any pther input from user, constructing it myself by default convention
        consulEnv.put('base_url', "http://${consulEnv.ip}:${consulEnv.port}/v1")
    }
}

/**
 * Register our data
 * @param data - json as string
 * @return stdout
 */
def register(String data){
    return httpHandler.put("/catalog/register", data)
}

/**
 * Store a key and value in consul
 * @param key_path - define a key in consul
 * @param data - data for key
 * @return stdout
 */
def store(String key_path, String data){
    return httpHandler.put(key_path, data)

}

/**
 * list all nodes of consul
 * @param path - url to consul
 * @return stdout
 */
def list_nodes(){
    return httpHandler.get("/catalog/nodes")
}
