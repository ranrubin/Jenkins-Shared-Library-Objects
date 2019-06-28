@Library('Jenkins-Shared-Library-Objects@with-shared-library')

def consul = new org.foo.Consul()


node('NODE') {
    try {

        String jsonRegisterData = "{ .. }" // A string whatever data we want in json format
        
        String path = "http://myconsul.com:8500/v1"

        stage("register data") {
            consul.register(path, jsonRegisterData)
        }

        stage("store some data") {
            consul.store(path, "v1/kv/test/1/data", "value1")
            consul.store(path, "v1/kv/test/2/data", "value2")
        }

        stage("list Nodes"){
            consul.list_nodes(path)
        }

    }

    catch(err){
        //.. handle
    }
}
