// Copyright © FunctionalKotlin.com 2017. All rights reserved.

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

sealed class Option<out A>

object None : Option<Nothing>()

data class Just<out A>(val value: A) : Option<A>()

val json = """{
    "name": "Alex",
    "accountType": "Premium",
    "email": "alex@functionalhub.com",
    "url": "www.functionalhub.com"
}"""

data class Account(
    val name: String,
    val accountType: String,
    val email: String,
    val url: String) {

    companion object {
        fun fromJson(json: String): Account = asJsonObject(json).let {
            Account(
                it["name"] as String,
                it["accountType"] as String,
                it["email"] as String,
                it["url"] as String)
        }
    }
}

fun asJsonObject(json: String): JsonObject =
    StringBuilder(json).let { Parser().parse(it) } as JsonObject