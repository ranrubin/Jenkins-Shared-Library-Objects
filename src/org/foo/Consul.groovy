package org.foo

/**
 * Register our data
 * @param path - url to consul
 * @param data - json as string
 * @return stdout
 */
def register(String path, String data){
    return sh (script: "curl -X PUT -H 'Content-Type: application/json' ${path}/catalog/register -d \'$data\'", returnStdout: true)
}

/**
 * Store a key and value in consul
 * @param path - url to consul
 * @param key_path - define a key in consul
 * @param data - data for key
 * @return stdout
 */
def store(String path, String key_path, String data){
    return  sh (script: "curl -X PUT -H 'Content-Type: application/json' ${path}/${key_path} -d \'${data}\'", returnStdout: true)

}

/**
 * list all nodes of consul
 * @param path - url to consul
 * @return stdout
 */
def list_nodes(String path){
    return sh (script: "curl -X GET ${path}/catalog/nodes", returnStdout: true)
}
