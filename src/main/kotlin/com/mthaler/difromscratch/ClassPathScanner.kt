package com.mthaler.difromscratch

import java.io.File
import java.net.URL
import java.util.*

fun getAllClassesInPackage(packageName: String): Set<Class<*>> {
    val classLoader = Thread.currentThread().contextClassLoader
    val path = packageName.replace('.', '/')
    val resources: Enumeration<URL> = classLoader.getResources(path)
    val dirs: MutableList<File> = ArrayList()
    while (resources.hasMoreElements()) {
        val resource: URL = resources.nextElement()
        dirs.add(File(resource.getFile()))
    }
    val classes: MutableSet<Class<*>> = HashSet()
    for (directory in dirs) {
        classes.addAll(findClasses(directory, packageName))
    }
    return classes
}

private fun findClasses(directory: File, packageName: String): List<Class<*>> {
    val classes: MutableList<Class<*>> = ArrayList()
    if (!directory.exists()) {
        return classes
    }
    val files = directory.listFiles()
    for (file in files) {
        if (file.isDirectory) {
            classes.addAll(findClasses(file, packageName + "." + file.name))
        } else if (file.name.endsWith(".class")) {
            classes.add(Class.forName(packageName + '.' + file.name.substring(0, file.name.length - 6)))
        }
    }
    return classes
}