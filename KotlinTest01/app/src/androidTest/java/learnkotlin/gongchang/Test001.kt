package learnkotlin.gongchang

//这是改良之后的简单工厂模式


//这是抽象的产品类
//制作电脑的CPU
interface ComputerCPU {
    companion object Factory{
        operator fun invoke(type: ComputerType): ComputerCPU{
            return when(type){
                ComputerType.sanxing -> sanxing()
                ComputerType.gaotong -> gaotong()
            }
        }
    }
    val cpu: String
    abstract fun start()
}

//电脑类
//具体的产品类
class sanxing(override val cpu: String = "三星") : ComputerCPU{
    override fun start() {
        println(cpu + "开始制作")
    }

}
class gaotong(override val cpu: String = "晓龙") : ComputerCPU{
    override fun start() {
        println(cpu + "开始制作")
    }

}

//CPU类型枚举
enum class ComputerType {
    sanxing, gaotong
}

// 使用工厂创建实例,并且使用实例中的方法
//使用户根据参数获得对应的类实例,避免了直接实例化,降低了耦合性
val pc = ComputerCPU(ComputerType.sanxing).start()
val server = ComputerCPU(ComputerType.gaotong).start()

