// Copyright © FunctionalKotlin.com 2017. All rights reserved.

sealed class Option<A>

object None : Option<Nothing>()

data class Just<A>(val value: A) : Option<A>()

