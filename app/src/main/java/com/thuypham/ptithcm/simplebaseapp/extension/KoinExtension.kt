package com.thuypham.ptithcm.simplebaseapp.extension

import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

fun Scope.getLoginScope(): Scope {
    return getKoin().getLoginScope()
}

@Synchronized
internal fun Koin.getLoginScope(): Scope {
    return getOrCreateScope(LOGIN_SCOPE, named(LOGIN_SCOPE))
}