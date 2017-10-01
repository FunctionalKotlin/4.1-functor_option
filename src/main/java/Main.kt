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
        fun fromJson(json: String): Option<Account> = asJsonObject(json).let {
            val name = it["name"] as? String
            val accountType = it["accountType"] as? String
            val email = it["email"] as? String
            val url = it["url"] as? String

            if (name != null && accountType != null && email != null && url != null)
                Just(Account(name, accountType, email, url))
            else
                None
        }
    }
}

fun asJsonObject(json: String): JsonObject =
    StringBuilder(json).let { Parser().parse(it) } as JsonObject

fun main(args: Array<String>) {
    val account = Account.fromJson(json)

    if (account is Just) {
        println(account.value.name)
    }
}