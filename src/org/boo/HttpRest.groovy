package org.boo

httpEnv

/**
 * Constructor. Should be used at the beginning of any usage
 * @param base_url - the base url to work from
 * @return
 */
def construct(String base_url){

    httpEnv = [
            base_url: base_url,
            curl_options: '-sS',
            debug: false,
            authentication_header: '',
            ignore_flag: "null"
    ]
}

def getHttpEnv(){
    return httpEnv
}

/**
 * PUT request
 * @param path - path after the base url
 * @param data - json data  as  string
 * @param args
 * @return response from server
 */
def put(String path, String data){
    return exec_request(" -H \'Content-Type: application/json\'", "${httpEnv.curl_options}", 'PUT', "${httpEnv.base_url}/${path}", data)
}

/**
 *  GET request
 * @param path - path after the base url
 * @param args
 * @return response from server
 */
def get(String path){
    return exec_request('', "${httpEnv.curl_options}", 'GET', "${httpEnv.base_url}/${path}",'')

}

/**
 * Executes a REST request using curl command.
 * @param headers - headers values as a String
 * @param curl_opt - options for the curl command
 * @param operation - Which REST request to perform
 * @param full_url - The url api to interact with
 * @param data - json data  as  string
 * @return response from server
 */
def exec_request(String headers, String curl_opt, String operation, String full_url, String data){
   
    def command = "curl ${curl_opt} -X${operation} ${full_url} ${headers}"

    if (data != "null" && data != '') {

        command = "${command} -d \'${data}\'"
        if (httpEnv.debug){
            echo "$data"
        }
    }
    return sh (script: "${command}", returnStdout: true)
}

