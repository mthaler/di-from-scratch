package com.mthaler.difromscratch

interface ServiceA {

    fun getServiceB(): ServiceB

    fun setServiceB(serviceB: ServiceB)

    fun jobA(): String
}