package com.mthaler.difromscratch

class ServiceA(private val serviceB: ServiceB) {

    fun jobA(): String {
        return "jobA(${serviceB.jobB()})"
    }
}