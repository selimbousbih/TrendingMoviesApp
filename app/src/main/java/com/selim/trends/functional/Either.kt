package io.thinkit.whatshot.functional

import kotlin.reflect.KFunction0

// Composes 2 functions
fun <A, B, C> ((A) -> B).compose(f: (B) -> C): (A) -> C = {
    f(this(it))
}

// A simple Either monad
sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight: Boolean
        get() = this is Right<R>

    val isLeft: Boolean
        get() = this is Left<L>
}

fun <L> left(a: L) = Either.Left(a)
fun <R> right(b: R) = Either.Right(b)

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Either.Left -> Either.Left(a)
            is Either.Right -> fn(b)
        }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.compose(::right))

fun <T, L, R> Either<L, R>.fold(fnL: (L) -> T, fnR: (R) -> T): T =
    when (this) {
        is Either.Left -> fnL(a)
        is Either.Right -> fnR(b)
    }

suspend fun <T, L, R> Either<L, R>.coFold(fnL: (L) -> T, fnR: suspend (R) -> T): T =
    when (this) {
        is Either.Left -> fnL(a)
        is Either.Right -> fnR(b)
    }