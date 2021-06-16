package com.mthaler.difromscratch


fun main(args: Array<String>) {
    val serviceClasses: MutableSet<Class<*>> = HashSet()
    serviceClasses.add(ServiceAImpl::class.java)
    serviceClasses.add(ServiceBImpl::class.java)

    val serviceA = createServiceA(serviceClasses)

    // call business logic

    // call business logic
    println(serviceA?.jobA())
}

@Throws(Exception::class)
private fun createServiceA(serviceClasses: Set<Class<*>>): ServiceA? {

    // create an instance of each service class
    val serviceInstances: MutableSet<Any> = HashSet()
    for (serviceClass in serviceClasses) {
        val constructor = serviceClass.getConstructor()
        constructor.setAccessible(true)
        serviceInstances.add(constructor.newInstance())
    }

    // wire them together
    for (serviceInstance in serviceInstances) {
        for (field in serviceInstance.javaClass.declaredFields) {
            val fieldType = field.type
            field.isAccessible = true
            // find a suitable matching service instance
            for (matchPartner in serviceInstances) {
                if (fieldType.isInstance(matchPartner)) {
                    field[serviceInstance] = matchPartner
                }
            }
        }
    }
    // from all our service instances, find ServiceA
    for (serviceInstance in serviceInstances) {
        if (serviceInstance is ServiceA) {
            return serviceInstance
        }
    }
    // we didn't find the requested service instance
    return null
}