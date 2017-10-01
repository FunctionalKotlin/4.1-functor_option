// Copyright © FunctionalKotlin.com 2017. All rights reserved.

sealed class Option<out A>

object None : Option<Nothing>()

data class Just<out A>(val value: A) : Option<A>()

class TextField(val text: Option<String> = None)

fun send(username: String) {
    //Logic to send username
}

fun onTextFieldChange(textField: TextField) = when (textField.text) {
    is None -> print("TextField is empty")
    is Just -> send(textField.text.value)
}