package com.example.android.analyzer.testutils

import org.mockito.Mockito

/**
 * Following two methods were added because it won't allow to use Mockito.any() on nullable
 * objects.
 */

fun <T> kotlinAny(): T {
	Mockito.any<T>()
	return uninitialized()
}

@Suppress("UNCHECKED_CAST")
fun <T> uninitialized(): T = null as T