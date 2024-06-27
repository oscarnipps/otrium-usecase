package com.android.otrium.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class MainDispatcherRule private constructor() : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }

    companion object {
        fun create(): MainDispatcherRule {
            return MainDispatcherRule()
        }
    }
}