package com.mthaler.difromscratch

class Main

fun main(args: Array<String>) {
    val context = createContext()
    context?.let { doBusinessLogic(it) }
}

@Throws(Exception::class)
private fun createContext(): DIContext? {
    val rootPackageName: String = Main::class.java.getPackage().getName()
    return DIContext.createContextForPackage(rootPackageName)
}

private fun doBusinessLogic(context: DIContext) {
    val serviceA = context.getServiceInstance(ServiceA::class.java)
    val serviceB = context.getServiceInstance(ServiceB::class.java)
    println(serviceA?.jobA())
    println(serviceB?.jobB())
}