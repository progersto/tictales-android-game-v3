package loveanddiaries.tictales.com.lovediaries.network.model

data class AuthResponse(var payload: Payload<Unit>?){
    override fun toString(): String {
        return "{payload: $payload}"
    }
//Log
}