package com.mthaler.difromscratch

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * All services need to be annotated with the Service annotation.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class Service