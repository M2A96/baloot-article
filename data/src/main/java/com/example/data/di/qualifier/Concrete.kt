package com.example.data.di.qualifier

import javax.inject.Qualifier

/**
 * A qualifier to identify real cloud api calls
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Concrete