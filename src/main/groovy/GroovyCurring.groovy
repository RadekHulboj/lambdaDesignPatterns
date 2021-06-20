class GroovyCurring {
    static void main(String[] args) {
        println "hello"

        new GroovyCurring().groovyCurring("1",  "2") {
            System.out.println(it.ala == 2)
        }

    }

    void groovyCurring(String one, String two, Closure closure) {
        closure([ala: 2, beta: [1,2,3]])
    }
}
