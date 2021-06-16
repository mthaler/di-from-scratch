package com.mthaler.difromscratch

import java.lang.reflect.Constructor

// Basically the Spring ApplicationContext
class DIContext(serviceClasses: Collection<Class<*>>) {

    private val serviceInstances: MutableSet<Any> = HashSet()

    init {
        // create an instance of each service class
        for (serviceClass in serviceClasses) {
            val constructor: Constructor<*> = serviceClass.getConstructor()
            constructor.setAccessible(true)
            val serviceInstance: Any = constructor.newInstance()
            serviceInstances.add(serviceInstance)
        }
        // wire them together
        for (serviceInstance in serviceInstances) {
            for (field in serviceInstance.javaClass.declaredFields) {
                if (!field.isAnnotationPresent(Inject::class.java)) {
                    // this field is none of our business
                    continue
                }
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
    }

    fun <T> getServiceInstance(serviceClass: Class<T>): T? {
        for (serviceInstance in serviceInstances) {
            if (serviceClass.isInstance(serviceInstance)) {
                return serviceInstance as T
            }
        }
        return null
    }
}