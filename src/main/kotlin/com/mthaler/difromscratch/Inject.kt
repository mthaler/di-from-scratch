package com.mthaler.difromscratch

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Fields of a service that reference another service need to be annotated with this annotation
 */
@Target(AnnotationTarget.FIELD)
@Retention(RetentionPolicy.RUNTIME)
annotation class Inject