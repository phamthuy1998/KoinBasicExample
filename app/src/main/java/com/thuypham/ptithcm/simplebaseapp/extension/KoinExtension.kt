package com.thuypham.ptithcm.simplebaseapp.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import org.koin.android.ext.android.getKoinScope
import org.koin.androidx.viewmodel.ViewModelStoreOwnerProducer
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

fun Scope.getLoginScope(): Scope {
    return getKoin().getLoginScope()
}

@Synchronized
internal fun Koin.getLoginScope(): Scope {
    return getOrCreateScope(LOGIN_SCOPE, named(LOGIN_SCOPE))
}


inline fun <reified T : ViewModel> Fragment.sharedViewModel1(
    qualifier: Qualifier? = null,
    noinline owner: ViewModelStoreOwnerProducer = { requireActivity() },
    noinline parameters: ParametersDefinition? = null,
    scope: Scope,
): Lazy<T> {
    return viewModel(qualifier, owner, parameters, scope)
}

@OptIn(KoinInternalApi::class)
inline fun <reified T : ViewModel> Fragment.viewModel(
    qualifier: Qualifier? = null,
    noinline owner: ViewModelStoreOwnerProducer = { this },
    noinline parameters: ParametersDefinition? = null,
    scope: Scope,
): Lazy<T> {
    return viewModels(ownerProducer = owner) {
        getViewModelFactory<T>(owner(), qualifier, parameters, scope = scope)
    }
}