package com.mthaler.difromscratch

class ServiceBImpl: ServiceB {

    @Inject
    private lateinit var serviceA: ServiceA

    override fun getServiceA(): ServiceA = serviceA

    override fun setServiceA(serviceA: ServiceA) {
        this.serviceA = serviceA
    }

    override fun jobB(): String = "jobB()"
}