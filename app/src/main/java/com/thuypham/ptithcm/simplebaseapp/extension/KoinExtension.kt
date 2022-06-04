package com.thuypham.ptithcm.simplebaseapp.extension

import com.thuypham.ptithcm.simplebaseapp.ui.fragment.LoginFragment
import org.koin.core.Koin
import org.koin.core.scope.Scope

const val LOGIN_SCOPE = "LOGIN_SCOPE"
fun Scope.getLoginScope(): Scope {
    return getKoin().getLoginScope()
}

@Synchronized
internal fun Koin.getLoginScope(): Scope {
    return getOrCreateScope<LoginFragment>(LOGIN_SCOPE)
}