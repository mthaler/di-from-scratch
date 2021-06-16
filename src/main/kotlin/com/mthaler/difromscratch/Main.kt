package com.mthaler.difromscratch

fun main(args: Array<String>) {
    val serviceB = ServiceB()
    val serviceA = ServiceA(serviceB)
    println(serviceA.jobA())
}