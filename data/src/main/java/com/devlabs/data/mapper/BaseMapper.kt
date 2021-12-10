package com.devlabs.data.mapper

abstract class BaseMapper<IN, OUT> {

    /**
     * Maps a single [entity]
     */
    abstract fun transform(entity: IN): OUT
}