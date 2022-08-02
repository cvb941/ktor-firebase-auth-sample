package com.plusmobileapps.firebaseadminsample.routes

import com.plusmobileapps.firebaseadminsample.firebase.FIREBASE_AUTH
import com.plusmobileapps.firebaseadminsample.firebase.User
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/authenticated")
class AuthenticatedRoute

fun Route.authenticatedRoute() {
    authenticate(FIREBASE_AUTH) {
        get<AuthenticatedRoute> {
            val user: User =
                call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            call.respond("User is authenticated: $user")
        }
    }
}