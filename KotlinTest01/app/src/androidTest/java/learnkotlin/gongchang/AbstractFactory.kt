package learnkotlin.gongchang

//参考https://blog.csdn.net/CSDN_LQR/article/details/123172728
/**
 * 抽象工厂改良：根据泛型参数类型构建工厂实例（inline + reified 具体化泛型参数类型）
 *
 * @author GitLqr
 */
abstract class AbstractFactory {
    abstract fun produce(): Computer

    companion object {
        inline operator fun <reified T : Computer> invoke(): AbstractFactory {
            return when (T::class) { // 不使用 reified 的话，T::class 会报错
                Dell::class -> DellFactory()
                Asus::class -> AsusFactory()
                else -> throw IllegalArgumentException()
            }
        }
    }
}

// 使用
val factory = AbstractFactory<Dell>()
val computer = factory.produce()



/**
 * 各品牌电脑
 *
 * @author GitLqr
 */
interface Computer
class Dell : Computer
class Asus : Computer


class DellFactory : AbstractFactory() {
    override fun produce() = Dell()
}

class AsusFactory : AbstractFactory() {
    override fun produce() = Asus()
}

