package com.mthaler.difromscratch


fun main(args: Array<String>) {
    val context = createContext()
    context?.let { doBusinessLogic(it) }
}

private fun createContext(): DIContext? {
    val serviceClasses: MutableSet<Class<*>> = HashSet()
    serviceClasses.add(ServiceAImpl::class.java)
    serviceClasses.add(ServiceBImpl::class.java)
    return DIContext(serviceClasses)
}

private fun doBusinessLogic(context: DIContext) {
    val serviceA = context.getServiceInstance(ServiceA::class.java)
    val serviceB = context.getServiceInstance(ServiceB::class.java)
    println(serviceA?.jobA())
    println(serviceB?.jobB())
}