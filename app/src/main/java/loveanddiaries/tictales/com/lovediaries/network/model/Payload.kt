package loveanddiaries.tictales.com.lovediaries.network.model

data class Payload<T>(var token: String?, var user: User?, var items: MutableList<T>){
    override fun toString(): String {
        return "{token: $token, user: $user}"
    }
}