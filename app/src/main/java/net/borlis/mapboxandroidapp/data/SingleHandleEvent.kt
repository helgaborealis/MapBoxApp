package net.borlis.mapboxandroidapp.data

class SingleHandleEvent<out T>(private val content: T?) {

    var handled: Boolean = false

    val data: T?
        get() {
            return if (handled) {
                null
            } else {
                handled = true
                content
            }
        }
}