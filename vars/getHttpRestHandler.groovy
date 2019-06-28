def call(String base_url){
    def http =  new org.boo.HttpRest()
    http.construct(base_url)
    return http
}
