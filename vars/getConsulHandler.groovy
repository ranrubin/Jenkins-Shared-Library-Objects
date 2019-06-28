def call(httpObj){
    def consul =  new org.foo.Consul()
    consul.construct(httpObj)
    return consul
}

