package com.mthaler.difromscratch

class ServiceAImpl: ServiceA {

    @Inject
    private lateinit var serviceB: ServiceB

    override fun getServiceB(): ServiceB = serviceB

    override fun setServiceB(serviceB: ServiceB) {
        this.serviceB = serviceB
    }

    override fun jobA(): String = "jobA(${serviceB.jobB()})"
}