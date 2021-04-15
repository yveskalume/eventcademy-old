package com.yvkalume.data.util

interface Mapper<in T,R> {
    fun map(t: T) : R
}