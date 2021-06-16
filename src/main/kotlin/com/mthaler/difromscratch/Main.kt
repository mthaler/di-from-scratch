package com.mthaler.difromscratch

fun main(args: Array<String>) {
    val serviceB: ServiceB = ServiceBImpl()
    val serviceA: ServiceA = ServiceAImpl(serviceB)
    println(serviceA.jobA())
}