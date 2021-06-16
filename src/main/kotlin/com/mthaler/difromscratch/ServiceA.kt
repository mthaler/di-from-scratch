package com.mthaler.difromscratch

object ServiceA {

    fun jobA(): String {
        return "jobA(${ServiceB.jobB()})"
    }
}