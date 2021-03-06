package com.soin.quesalvada.di.viewModels

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention()
@MapKey
internal annotation class ViewModelKey(
        val value: KClass<out ViewModel>)