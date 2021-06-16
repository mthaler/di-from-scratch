package com.mthaler.difromscratch

class ServiceAImpl(private val serviceB: ServiceB): ServiceA {

    override fun jobA(): String = "jobA(${serviceB.jobB()})"
}