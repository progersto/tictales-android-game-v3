package loveanddiaries.tictales.com.lovediaries.network.model

data class User(var id: Long, var username: String?, var email: String?, val country: String?, var guest: Boolean, var media: Media){
    override fun toString(): String {
        return "{id: $id, username: $username, email: $email, country: $country, guest: $guest, media: $media}"
    }
}