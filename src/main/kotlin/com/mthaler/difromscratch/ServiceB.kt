package com.mthaler.difromscratch

interface ServiceB {

    fun getServiceA(): ServiceA

    fun setServiceA(serviceA: ServiceA)

    fun jobB(): String
}