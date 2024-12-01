import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)


fun <T, K> Pair<T, T>.map(foo: (T) -> K): Pair<K, K> = Pair(foo(first), foo(second))
fun <T> Pair<T, T>.forEach(foo: (T) -> Unit): Pair<T, T> = also { foo(first); foo(second) }
fun <T, K> Pair<List<T>, List<K>>.toListOfPairs(): List<Pair<T, K>> = first.zip(second)
