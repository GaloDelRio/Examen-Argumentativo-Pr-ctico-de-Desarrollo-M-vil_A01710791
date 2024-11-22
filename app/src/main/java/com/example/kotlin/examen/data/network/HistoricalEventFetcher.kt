package com.example.kotlin.examen.data.network.model

import com.example.kotlin.examen.data.network.NetworkModuleDI.callCloudFunction
import com.parse.ParseObject

/**
 * Clase que maneja las operaciones relacionadas con la recuperación de eventos históricos desde un servidor Parse.
 */
class HistoricalEventFetcher {

    /**
     * Obtiene eventos históricos desde un servidor Parse, con soporte para reintentos en caso de errores.
     *
     * @param maxRetries Número máximo de reintentos permitidos (valor predeterminado es 5).
     * @param callback Función lambda que recibe dos parámetros:
     * - Una lista de `HistoricalEvent` si la operación es exitosa, o `null` si ocurre un error.
     * - Una excepción `Exception` en caso de error, o `null` si la operación es exitosa.
     */
    fun fetchHistoricalEvents(
        maxRetries: Int = 5,
        callback: (List<HistoricalEvent>?, Exception?) -> Unit


    ) {
        val params = hashMapOf<String, Any>()

        /**
         * Función interna recursiva que realiza la llamada al servidor con reintentos en caso de error.
         *
         * @param currentAttempt Número actual de intentos realizados.
         */
        fun attemptCall(currentAttempt: Int) {
            callCloudFunction<HashMap<String, Any>>("hello", params) { result, error ->

                if (error == null && result != null) {
                    try { val data = result["data"] as? List<ParseObject>
                        if (data != null) {
                            val events = data.map { parseObject ->
                                HistoricalEvent(
                                    date = parseObject.getString("date") ?: "Unknown",
                                    description = parseObject.getString("description") ?: "No description",
                                    category1 = parseObject.getString("category1") ?: "Unknown",
                                    category2 = parseObject.getString("category2") ?: "Unknown"

                                )
                            }
                            callback(events, null)

                        } else {
                            callback(emptyList(), null)

                        }
                    } catch (e: Exception) {
                        callback(null, e)

                    }
                } else {
                    if (currentAttempt < maxRetries) {
                        attemptCall(currentAttempt + 1)
                    } else {
                        callback(null, error ?: Exception("Failed after $maxRetries attempts")
                        )
                    }
                }
            }
        }

        attemptCall(0)
    }
}
