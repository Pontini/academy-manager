package com.systems.pontini.core

interface Mapper<S, T> {
    fun map(source: S): T
}
