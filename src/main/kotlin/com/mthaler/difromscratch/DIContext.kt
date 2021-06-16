package com.mthaler.difromscratch

import java.lang.reflect.Constructor

/**
 * The dependency injection container, or in Spring terms the ApplicationContext.
 *
 * @param serviceClasses service classes
 */
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

    /**
     * Returns the service instance for the given service class or null if there is no service instance
     *
     * @param serviceClass service class
     */
    fun <T> getServiceInstance(serviceClass: Class<T>): T? {
        for (serviceInstance in serviceInstances) {
            if (serviceClass.isInstance(serviceInstance)) {
                return serviceInstance as T
            }
        }
        return null
    }

    companion object {

        /**
         * Creates an application context for the given package name.
         * It iterates over all classes in the given package and checks if there are any service classes annotated with
         * the Service annotation.
         *
         * @param rootPackageName root package name
         */
        fun createContextForPackage(rootPackageName: String): DIContext {
            val allClassesInPackage: Set<Class<*>> = getAllClassesInPackage(rootPackageName)
            val serviceClasses: MutableSet<Class<*>> = HashSet()
            for (aClass in allClassesInPackage) {
                if (aClass.isAnnotationPresent(Service::class.java)) {
                    serviceClasses.add(aClass)
                }
            }
            return DIContext(serviceClasses)
        }

    }
}