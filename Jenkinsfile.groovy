node('NODE') {
    try {
        
        String jsonRegisterData = "{ .. }"

        stage("register data") {
            sh (script: "curl -X PUT -H 'Content-Type: application/json' http://myconsul.com:8500/v1/catalog/register -d \'$jsonRegisterData\'", returnStdout: true)
        }

        stage("store some data") {
            sh (script: "curl -X PUT -H 'Content-Type: application/json' http://myconsul.com:8500/v1/kv/test/1/data -d \'value1\'", returnStdout: true)
            sh (script: "curl -X PUT -H 'Content-Type: application/json' http://myconsul.com:8500/v1/kv/test/2/data -d \'value2\'", returnStdout: true)
        }

        stage("list Nodes"){

            sh (script: "curl -X GET http://myconsul.com:8500/v1/catalog/nodes", returnStdout: true)
            
        }
        
    }

    catch(err){
        //.. handle
    }
}


