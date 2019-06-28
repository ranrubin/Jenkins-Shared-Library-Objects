@Library('Jenkins-Shared-Library-Objects@stateful-shared-library')

def consul = new org.foo.Consul()



node('NODE') {
    try {

        String jsonRegisterData = "{ .. }" // A string of whatever data we want in json format

        consul.construct("http://myconsul.com", "8500")


        stage("register data") {
            consul.register(jsonRegisterData)
        }

        stage("store some data") {
            consul.store("v1/kv/test/1/data", "value1")
            consul.store("v1/kv/test/2/data", "value2")
        }

        stage("list Nodes"){
            consul.list_nodes()
        }

    }

    catch(err){
        //.. handle
    }
}

