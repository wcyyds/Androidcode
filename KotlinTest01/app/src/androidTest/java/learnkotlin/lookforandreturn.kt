package learnkotlin

fun main(){
    var b = startlist()
    println("-----" + b.toString() + "-----")
}

fun startlist() : Int{
    val list = mutableListOf(1,2,3,4,5,6)
    list.add(8)
    for(a in list){
        println(a.toString())
        if(a == 4){
            return 0
        }
    }
    return 100
}